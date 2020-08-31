package com.simonalong.neo.express;

import com.simonalong.neo.NeoQueue;

/**
 * 逻辑运算符
 * <p>{@code and、or、not（但是在数据库中，not暂时不作为处理）}
 *
 * @author shizi
 * @since 2020/8/30 12:52 上午
 */
public abstract class LogicOperate extends BaseOperate {

    public LogicOperate(String operateSymbol, Operate operate) {
        super();
        super.setOperateSymbol(operateSymbol);
        super.offerOperate(operate);
    }

    public LogicOperate(String operateSymbol, NeoQueue<Operate> operateQueue) {
        super();
        super.setOperateSymbol(operateSymbol);
        super.setChildOperateQueue(operateQueue);
    }

    @Override
    public void offerOperate(Operate value) {
        this.childOperateQueue.offer(value);
    }

    @Override
    public Boolean needWhere() {
        return true;
    }

    @Override
    public Boolean valueLegal() {
        return true;
    }

    @Override
    public NeoQueue<Object> getValueQueue() {
        NeoQueue<Object> valueQueue = NeoQueue.of();
        for (Operate operate : super.childOperateQueue) {
            valueQueue.addAll(operate.getValueQueue());
        }
        return valueQueue;
    }
}

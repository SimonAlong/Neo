package com.simonalong.neo.express;

import com.simonalong.neo.util.ObjectUtil;
import lombok.Getter;

import java.util.Collection;

/**
 * 关系运算符
 * <p>{@code <、>、<>、!=、=、>=、<=}
 *
 * @author shizi
 * @since 2020/8/30 12:50 上午
 */
public abstract class RelationOperate extends BaseOperate {

    @Getter
    private String key;
    @Getter
    private Object value;

    public RelationOperate(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 判断value是否满足要求
     * @return true：满足，false：不满足
     */
    @Override
    public Boolean valueLegal() {
        return ObjectUtil.isNotEmpty(value);
    }
}
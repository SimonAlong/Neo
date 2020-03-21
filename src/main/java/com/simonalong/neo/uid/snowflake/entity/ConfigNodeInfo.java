package com.simonalong.neo.uid.snowflake.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * config 节点中的信息
 *
 * @author 柿子
 * @since 2019/11/7 10:38 下午
 */
@Data
@Accessors(chain = true)
public class ConfigNodeInfo implements Serializable
{
    /**
     * 当前业务的最大机器个数，为2的次幂
     */
    private Integer currentMaxMachine;
    /**
     * 保留的数据
     */
    private Integer rsv;

    /**
     * 机房的bit占用
     */
    private Integer rsvBits;
    /**
     * 时间戳占用bit
     */
    private Integer timestampBits;
    /**
     * 机器占用的bit个数
     */
    private Integer workerBits;
    /**
     * 自增序列占用的bit个数
     */
    private Integer sequenceBits;
}

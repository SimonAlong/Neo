package com.simonalong.neo.devide;

import lombok.Data;

import java.util.List;

/**
 * @author shizi
 * @since 2020/6/11 9:48 AM
 */
@Data
public class TableDevideConfig {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 实际表名集合
     */
    private List<String> actTableNameList;
    /**
     * 分表的列名
     */
    private String columnName;
    private Integer min;
    private Integer size;
    /**
     * 若表的size为2的次方，则该markNum有值
     */
    private Integer markNum;
}

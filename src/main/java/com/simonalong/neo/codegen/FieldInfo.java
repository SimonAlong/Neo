package com.simonalong.neo.codegen;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author zhouzhenyong
 * @since 2019/3/23 下午11:06
 */
@Getter
@Accessors(chain = true)
final class FieldInfo {

    /**
     * 属性的类的类型
     */
    @Setter
    private String fieldType;
    /**
     * 属性的描述
     */
    private String fieldRemark;
    private Integer fieldRemarkFlag = 0;
    /**
     * 属性的名字
     */
    @Setter
    private String fieldName;

    FieldInfo setFieldRemark(String fieldRemark) {
        if (null != fieldRemark && !fieldRemark.equals("")) {
            this.fieldRemark = fieldRemark;
            this.fieldRemarkFlag = 1;
        }
        return this;
    }
}

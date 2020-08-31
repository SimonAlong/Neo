package com.simonalong.neo;

import lombok.Data;

/**
 * 分页请求类
 * <p>
 *     注意：该类已经改名为{@link com.simonalong.neo.db.PageReq}
 * @author shizi
 * @since 2020/8/22 4:34 PM
 */
@Deprecated
@Data
public class NeoPageReq<T> {

    private Integer pageNo;
    private Integer pageSize;
    private T param;

    public Integer getPageIndex() {
        return pageNo > 1 ? (pageNo - 1) * pageSize : 0;
    }
}


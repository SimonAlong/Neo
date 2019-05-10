package com.simon.neo.db;

import com.simon.neo.exception.NeoException;

/**
 * @author zhouzhenyong
 * @since 2019/5/10 下午4:42
 */
public class ColumnParseException extends NeoException {

    public ColumnParseException(String msg) {
        super(msg);
    }
}

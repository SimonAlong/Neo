package com.simon.neo.exception;

/**
 * @author zhouzhenyong
 * @since 2019/5/4 下午2:45
 */
public class NeoMapChgException extends NeoException {

    public NeoMapChgException(String msg){
        super("Neo异常：" + msg);
    }
}

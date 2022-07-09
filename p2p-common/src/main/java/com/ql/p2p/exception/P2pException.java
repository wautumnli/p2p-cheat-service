package com.ql.p2p.exception;

import java.text.MessageFormat;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:25
 */
public class P2pException extends RuntimeException{

    public P2pException(String msg) {
        super(msg);
    }

    public P2pException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public static P2pException fail(String pattern, Object... params) {
        String msg = MessageFormat.format(pattern, params);
        return new P2pException(msg);
    }

    public P2pException(Throwable throwable) {
        super(throwable);
    }
}

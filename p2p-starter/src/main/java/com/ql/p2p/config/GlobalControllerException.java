package com.ql.p2p.config;

import com.ql.p2p.exception.P2pException;
import com.ql.p2p.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wanqiuli
 * @date 2022/7/9 16:03
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerException {

    @ExceptionHandler(value = P2pException.class)
    public Result<String> p2pExceptionHandle(P2pException exception) {
        return Result.fail(exception.getMessage());
    }
}

package com.asuis.j2ee.exception;

import com.asuis.j2ee.dto.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 15988440973
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";
    @ExceptionHandler(value = Exception.class)
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        Result result = new Result();
        result.setCode(501);
        result.setMessage(e.getMessage());
        return result;
    }
}

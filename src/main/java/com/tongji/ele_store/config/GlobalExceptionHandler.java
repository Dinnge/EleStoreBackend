package com.tongji.ele_store.config;

import com.tongji.ele_store.IResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<IResponse<String>> handleException(Exception e) {
        log.error("Exception occurred: ", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(IResponse.error("服务器内部错误: " + e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IResponse<String>> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("Illegal argument: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(IResponse.error(e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<IResponse<String>> handleIllegalStateException(IllegalStateException e) {
        log.warn("Illegal state: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(IResponse.error(e.getMessage()));
    }
}
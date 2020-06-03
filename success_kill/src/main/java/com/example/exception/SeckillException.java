package com.example.exception;

import com.example.dto.SeckillStatEnum;
import com.example.entity.SuccessKilled;

public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

}

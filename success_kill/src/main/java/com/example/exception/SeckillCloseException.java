package com.example.exception;

import com.example.dto.SeckillStatEnum;
import com.example.entity.SuccessKilled;

public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }



}

package com.cx_project.kiiun.global.auth.exception;


import com.cx_project.kiiun.global.exception.CustomException;
import com.cx_project.kiiun.global.exception.ErrorCode;

public class TokenException extends CustomException {
    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}

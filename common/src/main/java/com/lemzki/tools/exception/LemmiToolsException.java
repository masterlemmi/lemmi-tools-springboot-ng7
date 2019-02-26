package com.lemzki.tools.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class LemmiToolsException extends RuntimeException {

    public LemmiToolsException(String msg){
        super(msg);
    }
}

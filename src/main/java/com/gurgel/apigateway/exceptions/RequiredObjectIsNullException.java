package com.gurgel.apigateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND )
public class RequiredObjectIsNullException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RequiredObjectIsNullException(String x){
        super(x);
    }

    public RequiredObjectIsNullException(){
        super("It is not allowed to persist null objects");
    }

}

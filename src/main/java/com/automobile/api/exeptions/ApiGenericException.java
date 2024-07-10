package com.automobile.api.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiGenericException extends RuntimeException {

    private static final long serialVersionUID = -4535302142757855307L;
    private String message;

    public ApiGenericException() {
    }

    public ApiGenericException(String message) {
        this.message = message;
    }

    public ApiGenericException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
		this.message = message;
	}
}
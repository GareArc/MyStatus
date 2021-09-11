package com.mystatus.application.resources.admin;

import org.restlet.resource.Status;

@Status(value = 10001)
public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }
}

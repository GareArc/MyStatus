package com.mystatus.application.exception;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Resource;
import org.restlet.service.StatusService;

public class CustomStatusService extends StatusService{
    @Override
    public Representation toRepresentation(Status status, Request request, Response response){
        if(status.getCode() == 400){
           return new JacksonRepresentation<>(status.getReasonPhrase());
        }
        return null;
    }

    @Override
    public Status toStatus(Throwable throwable, Resource resource) {
        if(throwable.getCause() instanceof CustomException){
            return new Status(400, throwable.getCause().getMessage());
        }
        return Status.CLIENT_ERROR_BAD_REQUEST;
    }
}

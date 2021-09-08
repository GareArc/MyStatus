package com.mystatus.application.resources.papi;

import org.restlet.resource.ServerResource;

public class PapiResource extends ServerResource {
    private String specialChar = "%";

    public String buildPlaceHolderStr(String content){
        return specialChar + content + specialChar;
    }

}

package com.mystatus.application.resources.papi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.restlet.resource.Get;

public class PapiServerResource extends PapiResource {

    @Get("txt")
    public String getPlaceHolder(){
        String placeHolderStr = getAttribute("placeholder");
        return PlaceholderAPI.setPlaceholders(null, buildPlaceHolderStr(placeHolderStr));
    }

}

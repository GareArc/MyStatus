package com.mystatus.application.resources.papi;

import com.mystatus.application.ServerManager;
import me.clip.placeholderapi.PlaceholderAPI;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PapiServerResource extends ServerResource {

    @Get
    public String getPlaceHolder(){
        String placeHolderStr = getAttribute("placeholder");
        return ServerManager.getInstance().getPlaceHolderResult(placeHolderStr, null);
    }

}

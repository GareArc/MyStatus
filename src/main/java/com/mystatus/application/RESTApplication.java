package com.mystatus.application;

import com.mystatus.application.resources.admin.CustomStatusService;
import com.mystatus.application.resources.admin.WhiteListResource;
import com.mystatus.application.resources.papi.PapiPlayerNameResource;
import com.mystatus.application.resources.papi.PapiPlayerUUIDResource;
import com.mystatus.application.resources.papi.PapiServerResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

public class RESTApplication extends Application {

    public RESTApplication(){
        setStatusService(new CustomStatusService());
    }

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        setUpRouter(router);
        return router;
    }

    private void setUpRouter(Router router){
        registerPath(router, "/papi/server/{placeholder}", PapiServerResource.class);
        registerPath(router, "/papi/player/{username}/{placeholder}", PapiPlayerNameResource.class);
        registerPath(router, "/papi/player/uuid/{uuid}/{placeholder}", PapiPlayerUUIDResource.class);
        registerPath(router, "/admin/whitelist/{uuid}", WhiteListResource.class);
        registerPath(router, "/admin/whitelist/{auth}/{username}", WhiteListResource.class);
    }

    private void registerPath(Router router, String path,
                              Class<? extends ServerResource> resourceClass){
        router.attach(path, resourceClass);
    }
}

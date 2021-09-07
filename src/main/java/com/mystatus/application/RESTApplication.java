package com.mystatus.application;

import com.mystatus.application.resources.LoginResource;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

public class RESTApplication extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        setUpRouter(router);
        return router;
    }

    private void setUpRouter(Router router){
        registerPath(router, "/logincount", LoginResource.class);
    }

    private void registerPath(Router router, String path,
                              Class<? extends ServerResource> resourceClass){
        router.attach(path, resourceClass);
    }
}

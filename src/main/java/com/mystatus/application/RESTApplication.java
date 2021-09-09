package com.mystatus.application;

import com.mystatus.application.resources.admin.WhiteListResource;
import com.mystatus.application.resources.papi.PapiPlayerResource;
import com.mystatus.application.resources.papi.PapiServerResource;
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
        registerPath(router, "/papi/server/{placeholder}", PapiServerResource.class);
        registerPath(router, "/papi/player/{username}/{placeholder}", PapiPlayerResource.class);
        registerPath(router, "/admin/whitelist/{username}", WhiteListResource.class);
        registerPath(router, "/admin/whitelist/{auth}/{username}", WhiteListResource.class);
    }

    private void registerPath(Router router, String path,
                              Class<? extends ServerResource> resourceClass){
        router.attach(path, resourceClass);
    }
}

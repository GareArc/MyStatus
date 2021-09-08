package com.mystatus.application;

import com.mystatus.application.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IoCFactory {
    private static IoCFactory instance = null;
    private ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    private IoCFactory(){}

    public static IoCFactory getInstance(){
        if(instance == null) instance = new IoCFactory();
        return instance;
    }

    public ServerManager getServerManager(){ return getBean(ServerManager.class); }

    public<T> T getBean(Class<T> targetClass){
        return context.getBean(targetClass);
    }

    public<T> T getBean(Class<T> targetClass, String name){
        return context.getBean(name, targetClass);
    }


}

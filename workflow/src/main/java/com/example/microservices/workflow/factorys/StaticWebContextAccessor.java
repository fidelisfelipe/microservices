package com.example.microservices.workflow.factorys;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class StaticWebContextAccessor implements ApplicationContextAware {
    @Autowired
    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public static SimpMessagingTemplate getMessagingTemplate() {
        return appContext.getBean(SimpMessagingTemplate.class);
    }
}

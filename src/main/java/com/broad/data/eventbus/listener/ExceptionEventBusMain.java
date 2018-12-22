package com.broad.data.eventbus.listener;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

public class ExceptionEventBusMain {

    public static void main(String[] args) {


        final EventBus eventBus = new EventBus(new SubscriberExceptionHandler() {
            @Override
            public void handleException(Throwable exception, SubscriberExceptionContext context) {
                System.out.println(context.getEvent());
                System.out.println(context.getSubscriberMethod());
                System.out.println(context.getSubscriber());
            }
        });
        eventBus.register(new ExceptionListener());
        eventBus.post("exception...");
    }
}

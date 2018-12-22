package com.broad.data.eventbus.listener;

import com.google.common.eventbus.EventBus;

public class DeadEventBusMain {


    public static void main(String[] args) {
        final EventBus eventBus = new EventBus("dead EventBus"){
            @Override
            public String toString() {
                return "DEAD-EVENTBUS";
            }
        };

        eventBus.register(new DeadEventListener());
        eventBus.post("hello");


    }
}

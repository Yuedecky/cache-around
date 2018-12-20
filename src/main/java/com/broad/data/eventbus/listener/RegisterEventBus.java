package com.broad.data.eventbus.listener;

import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterEventBus {


    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterEventBus.class);

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        LOGGER.info("start post an simple event");
        eventBus.post("a event");
    }
}

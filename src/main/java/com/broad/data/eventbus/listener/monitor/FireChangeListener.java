package com.broad.data.eventbus.listener.monitor;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FireChangeListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(FireChangeListener.class);

    @Subscribe
    public void onChange(FireChangeEvent event) {
        LOGGER.info("onchange:kind={},path={}", event.getKind(), event.getPath());
    }

    public static void main(String[] args) throws IOException {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FireChangeListener());
        TargetMonitor monitor = new DirectoryMonitor(eventBus, "/home/broad/excel/uat", "");
        monitor.startMonitor();
    }


}

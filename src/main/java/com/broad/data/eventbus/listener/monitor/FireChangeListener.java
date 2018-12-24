package com.broad.data.eventbus.listener.monitor;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.io.IOException;

public class FireChangeListener {

    @Subscribe
    public void onChange(FireChangeEvent event) {
        System.out.println("onchange:" + event.getKind());
    }

    public static void main(String[] args) throws IOException {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FireChangeListener());
        TargetMonitor monitor = new DirectoryMonitor(eventBus, "/home/broad/excel/uat", "");
        monitor.startMonitor();
    }


}

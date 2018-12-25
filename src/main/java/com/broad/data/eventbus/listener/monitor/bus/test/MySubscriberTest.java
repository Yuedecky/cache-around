package com.broad.data.eventbus.listener.monitor.bus.test;

import com.broad.data.eventbus.listener.monitor.bus.MyEventBus;
import com.broad.data.eventbus.listener.monitor.bus.MySubscribe;

public class MySubscriberTest {

    @MySubscribe
    public void testMySubscriber(String arg) {
        System.out.println("Enter into testMySubscriber method:args:" + arg);
    }

    public static void main(String[] args) {
        MyEventBus myEventBus = new MyEventBus();
        myEventBus.register(new MySubscriberTest());
        myEventBus.post("xxx");
    }
}

package com.broad.data.eventbus.listener.monitor.bus;

import java.lang.reflect.Method;

public interface MyEventContext {

    String getSource();

    Object getSubscriber();

    Method getSubscriberMethod();

    Object getEvent();
}

package com.broad.data.eventbus.listener.monitor.bus;

import java.util.concurrent.ConcurrentLinkedQueue;

public interface Registry {

    void bind(Object subscriber);

    void unbind(Object subscriber);

    ConcurrentLinkedQueue<MySubscriber> scanSubscriber(final String topic);
}

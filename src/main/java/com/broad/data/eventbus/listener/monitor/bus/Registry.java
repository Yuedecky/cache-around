package com.broad.data.eventbus.listener.monitor.bus;

public interface Registry {

    void bind(Object subscriber);

    void unbind(Object subscriber);

}

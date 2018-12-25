package com.broad.data.eventbus.listener.monitor.bus;

public interface Bus {


    void register(Object subscriber);

    void post(Object event);

    void post(Object event,String topic);

    void close();

    void unRegister(Object subscriber);


    String getBusName();


}

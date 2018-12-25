package com.broad.data.eventbus.listener.monitor.bus;


import java.util.concurrent.Executor;

public class MyEventBus implements Bus {


    private Registry registry = new MyRegistry();

    private static final String DEFAULT_EVENT_NAME = "default";


    private static final String DEFAULT_TOPIC = "default-topic";

    private final String busName;

    private final MyEventBusDispatcher dispatcher ;


    public MyEventBus() {
        this(DEFAULT_EVENT_NAME, MyEventBusDispatcher.SEQ_EXECUTOR_SERVICE);
    }

    public MyEventBus(String busName) {
        this(busName, MyEventBusDispatcher.SEQ_EXECUTOR_SERVICE);
    }


    public MyEventBus(String busName,  Executor executor) {
        this.busName = busName;
        dispatcher = MyEventBusDispatcher.dispatcher(null,executor);
    }

    @Override
    public void post(Object event, String topic) {
        dispatcher.dispatcher(this,registry,event,topic);
    }

    @Override
    public void register(Object object) {

        this.registry.bind(object);
    }

    @Override
    public void post(Object event) {
        this.post(event,DEFAULT_TOPIC);
    }

    @Override
    public void close() {

    }

    @Override
    public void unRegister(Object subscriber) {
        this.registry.unbind(subscriber);
    }

    @Override
    public String getBusName() {
        return busName;
    }
}

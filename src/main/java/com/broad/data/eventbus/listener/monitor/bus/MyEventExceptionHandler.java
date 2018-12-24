package com.broad.data.eventbus.listener.monitor.bus;

public interface MyEventExceptionHandler {

    void handleException(Throwable t,MyEventContext context);
}

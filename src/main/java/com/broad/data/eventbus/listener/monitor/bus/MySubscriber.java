package com.broad.data.eventbus.listener.monitor.bus;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

@Getter
@Setter
public class MySubscriber {

    private final Object subscribeObject ;

    private final Method subscribeMethod;

    private boolean disabled = false;

    public MySubscriber(Object subscribeObject, Method subscribeMethod) {
        this.subscribeObject = subscribeObject;
        this.subscribeMethod = subscribeMethod;
    }

    public Method getSubscribeMethod() {
        return subscribeMethod;
    }

    public Object getSubscribeObject() {
        return subscribeObject;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}

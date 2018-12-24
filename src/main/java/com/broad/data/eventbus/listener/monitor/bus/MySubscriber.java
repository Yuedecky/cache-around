package com.broad.data.eventbus.listener.monitor.bus;

import lombok.Getter;

import java.lang.reflect.Method;

@Getter
public class MySubscriber {

    private final Object subscriberObject ;

    private final Method subscriberMethod;

    private boolean disabled = false;

    public MySubscriber(Object subscriberObject, Method subscriberMethod) {
        this.subscriberObject = subscriberObject;
        this.subscriberMethod = subscriberMethod;
    }


    public Object getSubscriberObject() {
        return subscriberObject;
    }

    public Method getSubscriberMethod() {
        return subscriberMethod;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}

package com.broad.data.eventbus.listener.monitor.bus;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MySubscribe {
    String topic() default "default-topic";
}

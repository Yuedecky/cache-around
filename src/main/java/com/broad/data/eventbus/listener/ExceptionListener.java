package com.broad.data.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionListener.class);


    @Subscribe
    public void m1(String m) {
        System.out.println("Entering m1 method,args:" + m);
    }

    @Subscribe
    public void m2(String m) {
        System.out.println("Entering m2 method,args:" + m);
        throw new RuntimeException("occurs exception");
    }
}

package com.broad.data.eventbus.listener;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class DeadEventListener {

    @Subscribe
    public void dead(DeadEvent arg) {
        System.out.println("dead event ,args:" + arg);
    }
}

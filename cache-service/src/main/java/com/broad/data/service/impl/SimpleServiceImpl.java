package com.broad.data.service.impl;

import com.broad.data.service.SimpleService;

public class SimpleServiceImpl implements SimpleService {

    @Override
    public String info() {
        return "A service from SimpleServiceImpl.class";
    }
}

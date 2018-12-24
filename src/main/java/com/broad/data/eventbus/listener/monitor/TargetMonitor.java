package com.broad.data.eventbus.listener.monitor;

import java.io.IOException;

public interface TargetMonitor {

    void startMonitor() throws IOException;

    void stopMonitor() throws IOException;
}

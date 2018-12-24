package com.broad.data.eventbus.listener.monitor;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

@Setter
@Getter
public class FireChangeEvent {

    private final Path path;

    private final WatchEvent.Kind kind;


    public FireChangeEvent(Path path,WatchEvent.Kind kind) {
        this.path = path;
        this.kind = kind;
    }
}

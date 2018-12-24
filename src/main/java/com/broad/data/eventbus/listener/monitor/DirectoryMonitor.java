package com.broad.data.eventbus.listener.monitor;


import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DirectoryMonitor implements TargetMonitor {


    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryMonitor.class);


    private WatchService watchService;

    /**
     *eventBus
     */
    private EventBus eventBus;


    private volatile boolean start;

    /**
     * 监控的目录
     */
    private final Path pathToMonitor;


    public DirectoryMonitor(final EventBus eventBus, final String targetPath, final String... morePath) {
        this.eventBus = eventBus;
        this.pathToMonitor = Paths.get(targetPath, morePath);
    }


    @Override
    public void startMonitor() throws IOException {

        this.watchService = FileSystems.getDefault().newWatchService();

        this.pathToMonitor.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);

        LOGGER.info("开始监听:{}", this.pathToMonitor);
        this.start = true;
        while (start) {
            WatchKey watchKey = null;
            try {
                watchKey = watchService.take();
                List<WatchEvent<?>> eventList = watchKey.pollEvents();
                eventList.forEach(
                        event -> {
                            WatchEvent.Kind<?> kind = event.kind();
                            Path path = (Path) event.context();
                            Path children = DirectoryMonitor.this.pathToMonitor.resolve(path);
                            eventBus.post(new FireChangeEvent(children, kind));
                        }
                );
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                if (watchKey != null) {
                    watchKey.reset();
                }
            }
        }
    }

    @Override
    public void stopMonitor() throws IOException {
        LOGGER.info("停止监控：{}", pathToMonitor);
        Thread.currentThread().interrupt();
        this.start = false;
        this.watchService.close();
    }
}

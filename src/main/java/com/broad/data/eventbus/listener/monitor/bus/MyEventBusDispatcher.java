package com.broad.data.eventbus.listener.monitor.bus;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class MyEventBusDispatcher {

    private final Executor executor;

    private final MyEventExceptionHandler eventExceptionHandler;

    public static final SeqExecutorService SEQ_EXECUTOR_SERVICE = SeqExecutorService.INSTANCE;

    private MyEventBusDispatcher(Executor executor, MyEventExceptionHandler eventExceptionHandler) {
        this.executor = executor;
        this.eventExceptionHandler = eventExceptionHandler;
    }

    static MyEventBusDispatcher dispatcher(MyEventExceptionHandler eventExceptionHandler,Executor executor){
        return new MyEventBusDispatcher(executor, eventExceptionHandler);
    }


    public void dispatcher(Bus bus,Registry registry,Object event,String topic){

    }

    public void close(){
        if(executor instanceof ExecutorService){
            ((ExecutorService)executor).shutdown();
        }
    }

    private static class SeqExecutorService implements Executor {

        private static final SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {

            command.run();

        }
    }
}

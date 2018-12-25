package com.broad.data.eventbus.listener.monitor.bus;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
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

    static MyEventBusDispatcher dispatcher(MyEventExceptionHandler eventExceptionHandler, Executor executor) {
        return new MyEventBusDispatcher(executor, eventExceptionHandler);
    }


    public void dispatcher(Bus bus, Registry registry, Object event, String topic) {

        ConcurrentLinkedQueue<MySubscriber> subscribers = registry.scanSubscriber(topic);
        if (null == subscribers) {
            if (eventExceptionHandler != null) {
                eventExceptionHandler.handleException(new IllegalArgumentException("The topic" + topic + "not bind yet"),
                        new BaseEventContext(bus.getBusName(), null, event));
            }
            return;
        }

        subscribers.stream().filter(subscriber -> !subscriber.isDisabled())
                .filter(subscriber -> {
                    Method method = subscriber.getSubscribeMethod();
                    Class<?> aClz = method.getParameterTypes()[0];
                    return aClz.isAssignableFrom(event.getClass());
                }).forEach(
                subscriber -> {
                    realInvokeMySubscribe(subscriber, event, bus);
                }
        );

    }


    /**
     * @param subscriber
     * @param event
     * @param bus
     */
    private void realInvokeMySubscribe(MySubscriber subscriber, Object event, Bus bus) {
        Method method = subscriber.getSubscribeMethod();
        Object object = subscriber.getSubscribeObject();
        executor.execute(() -> {
            try {
                method.invoke(object, event);
            } catch (Exception e) {
                if (null != eventExceptionHandler) {
                    eventExceptionHandler.handleException(e, null);
                }
            }
        });
    }

    public void close() {
        if (executor instanceof ExecutorService) {
            ((ExecutorService) executor).shutdown();
        }
    }

    private static class SeqExecutorService implements Executor {

        private static final SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {

            command.run();

        }
    }


    private static class BaseEventContext implements MyEventContext {

        private final String eventBusName;

        private final MySubscriber mySubscriber;


        private final Object event;

        public BaseEventContext(String eventBusName, MySubscriber mySubscriber, Object event) {
            this.eventBusName = eventBusName;
            this.mySubscriber = mySubscriber;
            this.event = event;
        }

        @Override
        public String getSource() {
            return this.eventBusName;
        }

        @Override
        public Object getSubscriber() {
            return this.mySubscriber != null ? mySubscriber.getSubscribeObject() : null;
        }

        @Override
        public Method getSubscriberMethod() {
            return this.mySubscriber != null ? mySubscriber.getSubscribeMethod() : null;
        }

        @Override
        public Object getEvent() {
            return this.event;
        }
    }
}

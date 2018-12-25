package com.broad.data.eventbus.listener.monitor.bus;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MyRegistry implements Registry {


    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<MySubscriber>> subscriberContainer = new ConcurrentHashMap<>();

    @Override
    public void bind(Object subscriber) {
        List<Method> subscriberMethods = getSubscriberMethods(subscriber);
        subscriberMethods.forEach(
                m -> {
                    tierSubscriber(subscriber, m);
                }
        );
    }


    @Override
    public ConcurrentLinkedQueue<MySubscriber> scanSubscriber(final String topic) {
        return subscriberContainer.get(topic);
    }

    private void tierSubscriber(Object subscriber, Method method) {
        final MySubscribe mySubscriber = method.getDeclaredAnnotation(MySubscribe.class);
        String topic = mySubscriber.topic();
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        subscriberContainer.get(topic).add(new MySubscriber(subscriber, method));
    }

    /**
     * @param subscriber
     * @return
     */
    private List<Method> getSubscriberMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> clz = subscriber.getClass();
        while (clz != null) {
            Method[] declaredMethods = clz.getDeclaredMethods();
            Arrays.stream(declaredMethods).filter(m -> m.isAnnotationPresent(MySubscribe.class)
                    && m.getParameterCount() == 1 && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            clz = clz.getSuperclass();
        }
        return methods;
    }

    @Override
    public void unbind(Object subscriber) {

        subscriberContainer.forEach((key, queue) -> {
            queue.forEach(s -> {
                        if (s.getSubscribeObject() == subscriber) {
                            s.setDisabled(true);
                        }
                    }
            );
        });

    }
}

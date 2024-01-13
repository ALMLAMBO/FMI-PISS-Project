package com.rateuni.backend.services.di;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SimpleDIContainer implements DIContainer {
    private final Map<Class<?>, Object> services = new HashMap<>();

    @Override
    public <T> void register(Class<T> type, T instance) {
        services.put(type, instance);
    }

    @Override
    public <T> T resolve(Class<T> type) {
        T service = (T) services.get(type);

        if(service == null) {
            throw new IllegalArgumentException("No registered service");
        }

        return service;
    }
}

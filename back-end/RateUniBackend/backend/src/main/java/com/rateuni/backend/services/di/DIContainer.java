package com.rateuni.backend.services.di;

public interface DIContainer {
    <T> void register(Class<T> type, T instance);
    <T> T resolve(Class<T> type);
}

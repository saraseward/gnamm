package com.sara.gnamm.services;

public interface ServiceListener<T> {
    void onSuccess(T result);

    void onError(int code, ServiceError serviceError, String message);
}

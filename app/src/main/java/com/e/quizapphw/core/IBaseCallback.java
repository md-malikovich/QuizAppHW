package com.e.quizapphw.core;

public interface IBaseCallback<T> {
    void onSuccess(T result);

    void onFailure (Exception e);
}

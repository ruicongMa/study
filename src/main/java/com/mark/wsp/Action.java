package com.mark.wsp;

public interface Action<T> {

    T get() throws Exception;
}
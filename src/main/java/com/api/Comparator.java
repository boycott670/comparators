package com.api;

@FunctionalInterface
public interface Comparator<T>{
    int compare(final T left,final T right);
}

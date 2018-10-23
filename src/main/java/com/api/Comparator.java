package com.api;

@FunctionalInterface
public interface Comparator<T> {
    int compare(final T left, final T right);

    default Comparator<T> reversed() {
        return (left, right) -> compare(right, left);
    }

}

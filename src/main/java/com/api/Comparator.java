package com.api;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface Comparator<T> {
    int compare(final T left, final T right);

    default Comparator<T> reversed() {
        return (left, right) -> compare(right, left);
    }

    default Comparator<T> thenComparing(Comparator<? super T> other) {
        requireNonNull(other);
        return (left, right) -> (
                (compare(left, right) != 0) ? compare(left, right) : other.compare(left, right)
        );
    }
}

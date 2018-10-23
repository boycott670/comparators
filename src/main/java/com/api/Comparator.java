package com.api;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface Comparator<T> {
    int compare(final T left, final T right);

    default Comparator<T> reversed() {
        return (left, right) -> compare(right, left);
    }

    default Comparator<T> thenComparing(Comparator<? super T> other) {
        requireNonNull(other);
        return (left, right) -> {
            int result = compare(left, right);
            return (result != 0) ? result : other.compare(left, right);
        };
    }

    default <R extends Comparable<? super R>> Comparator<T> thenComparing(Function<? super T, ? extends R> extractor) {
        requireNonNull(extractor);
        return (left, right) -> {
            int result = compare(left, right);
            return (result != 0) ? result : extractor.apply(left).compareTo(extractor.apply(right));
        };
    }
}

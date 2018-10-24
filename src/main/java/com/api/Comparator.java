package com.api;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

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

    default <U> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        requireNonNull(keyComparator);
        requireNonNull(keyExtractor);
        return (left, right) -> {
            int result = compare(left, right);
            return (result != 0) ? result : keyComparator.compare(keyExtractor.apply(left), keyExtractor.apply(right));
        };
    }

    default Comparator<T> thenComparingDouble(ToDoubleFunction<? super T> keyExtractor) {
        requireNonNull(keyExtractor);
        return (left, right) -> {
            int result = compare(left, right);
            return (result != 0) ? result : Double.compare(keyExtractor.applyAsDouble(left), keyExtractor.applyAsDouble(right));
        };
    }

    default Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor) {
        requireNonNull(keyExtractor);
        return (left, right) -> {
            int result = compare(left, right);
            return (result != 0) ? result : Integer.compare(keyExtractor.applyAsInt(left), keyExtractor.applyAsInt(right));
        };
    }

    default Comparator<T> thenComparingLong(ToLongFunction<? super T> keyExtractor) {
        requireNonNull(keyExtractor);
        return (left, right) -> {
            int result = compare(left, right);
            return (result != 0) ? result : Long.compare(keyExtractor.applyAsLong(left), keyExtractor.applyAsLong(right));
        };
    }
}

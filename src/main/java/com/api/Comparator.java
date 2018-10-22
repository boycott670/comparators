package com.api;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface Comparator<T> {
    int compare(final T left, final T right);

    default Comparator<T> reversed() {
        return new Reversed<>(this);
    }

    class Reversed<T> implements Comparator<T> {
        final Comparator<T> comparator;

        Reversed(Comparator<T> original) {
            requireNonNull(original);
            this.comparator = original;
        }

        @Override
        public int compare(T left, T right) {
            return comparator.compare(right, left);
        }
    }
}

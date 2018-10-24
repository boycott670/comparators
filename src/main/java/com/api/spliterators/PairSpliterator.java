package com.api.spliterators;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PairSpliterator<T extends Pair<? super T>> implements Spliterator<T> {

    public int characteristics() {
        return DISTINCT | ORDERED | NONNULL;
    }

    public long estimateSize() {
        throw new UnsupportedOperationException();
    }

    public void forEachRemaining(Consumer<? super T> action) {
        throw new UnsupportedOperationException();
    }

    public boolean tryAdvance(Consumer<? super T> action) {
        throw new UnsupportedOperationException();
    }

    public PairSpliterator<T> trySplit() {
        throw new UnsupportedOperationException();
    }
}

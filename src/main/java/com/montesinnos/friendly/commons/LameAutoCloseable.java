package com.montesinnos.friendly.commons;

public interface LameAutoCloseable extends AutoCloseable {

    @Override
    void close() throws RuntimeException;
}

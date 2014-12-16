package com.cleancoder.base.common.util;


import com.google.common.base.Objects;

/**
 * Created by Leonid on 16.11.2014.
 */
public final class Result<V, T extends Throwable> {

    public static <R, E extends Throwable> Result<R, E> get(R result) {
        return new Result<R,E>(result, null);
    }

    public static <R, E extends Throwable> Result<R, E> absent(E exception) {
        if (exception == null) {
            throw new IllegalArgumentException("Cannot have an absent constructor with a null exception");
        }
        return new Result<R,E>(null, exception);
    }

    private final V result;
    private final T exception;

    private Result(V result, T exception) {
        this.result = result;
        this.exception = exception;
    }

    public boolean isException() {
        return exception != null;
    }

    public V get() {
        if (exception != null) {
            throw new IllegalStateException("Cannot call get() for an exception Result.", exception);
        }
        return result;
    }

    public T getException() {
        if (exception == null) {
            throw new UnsupportedOperationException("Cannot retrieve an exception when one was not set.");
        }
        return exception;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(exception) ^ Objects.hashCode(result);
    }

    @Override
    public boolean equals(Object them) {
        if (this == them) {
            return true;
        }
        if (Result.class.isInstance(them)) {
            return Objects.equal(result, ((Result<?, ?>) them).result) &&
                    Objects.equal(exception, ((Result<?, ?>) them).exception);
        }
        return false;
    }
}

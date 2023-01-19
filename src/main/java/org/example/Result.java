package org.example;

import java.util.Optional;

public class Result<T, E> {

    private final Optional<T> ok;
    private final Optional<E> err;

    public static <T, E> Result<T, E> ok(T t) throws NullPointerException {
        if (t == null) {
            throw new NullPointerException();
        }

        return new Result<>(Optional.of(t), Optional.empty());
    }

    public static <T, E> Result<T, E> err(E e) throws NullPointerException {
        if (e == null) {
            throw new NullPointerException();
        }

        return new Result<>(Optional.empty(), Optional.of(e));
    }

    public Object unwrap() {
        if (this.ok.isPresent()) {
            return getOk();
        } else {
            return getErr();
        }
    }

    public Object unwrapOrDefault(T defaultValue) throws NullPointerException {
        if (defaultValue == null) {
            throw new NullPointerException();
        }

        if (isErr()) {
            return defaultValue;
        } else {
            return getOk();
        }
    }

    public boolean isOk() {
        return this.ok.isPresent();
    }

    public boolean isErr() {
        return this.err.isPresent();
    }

    @Override
    public String toString() {
        return ok.map(t -> "Result{ok=" + t + '}')
                .orElseGet(() -> "Result{err=" + err.get() + '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result)) return false;

        Result<?, ?> result = (Result<?, ?>) o;

        if (!ok.equals(result.ok)) return false;
        return err.equals(result.err);
    }

    @Override
    public int hashCode() {
        int result = ok.hashCode();
        result = 31 * result + err.hashCode();
        return result;
    }

    private Result(Optional<T> ok, Optional<E> err) {
        this.ok = ok;
        this.err = err;
    }

    private T getOk() {
        return this.ok.get();
    }

    private E getErr() {
        return this.err.get();
    }

}

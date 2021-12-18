package com.fulkyJmartRK;

/**
 * interface yang digunakan untuk melakukan filtering
 * @param <T> generic object
 */
@FunctionalInterface
public abstract interface Predicate<T> {
    public boolean predicate (T arg);
}
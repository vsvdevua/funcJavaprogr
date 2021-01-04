package com.company.second;


@FunctionalInterface
public interface Criteria<E> {
    boolean test(E c);
}

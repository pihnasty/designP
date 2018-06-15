package com.aws.util;

@FunctionalInterface
public interface StringParser<T>{
    T parse(String string);
}

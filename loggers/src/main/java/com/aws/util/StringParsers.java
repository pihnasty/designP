package com.aws.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * TODO class description
 */
public final class StringParsers {

    private static Map<Class, StringParser> parserMap = new HashMap<>();

    public static <T> StringParser<T> registerType(Class<T> type, StringParser<T> parser){
        StringParser oldParser = parserMap.put(type, Objects.requireNonNull(parser));
        if(oldParser != null && !oldParser.equals(parser))
            throw new IllegalArgumentException("Attempt to re-register class " + type.getCanonicalName());
        return parser;
    }

    public static <T extends Enum<T>> StringParser<T> registerEnumType(Class<T> enumType){
        return registerType(enumType, string -> Enum.valueOf(enumType, string));
    }

    public static <T> StringParser<T> registerAnnotatedParsableType(Class<T> type) {
        // Try 1: c-tor

        MethodHandle handle = tryGetAnnotatedConstructorHandle(type);
        if (handle != null)
            return registerType(type, createStringParserFromMethodHandle(handle));

        handle = tryGetAnnotatedFactoryMethodHandle(type);
        if (handle != null)
            return registerType(type, createStringParserFromMethodHandle(handle));

        return null;
    }

    private static <T> StringParser<T> createStringParserFromMethodHandle(final MethodHandle mh){
        return string -> {
            try {
                return (T) mh.invokeExact(string);
            } catch (RuntimeException runtimeException) {
                throw runtimeException;
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        };
    }

    private static final Class[] PARSE_METHOD_PARAMETERS = {String.class};


    private static MethodHandle tryGetAnnotatedConstructorHandle(Class<?> type){
        try {
            Constructor ctor = type.getConstructor(String.class);
            if(ctor.isAnnotationPresent(ParseMethod.class))
                return MethodHandles.lookup()
                        .in(type)
                        .unreflectConstructor(ctor)
                        .asType(MethodType.methodType(Object.class, String.class));
        } catch (NoSuchMethodException | IllegalAccessException e) {
            // NOTE: intentionally left blank
        }
        return null;
    }

    private static MethodHandle tryGetAnnotatedFactoryMethodHandle(Class<?> type){
        try {
            Method parseMethod = null;
            for(Method method :type.getDeclaredMethods()){
                if( method.isAnnotationPresent(ParseMethod.class) &&
                    (method.getModifiers() & Modifier.STATIC) == Modifier.STATIC &&
                    type.isAssignableFrom(method.getReturnType()) &&
                    Arrays.equals(method.getParameterTypes(), PARSE_METHOD_PARAMETERS)){
                    parseMethod = method;
                    break;//TODO detect duplication
                }
            }
            if(parseMethod != null)
                return MethodHandles.lookup()
                        .in(type)
                        .unreflect(parseMethod)
                        .asType(MethodType.methodType(Object.class, String.class));
        } catch (IllegalAccessException e) {
            // NOTE: intentionally left blank
        }
        return null;
    }




    @SuppressWarnings({ "unchecked" })
    public static <T> StringParser<T> getParser(Class<T> resultClass){
        StringParser<T> parser = (StringParser<T>) parserMap.get(resultClass);
        if(parser != null)
            return parser;
        // implicitly register enum types
        if(resultClass.isEnum())
            return registerEnumType((Class)resultClass);
        else
            return registerAnnotatedParsableType(resultClass);
    }

    public static <T> T parse(Class<T> resultClass, String string) {
        return parse(resultClass, string, null);
    }

    public static <T> T parse(Class<T> resultClass, String string, T defaultValue){
        if(string == null)
            return defaultValue;

        StringParser<T> parser = getParser(resultClass);
        if(parser == null)
            throw new ClassCastException();

        return parser.parse(string);
    }

    static{
        registerType(String.class, (String value) -> value);

        registerType(Boolean.class, BooleanUtils::getBoolean);
        registerType(Boolean.TYPE, BooleanUtils::getBoolean);

        registerType(Byte.class, Byte::valueOf);
        registerType(Byte.TYPE, Byte::valueOf);

        registerType(Short.class, Short::valueOf);
        registerType(Short.TYPE, Short::valueOf);

        registerType(Integer.class, Integer::valueOf);
        registerType(Integer.TYPE, Integer::valueOf);

        registerType(Long.class, Long::valueOf);
        registerType(Long.TYPE, Long::valueOf);

        registerType(UUID.class, UUID::fromString);
    }

}


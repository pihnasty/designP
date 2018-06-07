package com.aws.util;

import com.amazon.sct.util.StringUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO class description
 */
public final class ValidationUtils {
    public static final Pattern VALID_IDENTIFIER_PATTERN = Pattern.compile("^[A-Za-z_$][A-Za-z_0-9\\-$]*$");

    public static int requireNonNegative(int value){
        if(value < 0)
            throw new IllegalArgumentException("Value must not be negative.");
        return value;
    }

    public static long requireNonNegative(long value){
        if(value < 0)
            throw new IllegalArgumentException("Value must not be negative.");
        return value;
    }

    public static int requirePositive(int value){
        if(value <= 0)
            throw new IllegalArgumentException("Value must be positive number.");
        return value;
    }

    public static long requirePositive(long value){
        if(value <= 0)
            throw new IllegalArgumentException("Value must be positive number.");
        return value;
    }

    public static int requireInRange(int value, int minValue, int maxValue){
        if(value < minValue || value > maxValue)
            throw new IllegalArgumentException(String.format("Value must be in range %1$d..%2$d", minValue, maxValue));
        return value;
    }

    public static long requireInRange(long value, long minValue, long maxValue){
        if(value < minValue || value > maxValue)
            throw new IllegalArgumentException(String.format("Value must be in range %1$d..%2$d", minValue, maxValue));
        return value;
    }

    public static String requireNonEmpty(final String string){
        if(StringUtils.isNullOrEmpty(string))
            throw new IllegalArgumentException("Value must be non-empty string.");
        return string;
    }

    public static String requireNonBlank(final String string){
        if(StringUtils.isNullOrWhiteSpace(string))
            throw new IllegalArgumentException("Value must be non-blank string.");
        return string;
    }

    public static String requireValidIdentifier(final String name){
        if(name == null)
            throw new NullPointerException();

        // TODO deduplicate (sct-common-tree:AbstractNodeMeta.ctor())
        Matcher m = VALID_IDENTIFIER_PATTERN.matcher(name);
        if (!m.matches()) {
            throw new IllegalArgumentException();
        }

        return name;
    }
}

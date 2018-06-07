package com.aws.util;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class StringUtils {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private static final Pattern LINE_BREAK_PATTERN = Pattern.compile("\\r\\n?|\\n");
    private static final Pattern LINE_BREAK_WITH_WHITESPACES_PATTERN = Pattern.compile("\\s*(?:\\r\\n?|\\n)\\s*");
    private static final String LINE_BREAK_REPLACEMENT = " ";

    public static boolean isNullOrEmpty(String string){
        return string == null || string.length() == 0;
    }

    public static boolean isNullOrWhiteSpace(String string){//TODO[AP] to be replaced by lang3.StringUtils direct call
        return org.apache.commons.lang3.StringUtils.isBlank(string);
    }

    /**
     * Creates the string containing a specified character repeated a specified number of times.
     * @param ch filling char
     * @param count repeat count
     * @return the created string
     */
    public static String repeat(char ch, int count) {
        char[] chars = new char[count];
        Arrays.fill(chars, ch);
        return new String(chars);
    }

    /**
     * Creates the string containing a specified character repeated a specified number of times.
     * @param str string to repeat
     * @param count repeat count
     * @return  the created string
     */
    public static String repeat(String str, int count){
        int sourceLength = str.length();
        char[] sourceChars = str.toCharArray();
        char[] targetChars = new char[sourceLength*count];
        for(int i = 0; i<count;++i) {
            System.arraycopy(sourceChars, 0, targetChars, i*sourceLength, sourceLength);
        }
        return new String(targetChars);
    }

    public static String join(Iterable<?> s, String delimiter) {
        StringBuilder builder = new StringBuilder();
        Iterator<?> iter = s.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            if (!iter.hasNext()) {
                break;
            }
            builder.append(delimiter);
        }
        return builder.toString();
    }

    public static String join(Iterable<?> s, String prefix, String delimiter, String postfix) {
        StringBuilder builder = new StringBuilder();
        Iterator<?> iter = s.iterator();
        boolean isFirstEntry = true;
        while (iter.hasNext()) {
            if (isFirstEntry) {
                builder.append(prefix);
                isFirstEntry = false;
            }
            else builder.append(delimiter);
            builder.append(iter.next());
        }
        if(!isFirstEntry/* there was at least one element in iterable */)
            builder.append(postfix);

        return builder.toString();
    }


    public static <E> String join(Iterable<E> s, Function<E, String> mapper, String delimiter) {
        StringBuilder builder = new StringBuilder();
        Iterator<E> iter = s.iterator();
        while (iter.hasNext()) {
            builder.append(mapper.apply(iter.next()));
            if (!iter.hasNext()) {
                break;
            }
            builder.append(delimiter);
        }
        return builder.toString();
    }



    // region Line operations

    public static String toSingleLine(String multiLine){
        return LINE_BREAK_WITH_WHITESPACES_PATTERN.matcher(multiLine).replaceAll(LINE_BREAK_REPLACEMENT);
    }

    public static String toSingleLine(String multiLine, int maxLength) {
        final CharSequence trimmedMultiLine = multiLine.length() <= maxLength
                                              ? multiLine
                                              : new StringSegment(multiLine, 0, maxLength);

        return LINE_BREAK_WITH_WHITESPACES_PATTERN.matcher(trimmedMultiLine).replaceAll(LINE_BREAK_REPLACEMENT);
    }


    public static String[] splitLines(String text){
        return LINE_BREAK_PATTERN.split(text);
    }

    // endregion

    // region IO Streams

    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static XMLStreamReader createXMLReader(final String xmlContent)
    {
        try {
            final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            final StringReader reader = new StringReader(xmlContent);
            return inputFactory.createXMLStreamReader(reader);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // endregion

    // region working with whitespaces

    /**
     * Creates trimmed string from right
     * @param str string to be trimmed
     * @return
     */
    public static String rTrim(String str) {
        int i = str.length()-1;
        while (i >= 0 && Character.isWhitespace(str.charAt(i))) {
            i--;
        }
        return str.substring(0, i + 1);
    }

    public static String lTrim(String str) {
        int i = 0;
        while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        return str.substring(i);
    }

    public static String trim(String str){
        int start = 0;
        int stop = str.length();

        while (start < stop && Character.isWhitespace(str.charAt(start))) {
            start++;
        }

        if(start == stop)
            return "";

        do{
            stop--;
        } while(start < stop && Character.isWhitespace(str.charAt(stop)));

        return str.substring(start, stop + 1);
    }

    public static String smartConcatWs(String a, String b){
        if(a.isEmpty())
            return b;

        if(b.isEmpty())
            return a;

        boolean alreadyHasSpace =
                Character.isWhitespace(b.charAt(0)) ||
                        Character.isWhitespace(a.charAt(a.length() - 1));

        if(alreadyHasSpace)
            return a.concat(b);
        else
            return a.concat(" ").concat(b);
    }

    public static String smartConcatWs(String... words){
        int bufferLength = 0;
        for(String word : words)
            bufferLength += word.length();

        if(bufferLength == 0)
            return "";
        else
            bufferLength += words.length - 1;

        StringBuilder builder = new StringBuilder(bufferLength);
        boolean lastWasNonSpace = false;
        for(String word : words){
            if(word.isEmpty())
                continue;
            if(lastWasNonSpace && !Character.isWhitespace(word.charAt(0)))
                builder.append(' ');
            builder.append(word);
            lastWasNonSpace = !Character.isWhitespace(word.charAt(word.length() - 1));
        }

        return builder.toString();
    }

    // endregion

    public static String capitalize(String value) {
        return value == null ? value : Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    public static String requireNonEmpty(String value) {
        if(value == null || value.isEmpty())
            throw new IllegalArgumentException("String must not be null or empty.");
        return value;
    }

    public static String evaluatePattern(Pattern pattern, String text, Function<MatchResult, String> matchEvaluator){
        StringBuffer result = new StringBuffer();
        evaluatePattern(pattern, text, matchEvaluator, result);
        return result.toString();
    }

    public static void evaluatePattern(
            Pattern pattern, String text, Function<MatchResult, String> matchEvaluator, StringBuffer result){
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String replacement = matchEvaluator.apply(matcher);
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);
    }

    public static boolean isNumber(String text){
        return NUMBER_PATTERN.matcher(text).matches();
    }

    private static class StringSegment implements CharSequence{
        private final String original;
        private final int start;
        private final int length;

        public StringSegment(String original, int start, int length) {
            this.original = original;
            this.start = start;
            this.length = length;
        }

        @Override public int length() {
            return length;
        }

        @Override public char charAt(int index) {
            return original.charAt(start + index);
        }

        @Override public CharSequence subSequence(int start, int end) {
            return new StringSegment(original, this.start + start, end - start);
        }
    }
}

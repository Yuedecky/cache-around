package com.broad.data.string;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StringOperation {

    public static void main(String[] args) {


        commonPrefix("hello,yue", "hello,yyz");

        System.out.println(paddingEndStr("uuu", 8));

        System.out.println(padStartStr("777", 6));

        splitter("hello word,世,接");

        joinnerList();

    }

    /**
     * 获得两个字符串的共同前缀
     *
     * @param a
     * @param b
     */
    private static void commonPrefix(String a, String b) {
        String ourCommonPrefix = Strings.commonPrefix(a, b);
        System.out.println(a + "and " + b + " common prefix:" + ourCommonPrefix);
    }


    /**
     * 判断字符串是否为空或者null
     */
    private static void isNullOrEmprty() {
        String input = "";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("input " + (isNullOrEmpty ? "is" : "is not") + "null or empty");
    }

    private static String paddingEndStr(String source, int minLen) {
        return Strings.padEnd(source, minLen, '0');
    }

    private static String padStartStr(String source, int minLen) {
        return Strings.padStart(source, minLen, 'c');
    }

    /**
     * 拆分字符串
     *
     * @param source
     */
    private static void splitter(String source) {
        Iterable<String> splitResults = Splitter.onPattern("[,]{1,}").trimResults().omitEmptyStrings().split(source);

        for (String item : splitResults) {
            System.out.println(item);
        }
    }

    /**
     * 俄二次拆分： a=b;c=d;e=f
     *
     * @param source
     */
    private static void splitSecond(String source) {
        Map<String, String> splitMap = Splitter.onPattern("[,;]{1,}").withKeyValueSeparator("=").split(source);

        for (Map.Entry<String, String> entry : splitMap.entrySet()) {

            System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
    }

    private static void joinStr(String source, String another) {
        String joinResult = Joiner.on(" ").join(new String[]{"hello", "world"});

        System.out.println(joinResult);
    }

    private static void joinnerList() {
        List<String> stringList = Arrays.asList(
                "Google", "Guava", "Java", "Scala", "Kafka"
        );
        List<String> stringListWithNullValues = Arrays.asList(
                "Google", "Guava", "Scala", null
        );

        String result = Joiner.on("#").join(stringList);

        System.out.println(result);

        String result2 = Joiner.on("#").skipNulls().join(stringListWithNullValues);

        System.out.println(result2);

        String result3 = Joiner.on("#").useForNull(" ").join(stringListWithNullValues);
        System.out.println(result3);

        StringBuilder builder = new StringBuilder();
        StringBuilder resultBuilder = Joiner.on("#").useForNull("NULL").appendTo(builder, stringListWithNullValues);

        System.out.println(resultBuilder.toString());
    }
}

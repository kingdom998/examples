package com.kingdom.test;

import java.util.Locale;

public class TString {
    public static void main(String args[]) {
        String str = "thiss is Java";
        System.out.println(removeCharAt(str, 3));

        TString option = new TString();
        TString.StringFormat();
        option.StringCompareEmp();
    }

    /**
     * 删除字符串中的一个字符
     *
     * @param s   字符串
     * @param pos 位置
     * @return 删除后的字符串
     */
    private static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    /**
     * 字符串比较
     */
    private void StringCompareEmp() {
        String str = "Hello World";
        String anotherString = "hello world";

        System.out.println(str.compareTo(anotherString));
        System.out.println(str.compareToIgnoreCase(anotherString));  //忽略大小写
        System.out.println(str.compareTo(((Object) str).toString()));
    }

    /**
     * 查找字符串最后一次出现的位置
     */
    public static void SearchlastString() {
        String strOrig = "Hello world ,Hello Runoob";
        int lastIndex = strOrig.lastIndexOf("Runoob");
        if (lastIndex == -1) {
            System.out.println("没有找到字符串 Runoob");
        } else {
            System.out.println("Runoob 字符串最后出现的位置： " + lastIndex);
        }
    }

    /**
     * 字符串替换
     */
    public static void StringReplaceEmp() {
        String str = "Hello World";
        System.out.println(str.replace('H', 'W'));
        System.out.println(str.replaceFirst("He", "Wa"));
        System.out.println(str.replaceAll("He", "Ha"));
    }

    /**
     * 字符串反转
     */
    public static void StringReverseExample() {
        String string = "runoob";
        String reverse = new StringBuffer(string).reverse().toString();
        System.out.println("字符串反转前:" + string);
        System.out.println("字符串反转后:" + reverse);
    }


    /**
     * 字符串搜索
     */
    public static void SearchStringEmp() {
        String strOrig = "Google Runoob Taobao";
        int intIndex = strOrig.indexOf("Runoob");
        if (intIndex == -1) {
            System.out.println("没有找到字符串 Runoob");
        } else {
            System.out.println("Runoob 字符串位置 " + intIndex);
        }
    }

    /**
     * 字符串分割
     */
    public static void JavaStringSplitEmp() {

        String str = "www-runoob-com";
        String[] temp;
        String delimeter = "-";  // 指定分割字符
        temp = str.split(delimeter); // 分割字符串
        // 普通 for 循环
        for (String aTemp : temp) {
            System.out.println(aTemp);
            System.out.println("");
        }

        System.out.println("------java for each循环输出的方法-----");
        String str1 = "www.runoob.com";
        String[] temp1;
        String delimeter1 = "\\.";  // 指定分割字符， . 号需要转义
        temp1 = str1.split(delimeter1); // 分割字符串
        for (String x : temp1) {
            System.out.println(x);
            System.out.println("");
        }
    }

    /**
     * 字符串小写转大写
     */
    public static void StringToUpperCaseEmp() {
        String str = "string runoob";
        String strUpper = str.toUpperCase();
        System.out.println("原始字符串: " + str);
        System.out.println("转换为大写: " + strUpper);

    }

    /**
     * 测试两个字符串区域是否相等
     */
    public static void StringRegionMatch() {
        String first_str = "Welcome to Microsoft";
        String second_str = "I work with microsoft";
        boolean match1 = first_str.
                regionMatches(11, second_str, 12, 9);
        boolean match2 = first_str.
                regionMatches(true, 11, second_str, 12, 9); //第一个参数 true 表示忽略大小写区别
        System.out.println("区分大小写返回值：" + match1);
        System.out.println("不区分大小写返回值：" + match2);
    }


    /**
     * 字符串性能比较测试
     */
    public static void StringComparePerformance() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            String s1 = "hello";
            String s2 = "hello";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("通过 String 关键词创建字符串"
                + " : " + (endTime - startTime)
                + " 毫秒");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            new String("hello");
            new String("hello");
        }
        endTime = System.currentTimeMillis();
        System.out.println("通过 String 对象创建字符串"
                + " : " + (endTime - startTime)
                + " 毫秒");
    }


    /**
     * 字符串优化
     */
    public static void StringOptimization() {
        String variables[] = new String[50000];
        for (int i = 0; i < 50000; i++) {
            variables[i] = "s" + i;
        }
        long startTime0 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            variables[i] = "hello";
        }
        long endTime0 = System.currentTimeMillis();
        System.out.println("直接使用字符串： " + (endTime0 - startTime0) + " ms");
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            variables[i] = new String("hello");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用 new 关键字：" + (endTime1 - startTime1) + " ms");
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            variables[i] = new String("hello");
            variables[i] = variables[i].intern();
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("使用字符串对象的 intern() 方法: "
                + (endTime2 - startTime2)
                + " ms");
    }


    /**
     * 字符串格式化
     */
    private static void StringFormat() {
        double e = Math.E;
        System.out.format("%f%n", e);
        System.out.format(Locale.CHINA, "%-10.4f%n%n", e);  //指定本地为中国（CHINA）
    }


    /**
     * 连接字符串
     */
    private static void StringConcatenate() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            String result = "This is"
                    + "testing the"
                    + "difference" + "between"
                    + "String" + "and" + "StringBuffer";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("字符串连接"
                + " - 使用 + 操作符 : "
                + (endTime - startTime) + " ms");
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            StringBuilder result = new StringBuilder();
            result.append("This is");
            result.append("testing the");
            result.append("difference");
            result.append("between");
            result.append("String");
            result.append("and");
            result.append("StringBuffer");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("字符串连接"
                + " - 使用 StringBuffer : "
                + (endTime1 - startTime1) + " ms");
    }
}
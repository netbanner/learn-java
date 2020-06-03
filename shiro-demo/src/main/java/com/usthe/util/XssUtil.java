package com.usthe.util;

import java.util.regex.Pattern;

public class XssUtil {

    private static final String STR_SCRIPT1 = "<script>(.*?)</script>";
    private static final String STR_SCRIPT2 = "</script>";
    private static final String STR_SCRIPT3 = "<script(.*?)>";
    private static final String STR_EVAL = "eval\\((.*?)\\)";
    private static final String STR_EXP = "expression\\((.*?)\\)";
    private static final String STR_JS = "javascript:";
    private static final String STR_VB = "vbscript:";
    private static final String STR_ON = "onload(.*?)=";

    /**
     * description 过滤XSS脚本内容
     *
     * @param value 1
     * @return java.lang.String
     */
    public static String stripXSS(String value) {
        String rlt = null;

        if (null != value) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.

            rlt = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile(STR_SCRIPT1, Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Remove any lonesome </script> tag
            rlt = getString(rlt, STR_SCRIPT2, Pattern.CASE_INSENSITIVE);
            rlt = getString(rlt, STR_SCRIPT3, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);

            // Avoid eval(...) expressions
            rlt = getString(rlt, STR_EVAL, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);

            // Avoid expression(...) expressions
            rlt = getString(rlt, STR_EXP, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);

            // Avoid javascript:... expressions
            rlt = getString(rlt, STR_JS, Pattern.CASE_INSENSITIVE);

            // Avoid vbscript:... expressions
            rlt = getString(rlt, STR_VB, Pattern.CASE_INSENSITIVE);

            // Avoid onload= expressions
            rlt = getString(rlt, STR_ON, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
        }

        return rlt;
    }

    private static String getString(String rlt, String strScript3, int i) {
        Pattern scriptPattern;// Remove any lonesome <script ...> tag
        scriptPattern = Pattern.compile(strScript3, i);
        rlt = scriptPattern.matcher(rlt).replaceAll("");
        return rlt;
    }

    /**
     * description 过滤SQL注入内容
     *
     * @param value 1
     * @return java.lang.String
     */
    public static String stripSqlInjection(String value) {
        return (null == value) ? null : value.replaceAll("('.+--)|(--)|(%7C)", "");
    }

    /**
     * description 过滤SQL 和 XSS注入内容
     *
     * @param value 1
     * @return java.lang.String
     */
    public static String stripSqlXss(String value) {
        return stripXSS(stripSqlInjection(value));
    }
}

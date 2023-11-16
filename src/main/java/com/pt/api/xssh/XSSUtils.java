package com.pt.api.xssh;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * xss脚本替换工具类
 */
public class XSSUtils {
    private static final Pattern[] PATTERNS = {
            // Avoid anything in a <script> type of expression
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            // Avoid anything in a src='...' type of expression
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // Remove any lonesome </script> tag
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
            // Avoid anything in a <iframe> type of expression
            Pattern.compile("<iframe>(.*?)</iframe>", Pattern.CASE_INSENSITIVE),
            // Remove any lonesome <script ...> tag
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // Remove any lonesome <img ...> tag
            Pattern.compile("<img(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // Avoid eval(...) expressions
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // Avoid expression(...) expressions
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // Avoid javascript:... expressions
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            // Avoid vbscript:... expressions
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            //sql注入
            Pattern.compile("(?i)(select|drop|insert|update|delete|create|alter|truncate|rename|desc|show|where).*?", Pattern.CASE_INSENSITIVE),
            // Avoid onload= expressions
            Pattern.compile("on(load|error|mouseover|submit|reset|focus|click)(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
    };

    public static String stripXSS(String value) {
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        for (Pattern scriptPattern : PATTERNS) {
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }
    private static final String[] INVALID_TAGS = {"script", "iframe"};
    private static final String[] INVALID_SQL = {"select ", "insert ", "delete ", "update ", "--"};
    public static void main(String[] args) {
        String input = "<sCript>hello delete SeleCT * where 1=1</scrIPt>";
        input = input.trim();
        String[] tags = INVALID_TAGS;
        for (String tag : tags) {
            input = input.replaceAll("<(?i)" + tag + "[^<>]*>", "");
            input = input.replaceAll("</(?i)" + tag + ">", "");
        }
        String[] sqls = INVALID_SQL;
        for (String sql : sqls) {
            input = input.replaceAll("(?i)"+sql, "");
            /*if (input.contains(sql)) {
                input = input.substring(0, input.indexOf(sql));
            }*/
        }
        System.out.println(input);
        System.out.println(stripXSS("DelEte * where 1=1"));
    }
}

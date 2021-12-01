package com.upload;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author �����
 */
public class XssUtil {
    private static Pattern[] patterns = new Pattern[]{
            // Script fragments
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            // src='...'
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // lonely script tags
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // eval(...)
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // expression(...)
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // javascript:...
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            // vbscript:...
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            // �ո�Ӣ�ĵ�˫����
            Pattern.compile("[\\s\'\"]+", Pattern.CASE_INSENSITIVE),
            // onload(...)=...
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // alert
            Pattern.compile("alert(.*?)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("<", Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile(">", Pattern.MULTILINE | Pattern.DOTALL),
            //Checks any html tags i.e. <script, <embed, <object etc.
            Pattern.compile("(<(script|iframe|embed|frame|frameset|object|img|applet|body|html|style|layer|link|ilayer|meta|bgsound))")
    };

    /**
     * xss�滻����
     *
     * @param value ��Ҫ�滻���ַ�
     * @return �滻����ַ�
     */
    public static String stripXSS(String value) {
        if (value != null) {
            // TODO ESAPI library
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("\0", "");

            // Remove all sections that match a pattern
            for (Pattern scriptPattern : patterns) {
                value = scriptPattern.matcher(value).replaceAll("");
            }
        }
        return value;
    }

    /**
     * xssУ�麯��
     *
     * @param value ��ҪУ����ַ�
     * @return ����ֵ��true ��ʾ����xss©����false��������
     */
    public static boolean checkIsXSS(String value) {
        boolean isXss = false;
        if (value != null) {
            for (Pattern scriptPattern : patterns) {
                Matcher matcher = scriptPattern.matcher(value);
                if (matcher.find()) {
                    isXss = true;
                    break;
                }
            }
        }
        return isXss;
    }

    public static void main(String[] args) {
        String str = "���������ַ�";
        boolean result = XssUtil.checkIsXSS(str);
        System.out.println(str + " �Ƿ����XSS�ַ���" + result);

        String str2 = "����xss�ַ�\'\"<script>alert(111111)</script>";
        boolean result2 = XssUtil.checkIsXSS(str2);
        System.out.println(str2 + " �Ƿ����XSS�ַ���" + result2);
    }
}
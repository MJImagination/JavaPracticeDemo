package util;

public class StringUtils {
    public StringUtils() {
    }

    public static boolean isNullOrEmpty(String src) {
        return null == src || "".equals(src.trim());
    }

    public static String convertNull(String str) {
        return str == null ? "" : str;
    }

    public static String convertTextareaToHtml(String text) {
        return text != null ? text.replaceAll("\r\n", "<br/>").replaceAll(" ", "&nbsp;") : null;
    }

    public static String convertTextareaToText(String text) {
        return text != null ? text.replaceAll("\r\n", "").replaceAll(" ", "") : null;
    }

    public static boolean isNumberString(String strIn) {
        return isNumberString(strIn, "0123456789");
    }

    public static boolean isNumberString(String strIn, String strRef) {
        if (strIn != null && strIn.length() != 0) {
            for(int i = 0; i < strIn.length(); ++i) {
                String strTmp = strIn.substring(i, i + 1);
                if (strRef.indexOf(strTmp, 0) == -1) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static String getCleanString(String body) {
        body = body.replaceAll("&[nN][bB][sS][pP];", " ");
        body = body.replaceAll("<[bB][rR]\\s*>", "\r\n");
        body = body.replaceAll("<[pP]\\s*>", "\r\n\r\n");
        body = body.replaceAll("<.+?>", "");
        return body;
    }

    public static String createSummary(String content, int length) {
        if (content == null) {
            return null;
        } else {
            content = getCleanString(content);
            int len = content.length();
            return len > length ? content.substring(0, length) : content;
        }
    }

    public static String getLmtStr(String value, int len) {
        return value != null && value.length() > len ? value.substring(0, len) : value;
    }

    public static String getLmtString(String value, int len) {
        return value != null && value.length() > len ? value.substring(0, len) + "..." : value;
    }

    public static String getLmtStrx(String value, int len) {
        if (value != null && value.length() > len) {
            value = value.substring(0, len / 2) + ".." + value.substring(value.length() - len / 2);
            return value;
        } else {
            return value;
        }
    }

    public String getZeroStr(String str, int len) {
        int strlen = str.length();

        for(int i = 0; i < len - strlen; ++i) {
            str = "0" + str;
        }

        return str;
    }
}
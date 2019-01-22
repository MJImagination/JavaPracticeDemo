package 字符串.正则;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/12/26
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println("３５");
        System.out.println(ToDBC("南苑新村西10幢１单元５０３室"));

        String s = "杭州20170222ax";
        //匹配数字，最小长度1，最大长度3（看下标）
        Pattern p = Pattern.compile("([0-9].{1,3})");
        Matcher m = p.matcher(s);
        m.find();
        System.out.println(m.group(1));



        //输出数字第一个出现的地方
        String string = "南苑小区东17栋2单元302室";
        Matcher matcher = Pattern.compile("[0-9]").matcher(string);
        if (matcher.find()) {
            int index = matcher.start();
            System.out.println(matcher.start());
            System.out.println(string.substring(0, index));
        } else {
            System.out.println("Not found!");
        }

        System.out.println("***" + getStandardAddress("南苑小区东"));
    }


    public static String getStandardAddress(String detailAddress){
        Matcher matcher = Pattern.compile("[0-9]").matcher(detailAddress);
        if (matcher.find()) {
            int index = matcher.start();
            return detailAddress.substring(0, index);
        } else {
            return detailAddress;
        }
    }





    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {


        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);

        return returnString;
    }
}

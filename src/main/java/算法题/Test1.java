package 算法题;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 单词的压缩编码
 * 1、题目描述：　
 * 　　给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 *
 * 　　例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 *
 * 示例：
 *
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 *
 * 链接：https://leetcode-cn.com/problems/short-encoding-of-words
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/5/27
 */
public class Test1 {
    public static void main(String[] args){
        minimumLengthEncoding("time,me,ball".split(","));
    }
    public static int minimumLengthEncoding(String[] words) {

        StringBuffer buffer = new StringBuffer();
//        String s = StringUtils.join(Arrays.asList(words), ",");
        String s = "";
        for (String word : words) {
            s = word +",";
        }
        String[] array = s.split(",");
//            Arrays.sort(array);
//            Arrays.sort(array, Collections.reverseOrder());
        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() != o2.length() ? Integer.compare(o2.length(),o1.length()):o1.compareTo(o2);
            }
        });
        String[] same = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            if (s.indexOf(array[i]) != s.lastIndexOf(array[i])) {
                if (hasSame(array[i], array) && !hasSame2(array[i], same)) {
                    buffer.append(array[i]);
                    same[same.length-1] = array[i];
                }
                if (!buffer.toString().endsWith("#")) {
                    buffer.append("#");
                }
            } else {
                buffer.append(array[i]).append("#");
            }

        }
        if (!buffer.toString().endsWith("#")) {
            buffer.append("#");
        }
        System.out.println(buffer.length());
        return  buffer.length();
    }
    public static boolean hasSame2(String str, String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (str.equals(arr[i])) {
                return true;
            }
        }
        return false;

    }

    public static boolean hasSame(String str, String[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (str.equals(arr[i])) {
                count ++;
            }
        }
        if(count>1){
            return true;
        }else{
            return false;
        }

    }
}

package 牛客网;

import org.junit.Test;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/5/22
 */
public class javaDemo1 {

    /**
     * 换汽水
     * 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”
     * 答案是5瓶，方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，
     * 这时候剩2个空瓶子。然后你让老板先借给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。
     * 如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
     */
    @Test
    public void test1() {
        int a = 310810;

        System.out.println(count(a, 0));
        System.out.println(bottle(a));
    }

    public static int count(int i, int sum) {
        if (i > 2) {
            int front = i / 3;
            int behind = i % 3;
            i = front + behind;
            sum = sum + front;
            return count(i, sum);
        } else if (i == 2) {
            return sum + 1;
        } else {
            return sum;
        }
    }


    public static int bottle(int n) {
        int count = 0;
        if (n > 0) {
            while (n > 1) {
                count += n / 3;
                n = n % 3 + n / 3;
                if (n == 2) {
                    count++;
                    n = 0;
                }
            }
        }
        return count;
    }

}

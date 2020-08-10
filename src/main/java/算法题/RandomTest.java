package 算法题;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/6/30
 */
public class RandomTest {
    public static void main(String[] args){
        testRandom();
    }
    private static void testRandom() {
        int value = 10000000;
        int count = 0;
        //int类型最大值：2的32次方 - 1 = Integer.MAX_VALUE = 2147483647，二十亿多,真够啦 。
        Set<Integer> result = new HashSet<>(value);
        Random random = new Random();
        long a = System.currentTimeMillis();
        while (result.size() < value + 1) {
            int i = random.nextInt(value + 1);
            result.add(i);
            count ++ ;
        }
        System.out.println("\r<br> 执行耗时 : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
        System.out.println("完了，集合大小为" + result.size());
        System.out.println(count);


    }
}

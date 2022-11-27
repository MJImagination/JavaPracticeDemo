package 递归;

import cn.hutool.core.exceptions.ValidateException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/15
 */
public class Demo2 {
    public static Map<Integer, Long> node = new HashMap<Integer, Long>();

    @Test
    //菲波那切数列
    public void fiboTest() {
        long startTime = System.currentTimeMillis();
        System.out.println(fibo(39));
        System.out.println(System.currentTimeMillis() - startTime);
        Integer.valueOf(1);

        /*优化后的*/
        long startTime2 = System.currentTimeMillis();
        System.out.println(fibo2(150));
        System.out.println(System.currentTimeMillis() - startTime2);



    }

    public static long fibo(int num) {
        if (num == 0) {
            throw new ValidateException("不合法的数字");
        }
        if (num == 1 || num == 2) {
            return 1;
        }
        return fibo(num - 1) + fibo(num - 2);
    }

    public static long fibo2(int num) {
        if (num == 0) {
            throw new ValidateException("不合法的数字");
        }
        if (num == 1 || num == 2) {
            return 1;
        }
        if (node.containsKey(num)) {
            return node.get(num);
        } else {
            long result = fibo2(num - 1) + fibo2(num - 2);
            node.put(num, result);
            return result;
        }
    }
}

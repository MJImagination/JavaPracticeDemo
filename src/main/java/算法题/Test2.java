package 算法题;

import java.util.HashMap;
import java.util.Map;

/**
 * https://juejin.im/post/5a29d52cf265da43333e4da7
 *
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/5/28
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(f(10, new HashMap<>()));
        System.out.println(f2(10));
    }

    public static int f(int i, Map<Integer, Integer> map) {
        if (i < 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        if (map.containsKey(i)) {
            return map.get(i);
        } else {
            int value = f(i - 1, map) + f(i - 2, map);
            map.put(i, value);
            return value;
        }
//        return f(i - 1) + f(i - 2);
    }

    public static int f2(int i) {
        if (i < 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int k = 3; k <= i; k++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }
}

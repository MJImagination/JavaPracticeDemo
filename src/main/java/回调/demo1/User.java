package 回调.demo1;


import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 *
 *  14:34:09
 * 回调：https://zhuanlan.zhihu.com/p/30052334
 *
 *  15:11:34
 * https://www.zhihu.com/search?type=content&q=java%E5%9B%9E%E8%B0%83
 *
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/12/23
 */
public class User implements CallBackInterface {
    @Override
    public T run(List<Object> params) {
        return null;
    }

    public static void main(String[] args) {
        new Hotel().serviceWakeUp("7:00", new CallBackInterface() {
            @Override
            public T run(List<Object> params) {
                System.out.println("被叫醒了");
                return null;
            }
        });
    }
}

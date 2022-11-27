package 多线程.简单示例;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Description:  https://www.cnblogs.com/z-qinfeng/p/12032455.html
 * https://www.cnblogs.com/baizhanshi/p/6425209.html
 * @Author: MJ
 * @Date: Created in 2021/4/20
 */
public class CallAbleTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Task task = new CallAbleTest().new Task();
        FutureTask<Long> result1 = new FutureTask<>(task);
        FutureTask<Long> result2 = new FutureTask<>(task);
        new Thread(result1).start();
        System.out.println(result1.get());
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(result2).start();
        System.out.println(result2.get());
    }

    class Task implements Callable<Long>{
        @Override
        public Long call() throws Exception {
            long time = System.currentTimeMillis();
            System.out.println(time);
            return time;
        }
    }


}

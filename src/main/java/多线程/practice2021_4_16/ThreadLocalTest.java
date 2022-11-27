package 多线程.practice2021_4_16;

import org.springframework.util.ObjectUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/20
 */
public class ThreadLocalTest {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {
        Task task = new ThreadLocalTest().new Task();
        new Thread(task).start();
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(task).start();


    }


    class Task implements Runnable{
        public Task() {
            super();
        }

        @Override
        public void run() {
            if(ObjectUtils.isEmpty(threadLocal.get())){
                threadLocal.set(System.currentTimeMillis());
            }
            System.out.println(threadLocal.get());
        }
    }


}

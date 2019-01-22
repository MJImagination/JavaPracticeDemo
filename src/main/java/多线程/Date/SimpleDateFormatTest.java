package 多线程.Date;

import java.util.concurrent.CountDownLatch;
/**
 * SimpleDateFormatTest
 * @author xtt
 * @date 2019/1/17 下午1:14
 */
public class SimpleDateFormatTest {
    static int threadCount = 20;
    final static CountDownLatch latch = new CountDownLatch(threadCount);
    public static void main(String[] args) {
        for (int i = 0;i < threadCount; i++){
            new Thread(){
                public void run() {
                    try {
                        System.out.println("子线程 " + Thread.currentThread().getName() + " 正在执行");
                        System.out.println(DateUtil.xttblog("2019-01-17 14:00:00"));
                        System.out.println("子线程 " + Thread.currentThread().getName() + " 执行完毕");
                        latch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        }
    }
}
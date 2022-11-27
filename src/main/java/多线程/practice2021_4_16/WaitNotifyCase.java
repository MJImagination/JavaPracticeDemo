package 多线程.practice2021_4_16;

import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/u013394527/article/details/80560153
 *                  https://blog.csdn.net/weixin_38106322/article/details/104551127
 *                 https://blog.csdn.net/weixin_30482383/article/details/97051835
 *                 https://www.cnblogs.com/moongeek/p/7631447.html
 */
public class WaitNotifyCase {
    public static void main(String[] args) {
        final Object lock = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("线程1开始运行");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("线程1阻塞,阻塞的时候同步代码块没有执行完，再次获得锁后，会继续执行");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程一被唤醒");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }
        }).start();
    }
}
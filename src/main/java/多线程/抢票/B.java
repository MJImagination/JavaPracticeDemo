package 多线程.抢票;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/12/14
 *
 * 解释：
 * 第二种方法为什么不需要static 而第一种方法需要
 * A aa = new A();
 * Thread t = new Thread(aa);
 * 用一个aa对象可以同时构建多个线程，一个对象就默认共享同一个资源，所以就是卖同一张票。
 *
 * 所以相比较而言，最好用第二种方法，创建一个对象就可以同时构建多个线程，并且还共享同一个资源，也节省对象资源
 * ---------------------
 * 作者：qq_34724252
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_34724252/article/details/78034276
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 */
public class B implements Runnable {
    public  int tickets = 100;
    public  String str = new String("哈哈");

    @Override
    public void run() {
        while (true) {
            synchronized (str) {
                if (tickets > 0) {
                    System.out.println("第" + Thread.currentThread().getName() + "个线程卖出了第" + tickets + "张票");
                    --tickets;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        B b = new B();
        Thread thread1 = new Thread(b);
        Thread thread2 = new Thread(b);
        thread1.start();
        thread2.start();
    }
}

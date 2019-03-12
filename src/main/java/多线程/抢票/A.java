package 多线程.抢票;

/**
 * 解释：两个线程两个对象aa1 和 aa2 ，两个.start()方法，开启了两个run（）方法，所以如果想让它卖一种票就对tickets进行静态处理，如果还想让两个线程互斥，则要统一str，因为是两个对象锁定各自的str，要对其进行静态处理，因为Synchronized( str ){ }该方法。
 * ---------------------
 * 作者：qq_34724252
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_34724252/article/details/78034276
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 * <p>
 * 多次启动cpu占用率 100%
 */
public class A extends Thread {
    public static int tickets = 100;
    public static String str = new String("哈哈");

    @Override
    public void run() {

        synchronized (str) {
            while (true) {
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
        A aa1 = new A();
        aa1.start();

        A aa2 = new A();
        aa2.start();
    }
}




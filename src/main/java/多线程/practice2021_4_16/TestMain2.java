package 多线程.practice2021_4_16;

/**
 * https://www.cnblogs.com/moongeek/p/7631447.html
 */
public class TestMain2 {
    //对象锁一
    private static Object lock1 = new Object();
    //对象锁二
    private static Object lock2 = new Object();

    public static void main(String[] args) throws Exception {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println("线程一拿到了lock1锁");
                    System.out.println("线程一准备获取lock2锁");
                    synchronized (lock2) {
                        System.out.println("线程一拿到了lock2锁");
                        try {
                            System.out.println("线程一释放了lock1锁");
                            //先让出lock1锁，不设置超时时间
                            lock1.wait();
                            //唤醒lock1等待的线程
                            lock1.notify();
                            System.out.println("线程一运行结束");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //睡眠一秒，让线程一能够成功运行到wait()方法，释放lock1锁
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("线程二拿到了lock1锁，开始运行");
                    System.out.println("线程二准备获取lock2锁");
                    //唤醒lock1等待的线程
                    lock1.notify();
                    try {
                        //先让出lock1锁，不设置超时时间
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("线程二拿到了lock2锁，开始运行");
                        System.out.println("线程二运行结束");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
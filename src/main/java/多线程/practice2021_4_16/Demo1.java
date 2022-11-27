package 多线程.practice2021_4_16;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/16
 */
public class Demo1 {
    public static Object a = new Object();
    public static Object b = new Object();
    public static Object c = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        daying();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 轮流打印ABC
     */
    public static void daying() throws InterruptedException {
        //打印A
        synchronized (a) {
            System.out.println(Thread.currentThread());
            System.out.println("a");
            synchronized (b) {
                System.out.println("A");
                b.notifyAll();
            }
            a.wait();
        }

        //打印B
        synchronized (b) {
            System.out.println(Thread.currentThread());
            System.out.println("b");
            synchronized (c) {
                System.out.println("B");
                c.notifyAll();
            }
            b.wait();
        }


        //打印C
        synchronized (c) {
            System.out.println(Thread.currentThread());
            System.out.println("c");
            synchronized (a) {
                System.out.println("C");
                a.notifyAll();
            }
            c.wait();
        }

    }
}




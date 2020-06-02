package 多线程.线程通信;

/**
 * @Description:  打印ABC
 * @Author: MJ
 * @Date: Created in 2020/6/2
 */
public class Demo2 {
    private static volatile int num = 1;

    public static void main(String[] args) {
        new Thread("thread_1") {
            @Override
            public void run() {
                while (true) {
                    while (num == 1) {
                        System.out.println(this.getName());
                        num = 2;
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();


        new Thread("thread_2") {
            @Override
            public void run() {
                while (true) {


                    while (num == 2) {
                        System.out.println(this.getName());
                        num = 3;
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (num == 3) {
                        System.out.println(Thread.currentThread().getName());
                        num = 1;
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "thread_3").start();
    }
}

package 多线程.线程通信;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/6/2
 */
public class Demo1 {
    private static volatile boolean status = true;

    public static void main(String[] args) {
        Thread thread_1 = new Thread("线程_1") {
            @Override
            public void run() {
                int num = 0;
                while (true) {
                    while (!status) {
                        System.out.println(this.getName());
                        num++;
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (num == 1) {
                            status = true;
                            num = 0;
                        }
                    }
                }


            }
        };
        thread_1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int num = 0;
                while (true) {
                    while (status) {
                        System.out.println(Thread.currentThread().getName());
                        num++;
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (num == 1) {
                            status = false;
                            num = 0;
                        }
                    }
                }

            }
        }, "线程_2").start();
    }
}

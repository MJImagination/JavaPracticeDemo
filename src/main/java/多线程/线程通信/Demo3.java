package 多线程.线程通信;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/6/2
 */
public class Demo3 {
    public static void main(String[] args) {

        Thread thread2 = new Thread("thread_2"){
            @Override
            public void run() {
                for(int i = 0;i<3;i++){
                    System.out.println(this.getName());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        new Thread("thread_1"){
            @Override
            public void run() {
                System.out.println(this.getName());
                try {
                    thread2.start();
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName());
            }
        }.start();



    }
}

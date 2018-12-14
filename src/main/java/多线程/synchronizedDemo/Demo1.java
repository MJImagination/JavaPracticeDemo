package 多线程.synchronizedDemo;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/12/14
 *
 * https://www.jianshu.com/p/27f5935cafd8
 */
public class Demo1 implements Runnable{
    //共享资源变量
    int count = 0;

    @Override
    public synchronized void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+":"+count++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Demo1 syncTest1 = new Demo1();
        Demo1 syncTest2 = new Demo1();
        Thread thread1 = new Thread(syncTest1,"thread1");
        Thread thread2 = new Thread(syncTest2, "thread2");
        thread1.start();
        thread2.start();
    }
    /**
     * 输出结果
     thread1:0
     thread2:0
     thread1:1
     thread2:1
     thread1:2
     thread2:2
     thread1:3
     thread2:3
     thread1:4
     thread2:4
     */

    
}

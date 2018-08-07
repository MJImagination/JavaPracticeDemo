package 线程;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/4
 */
public class ThreadDemo extends Thread {
    public String name;

    public ThreadDemo() {
//        super();
    }


    public ThreadDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " 运行 " + "i = " + i);
        }
    }

//    @Test
    public void thread_1() {
        ThreadDemo threadDemoA = new ThreadDemo("线程A");
        ThreadDemo threadDemoB = new ThreadDemo("线程B");
        threadDemoA.start();
        threadDemoB.start();
    }

    public static void main(String[] args) {
        ThreadDemo threadDemoA = new ThreadDemo("线程A");
        ThreadDemo threadDemoB = new ThreadDemo("线程B");
        threadDemoA.start();
        threadDemoB.start();
    }
}

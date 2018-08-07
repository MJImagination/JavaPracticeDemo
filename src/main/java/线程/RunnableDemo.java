package 线程;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/4
 */
public class RunnableDemo implements Runnable {
    private String name;

    public RunnableDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " 运行 " + "i = " + i);
        }
    }

    public static void main(String[] args){
//        RunnableDemo runnableDemo = new RunnableDemo("A");
        Thread thread = new Thread(new RunnableDemo("A"),"fd");
        thread.start();
    }
}

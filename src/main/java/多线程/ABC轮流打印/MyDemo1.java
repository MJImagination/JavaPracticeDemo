package 多线程.ABC轮流打印;


/**
 * @Description:  https://blog.csdn.net/u013394527/article/details/80560153
 *                https://blog.csdn.net/weixin_38106322/article/details/104551127
 *                https://blog.csdn.net/weixin_30482383/article/details/97051835
 * @Author: MJ
 * @Date: Created in 2020/3/31
 */
public class MyDemo1 implements Runnable {
    public Object now;
    public Object after;
    public String name;

    public MyDemo1(String name, Object now, Object after) {
        this.now = now;
        this.after = after;
        this.name = name;
    }

    public void pirntABC() throws InterruptedException {
        int i = 300;
        while (i > 0) {
            synchronized (now) {
                i--;
                System.out.print(name);
                synchronized (after) {
                    after.notify();
                }
                if (i == 0) {
                    System.out.println(Thread.currentThread());
                    System.out.println("打印" + i);
                    now.notify();
                } else {

                    now.wait();
                }
            }

        }
    }


    @Override
    public void run() {
        try {
            pirntABC();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        Thread A = new Thread(new MyDemo1("A", a, b));
        Thread B = new Thread(new MyDemo1("B", b, c));
        Thread C = new Thread(new MyDemo1("C", c, a));
        A.start();
        Thread.sleep(100L);
        B.start();
        Thread.sleep(100L);
        C.start();
    }
}

package 多线程.ABC轮流打印;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/6/2
 */
public class ThreadPrint2 implements Runnable {
    private Object now;
    private Object after;
    private String name;

    public ThreadPrint2(Object now, Object after, String name) {
        this.now = now;
        this.after = after;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            synchronized (now) {
                synchronized (after) {
                    System.out.print(this.name);
//                    after.notify();
//                    /*注释掉只会打印一遍，因为wait会释放锁，但是线程会进入等待阻塞状态（等待队列中），
//                    因此无法进入下次循环，所以 wait() 和notify要一起用*/

                }
                try {
                    now.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

//        for (int i = 0; i < 3; i++) {
//            //A
//            synchronized (now) {
//                synchronized (after) {
//                    System.out.print(name);
//                    after.notify();   //B
//                }
//                try {
//                    now.wait();      //线程A放入a锁
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        new Thread(new ThreadPrint2(a, b, "A")).start();
        Thread.sleep(100L);
        new Thread(new ThreadPrint2(b, c, "B")).start();
        Thread.sleep(100L);
        new Thread(new ThreadPrint2(c, a, "C")).start();

//        Object a = new Object();
//        Object b = new Object();
//        Object c = new Object();
//        ThreadPrint2 threadPrintA = new ThreadPrint2( a, b,"A");
//        ThreadPrint2 threadPrintB = new ThreadPrint2( b, c,"B");
//        ThreadPrint2 threadPrintC = new ThreadPrint2( c, a,"C");
//        Thread threadA = new Thread(threadPrintA);
//        Thread threadB = new Thread(threadPrintB);
//        Thread threadC = new Thread(threadPrintC);
//        threadA.start();
//        threadA.sleep(100);
//        threadB.start();
//        threadB.sleep(100);
//        threadC.start();
    }
}

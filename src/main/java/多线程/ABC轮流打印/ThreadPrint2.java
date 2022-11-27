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
//                    after.notify();  去掉这个只打印一次ABC是因为同步代码块执行完成会自动释放当前锁，当前线程wait释放锁后，进去等待队列，调用notify会进去锁池状态，才能再次获得锁
//                    当方法wait()被执行后，锁自动被释放，但执行完notify()方法后，锁不会自动释放。必须执行完notify()方法所在的synchronized代码块后才释放。


//                    Object.wait() 线程必须先获取到对象监视锁，才能调用此方法。执行此方法线程立即释放对象监视锁，进去等待队列,当前线程阻塞。若方法抛出InterruptedException异常，同样需要获取锁才能继续往下执行



//                    Object.notify() 线程必须先获取到对象监视锁，才能调用此方法。执行此方法会唤起wait的线程，当前线程不会立即获取锁，而是进去锁池状态，直到临界区代码库执行完线程释放锁之后，其他线程才有机会去竞争锁。
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
        for(int i=0;i<100;i++){
            Object a = new Object();
            Object b = new Object();
            Object c = new Object();
            new Thread(new ThreadPrint2(a, b, "A")).start();
        Thread.sleep(100L); //没有睡眠顺序会乱
            new Thread(new ThreadPrint2(b, c, "B")).start();
        Thread.sleep(100L);
            new Thread(new ThreadPrint2(c, a, "C")).start();
        }


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

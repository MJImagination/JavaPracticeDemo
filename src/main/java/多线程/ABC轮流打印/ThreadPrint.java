package 多线程.ABC轮流打印;

public class ThreadPrint implements Runnable {  //A

    private Object after;  // Object b 下一个要执行的对象锁
    private Object now;    //Object a 当前对象锁
    private String name;

    public ThreadPrint(String name, Object now, Object after) {
        this.name = name;
        this.now = now;
        this.after = after;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            //A
            synchronized (now) {
                synchronized (after) {
                    System.out.print(name);
                    after.notify();   //B
                }
                try {
                    now.wait();      //线程A放入a锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrint threadPrintA = new ThreadPrint("A", a, b);
        ThreadPrint threadPrintB = new ThreadPrint("B", b, c);
        ThreadPrint threadPrintC = new ThreadPrint("C", c, a);
        Thread threadA = new Thread(threadPrintA);
        Thread threadB = new Thread(threadPrintB);
        Thread threadC = new Thread(threadPrintC);
        threadA.start();
        threadA.sleep(100);
        threadB.start();
        threadB.sleep(100);
        threadC.start();
    }
}
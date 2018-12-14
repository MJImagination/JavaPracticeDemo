package 多线程.抢票;

class SaleTicket implements Runnable {
    private static int tickets = 100;


    private synchronized void sale() {
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出 第 " + (tickets--) + "张票");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        while (tickets > 0) {
            sale();
        }
    }
}


public class SaleTicketTest {

    public static void main(String[] args) {

        test2();


    }


    public static void test1(){

        SaleTicket st = new SaleTicket();
        Thread t1 = new Thread(st, "一号窗口");
        Thread t2 = new Thread(st, "二号窗口");
        Thread t3 = new Thread(st, "三号窗口");
        Thread t4 = new Thread(st, "四号窗口");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    public static void test2(){

        SaleTicket st = new SaleTicket();
        Thread t1 = new Thread(st, "A一号窗口");
        Thread t2 = new Thread(st, "A二号窗口");

        SaleTicket st2 = new SaleTicket();
        Thread t3 = new Thread(st2, "B一号窗口");
        Thread t4 = new Thread(st2, "B二号窗口");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

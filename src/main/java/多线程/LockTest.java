package 多线程;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        //运用内部类的方法实现runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用out方法中的print方法
                out pOut = new out();
                pOut.print("hello");
            }
        //开启一个线程
        }).start();
    }
}

class out {
    //调用lock的实现类renntrantlocak
    Lock lock = new ReentrantLock();

    public void print(String name) {
        //给当前的name上锁
        lock.lock();
        try {
            //打印name的长度
            for (int i = 0; i < name.length(); i++) {
                System.out.println(name.charAt(i));
            }
            System.out.println();
        } finally {
            //解锁
//            lock.unlock();
        }
    }
}
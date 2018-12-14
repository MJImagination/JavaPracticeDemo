package 多线程.synchronizedDemo;

import java.util.Date;

/**
 * 测试的object类
 *
 * @author:dufy
 * @version:1.0.0
 * @date 2017/9/29
 * @email 742981086@qq.com
 * https://blog.csdn.net/qq287920861/article/details/78166613   有图解释ObjectTest.png
 */
public class ObjectTest {


    public synchronized void methodA() {
        try {
            System.out.println("This is methodA ...." + Thread.currentThread().getName() + ": " + new Date());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void methodB() {

        System.out.println("This is methodB ...." + Thread.currentThread().getName() + ": " + new Date());
    }

    public synchronized void methodC() {

        try {
            System.out.println("This is methodC ...." + Thread.currentThread().getName() + ": " + new Date());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ObjectTest ot = new ObjectTest();
        Thread1 t1 = new Thread1(ot, "thread1");
        Thread2 t2 = new Thread2(ot, "thread2");
        Thread3 t3 = new Thread3(ot, "thread3");
        Thread4 t4 = new Thread4(ot, "thread4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }


    static class Thread1 extends Thread {
        private ObjectTest objectTest;

        public Thread1(ObjectTest objectTest, String name) {
            setName(name);
            this.objectTest = objectTest;
        }

        @Override
        public void run() {
            super.run();
            objectTest.methodA();
        }
    }

    static class Thread2 extends Thread {
        private ObjectTest objectTest;

        public Thread2(ObjectTest objectTest, String name) {
            setName(name);
            this.objectTest = objectTest;
        }

        @Override
        public void run() {
            super.run();
            objectTest.methodB();
        }
    }

    static class Thread3 extends Thread {
        private ObjectTest objectTest;

        public Thread3(ObjectTest objectTest, String name) {
            setName(name);
            this.objectTest = objectTest;
        }

        @Override
        public void run() {
            super.run();
            objectTest.methodA();
        }
    }

    static class Thread4 extends Thread {
        private ObjectTest objectTest;

        public Thread4(ObjectTest objectTest, String name) {
            setName(name);
            this.objectTest = objectTest;
        }

        @Override
        public void run() {
            super.run();
            objectTest.methodC();
        }
    }
}
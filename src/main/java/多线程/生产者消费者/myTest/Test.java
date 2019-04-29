package 多线程.生产者消费者.myTest;

import 线程.ThreadPoolUtil;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/4/29
 */
public class Test {
    public static void main(String[] args){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 8000,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());

        Warehouse warehouse = new Warehouse();
        Producer producer1 = new Producer(warehouse,"生产者1");
        Producer producer2 = new Producer(warehouse,"生产者2");

        Consumer consumer1 = new Consumer(warehouse,"消费者1");
        Consumer consumer2 = new Consumer(warehouse,"消费者2");
        Consumer consumer3 = new Consumer(warehouse,"消费者3");
        Consumer consumer4 = new Consumer(warehouse,"消费者4");
        Consumer consumer5 = new Consumer(warehouse,"消费者5");
        Consumer consumer6 = new Consumer(warehouse,"消费者6");

        executor.submit(producer1);
//        executor.submit(producer2);
        executor.submit(consumer1);
        executor.submit(consumer2);
        executor.submit(consumer3);
        executor.submit(consumer4);
        executor.submit(consumer5);
        executor.submit(consumer6);
    }
}

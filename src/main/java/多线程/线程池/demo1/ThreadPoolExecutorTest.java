package 多线程.线程池.demo1;

import java.util.concurrent.*;

/**
 * 自定义的线程池
 *
 * corePoolSize核心线程1个:
 *      没有任务需要执行的时候线程池的大小，如果任务数比较多会开启其他线程，但是不会超过最大线程数。
 *      在刚刚创建ThreadPoolExecutor的时候，线程并不会立即启动，而是要等到有任务提交时才会启动，
 *      除非调用了prestartCoreThread/prestartAllCoreThreads事先启动核心线程
 *
 * maximumPoolSize最大线程2个:
 *      线程池中允许的最大线程数，线程池中的当前线程数目不会超过该值。如果任务比较多的时候，可以将任务添加到缓冲池中。
 * keepAliveTime存活时间:
 *      120S
 * 缓冲任务3:
 *      超过就会被reject拒绝
 *      这里的超过是指：分配的任务数大于最大线程数和缓冲线程数之和
 *
 *  拒绝任务:
 *      超过指定范围的限制就会拒绝
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(1,//corePoolSize
                                                         2,//maximumPoolSize
                                                        60,//keepAliveTime
                                                        TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),//缓冲池
                                                        new Reject());//拒绝任务类

        Task t1 = new Task("1","任务1");
        Task t2 = new Task("2","任务2");
        Task t3 = new Task("3","任务3");
        Task t4 = new Task("4","任务4");
        Task t5 = new Task("5","任务5");
        Task t6 = new Task("6","任务6");
        Task t7 = new Task("7","任务7");
        Task t8 = new Task("8","任务8");
        Task t9 = new Task("9","任务9");
        Task t10 = new Task("10","任务10");

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        pool.execute(t8);
        pool.execute(t9);
        pool.execute(t10);

        getAliveThread(pool);
    }

    /**
     * 获取线程池的线程数据
     * @param pool
     */
    public static void getAliveThread(ThreadPoolExecutor pool){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        System.out.println("########返回核心线程数:"+pool.getCorePoolSize());
                        System.out.println("########返回正在执行任务的线程的大概数量:"+pool.getActiveCount());
                        System.out.println("########返回池中当前的线程数:"+pool.getPoolSize());
                        System.out.println("########返回计划执行的任务的大概总数:"+pool.getTaskCount());
                        System.out.println("########返回池中当前的线程数:"+pool.getPoolSize());
                        System.out.println("########回完成执行的任务的大致总数:"+pool.getCompletedTaskCount());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

}

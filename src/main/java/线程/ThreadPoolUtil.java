package 线程;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Exrickx
 */
public class ThreadPoolUtil {

    /**
     * 线程缓冲队列
     */
    private static BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(100);
    /**
     * 核心线程数，会一直存活，即使没有任务，线程池也会维护线程的最少数量
     */
    private static final int SIZE_CORE_POOL = 5;
    /**
     * 线程池维护线程的最大数量
     */
    private static final int SIZE_MAX_POOL = 10;
    /**
     * 线程池维护线程所允许的空闲时间
     */
    private static final long ALIVE_TIME = 2000;

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL, ALIVE_TIME, TimeUnit.MILLISECONDS, bqueue, new ThreadPoolExecutor.CallerRunsPolicy());

    static {

        pool.prestartAllCoreThreads();
    }

    public static ThreadPoolExecutor getPool() {
        return pool;
    }

    public static void main(String[] args) {
        System.out.println(pool.getPoolSize());
//        ThreadPoolUtil.getPool().execute(new SaveSystemLogThread(log, logService));
        ThreadPoolUtil.getPool().execute( new SaveSystemLogThread());
        ThreadPoolUtil.getPool().execute( new SaveSystemLogThread());
        ThreadPoolUtil.getPool().execute( new SaveSystemLogThread());
        ThreadPoolUtil.getPool().execute( new SaveSystemLogThread());
        ThreadPoolUtil.getPool().execute( new SaveSystemLogThread());
        ThreadPoolUtil.getPool().execute( new SaveSystemLogThread());
        ThreadPoolUtil.getPool().execute( new SaveSystemLogThread());
        System.out.println(pool.getPoolSize());
    }


    /**
     * 保存日志至数据库
     */
    private static class SaveSystemLogThread implements Runnable {
        /**
         * 原代码
         */
//        private Log log;
//        private LogService logService;
//
//        public SaveSystemLogThread(Log esLog, LogService logService) {
//            this.log = esLog;
//            this.logService = logService;
//        }
//
//        @Override
//        public void run() {
//
//            logService.save(log);
//        }


        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开启了一个线程");
        }
    }




}




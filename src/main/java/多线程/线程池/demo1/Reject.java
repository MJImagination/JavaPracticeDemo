package 多线程.线程池.demo1;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class Reject implements RejectedExecutionHandler {

    public Reject(){

    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("##被拒绝的任务:"+r.toString());
    }
}
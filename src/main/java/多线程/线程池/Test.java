package 多线程.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author 微信公众号：程序员之路
 *
 */
class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(String.format("子线程 %s: 在进行计算", Thread.currentThread().getName()));
        Thread.sleep(6000);
        int sum = 0;
        for (int i = 0; i < 100; i++)
            sum += i;
        return "子进程"+Thread.currentThread().getName()+"："+sum;
    }
}

/**
 * @author 微信公众号：程序员之路
 *
 */
public class Test {
    public static void main(String[] args) {
        //个人建议，控制线程池中线程的个数，防止开启过多线程造成系统崩溃
        ExecutorService executor = Executors.newFixedThreadPool(5);
        
        //创建100个任务
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Task task = new Task();
            tasks.add(task);
        }
        
        //ExecutorService的invokeAll方法执行任务集合中的所有任务，并设置超时时间（100秒）。超时后的任务将取消进行
        List<Future<String>> futures = new ArrayList<>();
        try {
            futures = executor.invokeAll(tasks, 10, TimeUnit.SECONDS);//注：执行所有的方法执行完的时间不能超过100秒，超时后的任务将取消执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        
        Integer i = 1;
        Integer j = 0;
        for (Future<String> future : futures) {
            try {
                System.out.println(i+" : "+future.get());
                i++;
            }catch (Exception e) {
                j++;
            } 
        }
        System.out.println("未成功执行的任务个数："+j);
    }
}
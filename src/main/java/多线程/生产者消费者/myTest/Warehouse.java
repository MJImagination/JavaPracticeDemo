package 多线程.生产者消费者.myTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: 仓库
 * @Author: MJ
 * @Date: Created in 2019/4/29
 */
public class Warehouse {
    /**
     * 用于存放商品的阻塞队列 消费者
     */
    private BlockingQueue<Product> blockingQueue = new LinkedBlockingQueue<>();

    public Product getProduct(){
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return null;
    }

    public void setProdect(Product prodect){
        try {
            blockingQueue.put(prodect);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getSize(){
        return blockingQueue.size();
    }
}



//        ffer(E e): 将给定的元素设置到队列中，如果设置成功返回true, 否则返回false. e的值不能为空，否则抛出空指针异常。
//        offer(E e, long timeout, TimeUnit unit): 将给定元素在给定的时间内设置到队列中，如果设置成功返回true, 否则返回false.
//        add(E e): 将给定元素设置到队列中，如果设置成功返回true, 否则抛出异常。如果是往限定了长度的队列中设置值，推荐使用offer()方法。
//
//        put(E e): 将元素设置到队列中，如果队列中没有多余的空间，该方法会一直阻塞，直到队列中有多余的空间。
//        take(): 从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
//        poll(long timeout, TimeUnit unit): 在给定的时间里，从队列中获取值，如果没有取到会抛出异常。
//        remainingCapacity()：获取队列中剩余的空间。
//        remove(Object o): 从队列中移除指定的值。
//        contains(Object o): 判断队列中是否拥有该值。
//        drainTo(Collection c): 将队列中值，全部移除，并发设置到给定的集合中。
//
//        ---------------------
//        原文：https://blog.csdn.net/wuzhiwei549/article/details/79869699

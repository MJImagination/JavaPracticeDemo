package 多线程.生产者消费者;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductorConsumer {

//https://www.jianshu.com/p/e29632593057
public static void main(String[] args) {

    LinkedList linkedList = new LinkedList();
    ExecutorService service = Executors.newFixedThreadPool(15);
    for (int i = 0; i < 5; i++) {
        service.submit(new Productor(linkedList, 8));
    }

    for (int i = 0; i < 10; i++) {
        service.submit(new Consumer(linkedList));
    }

}

static class Productor implements Runnable {

    private List<Integer> list;
    private int maxLength;

    public Productor(List list, int maxLength) {
        this.list = list;
        this.maxLength = maxLength;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                try {
                    System.out.println("生产：" + list.size());
                    while (list.size() == maxLength) {
                        System.out.println("生产者" + Thread.currentThread().getName() + "  list以达到最大容量，进行wait");
                        list.wait();
                        System.out.println("生产者" + Thread.currentThread().getName() + "  退出wait");
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("生产者" + Thread.currentThread().getName() + " 生产数据" + i);
                    list.add(i);
                    list.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}


static class Consumer implements Runnable {

    private List<Integer> list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                try {
                    while (list.isEmpty()) {
                        System.out.println("消费者" + Thread.currentThread().getName() + "  list为空，进行wait");
                        list.wait();
                        System.out.println("消费者" + Thread.currentThread().getName() + "  退出wait");
                    }
                    Integer element = list.remove(0);
                    System.out.println("消费者" + Thread.currentThread().getName() + "  消费数据：" + element);
                    list.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

}

//输出结果：
//
//生产者pool-1-thread-1 生产数据-232820990
//生产者pool-1-thread-1 生产数据1432164130
//生产者pool-1-thread-1 生产数据1057090222
//生产者pool-1-thread-1 生产数据1201395916
//生产者pool-1-thread-1 生产数据482766516
//生产者pool-1-thread-1  list以达到最大容量，进行wait
//消费者pool-1-thread-15  退出wait
//消费者pool-1-thread-15  消费数据：1237535349
//消费者pool-1-thread-15  消费数据：-1617438932
//消费者pool-1-thread-15  消费数据：-535396055
//消费者pool-1-thread-15  消费数据：-232820990
//消费者pool-1-thread-15  消费数据：1432164130
//消费者pool-1-thread-15  消费数据：1057090222
//消费者pool-1-thread-15  消费数据：1201395916
//消费者pool-1-thread-15  消费数据：482766516
//消费者pool-1-thread-15  list为空，进行wait
//生产者pool-1-thread-5  退出wait
//生产者pool-1-thread-5 生产数据1442969724
//生产者pool-1-thread-5 生产数据1177554422
//生产者pool-1-thread-5 生产数据-133137235
//生产者pool-1-thread-5 生产数据324882560
//生产者pool-1-thread-5 生产数据2065211573
//生产者pool-1-thread-5 生产数据253569900
//生产者pool-1-thread-5 生产数据571277922
//生产者pool-1-thread-5 生产数据1622323863
//生产者pool-1-thread-5  list以达到最大容量，进行wait
//消费者pool-1-thread-10  退出wait
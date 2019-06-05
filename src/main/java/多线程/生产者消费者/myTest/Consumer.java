package 多线程.生产者消费者.myTest;

import util.HttpGet;

import java.util.Date;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/4/29
 */
public class Consumer implements Runnable {
    //仓库
    private Warehouse warehouse;
    private String name;

    public Consumer(Warehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            int i = 3;
            while (i> 0) {

                Thread.sleep((int) (Math.random() * 3000) );
                System.out.println("消费 **** 消费者:" + name + "-" + Thread.currentThread().getId() + " 已消费产品:" + warehouse.getProduct().getName() + " 时间：" + DateTools.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "仓库数量：" + warehouse.getSize());
                i--;
                HttpGet httpGet = new HttpGet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Consumer{" +
                "warehouse=" + warehouse +
                ", name='" + name + '\'' +
                '}';
    }


}

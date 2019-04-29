package 多线程.生产者消费者.myTest;

import java.util.Date;
import java.util.Random;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/4/29
 */
public class Producer implements Runnable {
    //仓库
    private Warehouse warehouse;
    private String name;

    public Producer(Warehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    @Override
    public void run() {

        try {
            while(true){
                Product product = new Product(String.valueOf((int) (Math.random() * 1000)));
                System.out.println("生产 **** 生产者:" + name + "-" + Thread.currentThread().getId() + " 准备生产品:" + product.getName() + " 时间：" + DateTools.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                Thread.sleep(500);
                product.setDate(new Date());
                warehouse.setProdect(product);
                System.out.println("生产 **** 生产者:" + name + "-" + Thread.currentThread().getId() + " 已生产产品:" + product.getName() + " 时间：" + DateTools.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "仓库数量：" + warehouse.getSize() );
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
        return "Producer{" +
                "warehouse=" + warehouse +
                ", name='" + name + '\'' +
                '}';
    }
}

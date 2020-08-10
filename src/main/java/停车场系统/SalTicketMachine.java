package 停车场系统;


import java.util.Random;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/6/18
 */
public class SalTicketMachine {

    /*模拟2个售票机器*/
    public static Thread thread1;
    public static Thread thread2;


    public static void main(String[] args) {
        /*初始化停车场系统*/
        Park park = new Park(3);

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                /*模拟26辆车  a-z */
                for (int i = 97; i < 123; i++) {
                    SalTicketMachine.doParking(park, new Car((char) i + ""));
                }
            }
        });

        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                /*模拟26辆车 A-Z  */
                for (int i = 65; i < 91; i++) {
                    SalTicketMachine.doParking(park, new Car((char) i + ""));
                }
            }
        });

        thread1.start();
        thread2.start();

    }

    /**
     * 模拟停车
     *
     * @param park
     * @param car
     */
    public static void doParking(Park park, Car car) {
        /*获取剩余车位并占位*/
        ParkingSpace parkingSpace = park.getParkingSpace();
        if (parkingSpace == null) {
            System.out.println("车号:" + car.getCarNum() + "尝试占车位失败");
            System.out.println("最少等待时间：" + park.lestRemainingTime());
            /*占用位子失败，阻塞线程，等待定时任务换起*/
            LockSupport.park();
            /*唤醒线程后尝试重新占位*/
            doParking(park, car);
        } else {
            System.out.println("停车位信息 --  车号:" + car.getCarNum() + "，" +
                    "车位编号:" + parkingSpace.getParkingNum());

            /*停车入库*/
            park.addCard(parkingSpace.getParkingNum(), car, System.currentTimeMillis()
                    + 1000 * new Random().nextInt(10));
        }

    }
}

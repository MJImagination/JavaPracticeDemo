package 停车场系统;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: 停车场
 * @Author: MJ
 * @Date: Created in 2020/6/18
 */
public class Park {
    /**
     * 停车位数量
     */
    private int parkingNum;

    /**
     * 停车位&车辆信息map  key:车位号，value:车位信息
     */
    public Map<Integer, ParkingSpace> spaceCarMap = new ConcurrentHashMap<>();

    /**
     * 排队队列 key：车位号  value:数值 （ {0：未停车，1：占位 ,2:超时，其他：预计离开时间}）
     */
    public Map<Integer, Long> leaveTimeMap = new ConcurrentHashMap<>();


    public Park(int parkingNum) {
        this.parkingNum = parkingNum;
        init(parkingNum);
        /* 这里切换是否存在延期的情况 */
//        refreshCache();
        refreshCache2();
    }

    /**
     * 初始化停车位输量
     * 时间复杂度为O（n）
     *
     * @param num
     */
    public void init(int num) {
        for (int i = 1; i <= num; i++) {
            leaveTimeMap.put(i, 0L);
        }
    }

    /**
     * 停车
     * 时间复杂度为O（n）
     * @param parkingNum      车位编号
     * @param car             汽车信息
     * @param expectLeavetime 预计离开时间
     * @return
     */
    public ParkingSpace addCard(int parkingNum, Car car, long expectLeavetime) {

        if (leaveTimeMap.containsKey(parkingNum) && !spaceCarMap.containsKey(parkingNum)) {
            /*车位已被排队但是未停，则停入车辆*/
            ParkingSpace parkingSpace = new ParkingSpace();
            parkingSpace.setParkingNum(parkingNum);
            parkingSpace.setEnterTime(System.currentTimeMillis());
            parkingSpace.setLeaveTime(null);
            parkingSpace.setCar(car);
            parkingSpace.setExpectLeaveTime(expectLeavetime);
            /*从占位状态更新为实际占用状态*/
            leaveTimeMap.put(parkingNum, expectLeavetime);
            /*保存车位号和车位信息*/
            spaceCarMap.put(parkingNum, parkingSpace);

            return parkingSpace;
        } else if (leaveTimeMap.containsKey(parkingNum) && spaceCarMap.containsKey(parkingNum)) {
            /*车位已排队，且已停入*/
            return spaceCarMap.get(parkingNum);
        } else {
            /*停车失败*/
            return null;
        }
    }

    /**
     * 获取车位 -用于占位
     * 时间复杂度为O（n）
     * @return
     */
    public synchronized ParkingSpace getParkingSpace() {
        Iterator<Map.Entry<Integer, Long>> entryIterator = leaveTimeMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, Long> entry = entryIterator.next();
            if (entry.getValue() == 0L) {
                /*占位*/
                leaveTimeMap.put(entry.getKey(), 1L);
                return new ParkingSpace(entry.getKey());
            }
        }
        return null;
    }


    /**
     * 获取最少等待时间
     * 时间复杂度为O（n）
     * @return
     */
    public String lestRemainingTime() {
        List<Map.Entry<Integer, Long>> list = new ArrayList<Map.Entry<Integer, Long>>(leaveTimeMap.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, Long>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<Integer, Long> o1,
                               Map.Entry<Integer, Long> o2) {
                return Long.compare(o1.getValue(), o2.getValue());
            }

        });
        if (list != null && list.size() > 0) {

            for (Map.Entry<Integer, Long> integerLongEntry : list) {
                if (integerLongEntry.getValue() != 0 && integerLongEntry.getValue() != 1 && integerLongEntry.getValue() != 2) {
                    return formatTime(integerLongEntry.getValue() - System.currentTimeMillis());

                }
            }
        }
        return "无法预计剩余时间，所有车位均超过预期停车";
    }

    /**
     * 根据预计离开时间排序
     * 时间复杂度为O（n）
     */
    public List sortTime() {
        List<Map.Entry<Integer, Long>> list = new ArrayList<Map.Entry<Integer, Long>>(leaveTimeMap.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, Long>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<Integer, Long> o1,
                               Map.Entry<Integer, Long> o2) {
                return Long.compare(o1.getValue(), o2.getValue());
            }

        });
        return list;
    }


    /**
     * 模拟每次准时离开
     */
    public void refreshCache() {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();
        /**每间隔5秒执行一次任务*/
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //将到期car移除（模拟准时离开）
                Iterator<Map.Entry<Integer, Long>> entrys = leaveTimeMap.entrySet().iterator();
                while (entrys.hasNext()) {
                    Map.Entry<Integer, Long> entry = entrys.next();
                    if (entry.getValue() <= System.currentTimeMillis()) {
                        leaveTimeMap.put(entry.getKey(), 0L);
                        spaceCarMap.remove(entry.getKey());
                    }
                }
                //唤醒等待占用的车
                LockSupport.unpark(SalTicketMachine.thread1);
                LockSupport.unpark(SalTicketMachine.thread2);
            }
        }, 1, 5, TimeUnit.SECONDS);
    }

    /**
     * 模拟不准时离开
     */
    public void refreshCache2() {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();
        /**每间隔5秒执行一次任务*/
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //将到期car移除（模拟准时离开）
                Iterator<Map.Entry<Integer, Long>> entrys = leaveTimeMap.entrySet().iterator();
                while (entrys.hasNext()) {
                    Map.Entry<Integer, Long> entry = entrys.next();
                    if (entry.getValue() <= System.currentTimeMillis()) {
                        leaveTimeMap.put(entry.getKey(), 2L);
//                        spaceCarMap.remove(entry.getKey());
                    }
                }
                //唤醒等待占用的车
                LockSupport.unpark(SalTicketMachine.thread1);
                LockSupport.unpark(SalTicketMachine.thread2);
            }
        }, 1, 5, TimeUnit.SECONDS);
    }


    /**
     * 毫秒转化天时分秒毫秒
     *
     * @param ms
     * @return
     */
    public static String formatTime(Long ms) {
        if (ms > 0) {
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = ms / dd;
            Long hour = (ms - day * dd) / hh;
            Long minute = (ms - day * dd - hour * hh) / mi;
            Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
            Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

            StringBuffer sb = new StringBuffer();
            if (day > 0) {
                sb.append(day + "天");
            }
            if (hour > 0) {
                sb.append(hour + "小时");
            }
            if (minute > 0) {
                sb.append(minute + "分");
            }
            if (second > 0) {
                sb.append(second + "秒");
            }
            if (milliSecond > 0) {
                sb.append(milliSecond + "毫秒");
            }

            return sb.toString();
        } else {
            return "0秒";
        }

    }

    public int getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(int parkingNum) {
        this.parkingNum = parkingNum;
    }
}

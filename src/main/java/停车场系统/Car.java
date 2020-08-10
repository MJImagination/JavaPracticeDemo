package 停车场系统;

/**
 * @Description: 车辆
 * @Author: MJ
 * @Date: Created in 2020/6/18
 */
public class Car {
    /**
     * 车牌
     */
    private String carNum ;

    public Car(String carNum) {
        this.carNum = carNum;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}

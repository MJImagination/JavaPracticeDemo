package 停车场系统;

/**
 * @Description:  车位信息
 * @Author: MJ
 * @Date: Created in 2020/6/18
 */
public class ParkingSpace {
    public ParkingSpace() {
    }

    public ParkingSpace(Integer serialNumber) {
        this.parkingNum = serialNumber;
    }

    /**
     * 车位编号
     */
    private Integer parkingNum;

    /**
     * 驶入时间
     */
    private long enterTime;

    /**
     * 离开时间
     */
    private Long leaveTime;

    /**
     * 预计离开时间
     */
    private long expectLeaveTime;

    private Car car;




    public Integer getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(Integer parkingNum) {
        this.parkingNum = parkingNum;
    }

    public long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public Long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public long getExpectLeaveTime() {
        return expectLeaveTime;
    }

    public void setExpectLeaveTime(long expectLeaveTime) {
        this.expectLeaveTime = expectLeaveTime;
    }
}

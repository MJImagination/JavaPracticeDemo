package 设计模式.观察者模式.ObserveDemo;

import java.util.Observable;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/17
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void measurementsChanged() {
        //一定要有这步
        super.setChanged();
        /**
         * 下面2个顺序还不能改，改了“其他通知参数”就没了  
         */
        notifyObservers();
        notifyObservers("其他通知参数");
    }

    /**
     * 手动模拟被观测者更改时  会去通知所有观察者 （天气信息更改时，通知***）
     * @param temperature
     * @param humidity
     */
    public void setMeasurements(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        measurementsChanged();
    }
}

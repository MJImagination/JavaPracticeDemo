package 观察者模式.ObserveDemo;

import java.util.Observer;

/**
 * @Description: 回调函数是观察者模式的简单应用
 * @Author: MJ
 * @Date: Created in 2018/7/17
 */
public class CurrendDisplay implements Observer {
    private String name;
    private float temperature;
    private float humidity;
    private java.util.Observable observable;

    public CurrendDisplay(java.util.Observable observable, String name) {
        this.observable = observable;
        this.name = name;
        observable.addObserver(this);
    }

    @Override
    public void update(java.util.Observable obs, Object arg) {
//        this.temperature
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) obs;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
        }
        if (arg instanceof String) {
            String str = (String) arg;
            System.out.println(str);
        }
        this.display();
    }

    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "CurrendDisplay{" +
                "name='" + name + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", observable=" + observable +
                '}';
    }
}

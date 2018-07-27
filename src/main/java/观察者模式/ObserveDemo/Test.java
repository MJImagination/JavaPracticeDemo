package 观察者模式.ObserveDemo;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/17
 */
public class Test {
    public static void main(String[] args){

        WeatherData weatherDat = new WeatherData();

        CurrendDisplay currendDisplayA = new CurrendDisplay(weatherDat,"A");
        CurrendDisplay currendDisplayB = new CurrendDisplay(weatherDat,"B");
        CurrendDisplay currendDisplayC = new CurrendDisplay(weatherDat,"C");

        weatherDat.setMeasurements(10,37);
    }


}

package 观察者模式.WeatherStation;

public interface Observer {
	public void update(float temp, float humidity, float pressure);
}

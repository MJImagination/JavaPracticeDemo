/**
 * Description:
 * 偶数观察者类
 * <p>订阅主题的内容的偶数变化
 * @author SuooL
 * @version 1.0.0
 * <p> <a herf = "http://suool.net"> SuooL's Blog </a>
 */
package 设计模式.观察者模式.Test2;
 
import java.util.Observable;
import java.util.Observer;
 
/**
 * 偶数内容订阅类
 * @author SuooL
 *
 */
public class evenObserver implements Observer	{
	/**
	 * 继承自Observer接口类,update的方法的实现
	 * @param o 主题对象
	 * @param arg notifyObservers(flag);传来的参数,即是标识变量
	 */
	public void update(Observable o, Object arg) {
		if (arg == NumsObservable.EVEN) {
		NumsObservable myObserable=(NumsObservable) o;
	    System.out.println("Data has changed to EVEN number " +myObserable.getData());
	    }
	}
}

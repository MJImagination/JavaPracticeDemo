/**
 * Description:
 * 被观察者--主题类
 * <p>两个私有静态变量,分别变化
 * <p>被两个订阅者订阅,分别关注不同的兴趣点--奇数变化 OR 偶数变化
 * @author SuooL
 * @version 1.0.0
 * <p> <a herf = "http://suool.net"> SuooL's Blog </a>
 */
package 观察者模式.Test2;
public class NumsTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建主题对象和订阅者对象
		NumsObservable observerNum = new NumsObservable();
		oddObserver oddObserver = new oddObserver();
		evenObserver evenObserver = new evenObserver();
		// 为主题增加订阅者
		observerNum.addObserver(oddObserver);
		observerNum.addObserver(evenObserver);
		// 修改主题对象内容
		observerNum.setData(12);
		observerNum.setData(11);
		observerNum.setData(10);
	}
}

package 设计模式.观察者模式.Test1;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/10/11
 */
public class MyNews implements Observer {
    private Observable observable;

    public MyNews(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof News){
            News news = (News)o;
            System.out.println(((News) o).getName() + ":" + arg);

        }
    }
}

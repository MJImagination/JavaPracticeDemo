package 设计模式.观察者模式.Test1;


import java.util.Observable;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/10/11
 */
public class News extends Observable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        super.setChanged();
        super.notifyObservers("dfd");
    }
}

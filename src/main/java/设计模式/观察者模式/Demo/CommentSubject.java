package 设计模式.观察者模式.Demo;

import java.util.Observable;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/7/22
 */
public class CommentSubject extends Observable {
    private String name;

    public CommentSubject(String name) {
        this.name = name;
    }

    public CommentSubject() {
    }

    public void setChange(){
        super.setChanged();
    }

    @Override
    public String toString() {
        return "CommentSubject{" +
                "name='" + name + '\'' +
                '}';
    }
}

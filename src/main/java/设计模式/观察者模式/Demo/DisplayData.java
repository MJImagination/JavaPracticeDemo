package 设计模式.观察者模式.Demo;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/7/22
 */
public class DisplayData implements Observer {
    private SubjectData subjectData;

    private Observable observable;

    public DisplayData(SubjectData subjectData) {
        this.subjectData = subjectData;
        subjectData.addObserver(this);
    }

    public DisplayData(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
//        if (arg instanceof String) {
//            String str = (String) arg;
//            System.out.println(str);
//        }
        System.out.println(o.toString() /*+ ":" + arg.toString()*/);
    }


    public static void main(String[] args) {
        SubjectData subjectData = new SubjectData("A");
        DisplayData displayData = new DisplayData(subjectData);
        subjectData.setChange();
        subjectData.test();//同包下子类可以调用父类protect
        subjectData.notifyObservers();
//        subjectData.setChange();
//        subjectData.notifyObservers();
        subjectData.notifyObservers("aaaaaa");

        CommentSubject commentSubject = new CommentSubject("B");
        DisplayData displayData_2 = new DisplayData(commentSubject);
        commentSubject.setChange();
        commentSubject.notifyObservers();


    }
}

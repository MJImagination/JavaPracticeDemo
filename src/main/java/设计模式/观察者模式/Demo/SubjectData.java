package 设计模式.观察者模式.Demo;

import java.util.Observable;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/7/22
 */
public class SubjectData extends Observable {
    private String name;

    public SubjectData(String name) {
        this.name = name;
    }

    public SubjectData() {
        super();
    }

    @Override
    public String toString() {
        return "SubjectData{" +
                "name='" + name + '\'' +
                '}';
    }


    public void setChange(){
        super.setChanged();//因为不同包，所以要放在子类里调用
    }

    protected void test(){

    }
}

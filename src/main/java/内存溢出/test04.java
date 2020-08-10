package 内存溢出;

import java.util.HashSet;
import java.util.Set;

public class test04 {
    public static void main(String[] args) {

        Set set = new HashSet();

        for (int i = 0; i < 10; i++) {
            C c = new C();
            c.setName(i + "");
            set.add(c);

            // 设置为空，这对象我不再用了
//                object = null;
            //https://www.jianshu.com/p/2d88e5070361
            c = null;
//            System.out.println();
        }

        // 但是set集合中还维护这obj的引用，gc不会回收object对象
        System.out.println(set);
    }
}

class C {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "c{" +
                "name='" + name + '\'' +
                '}';
    }
}

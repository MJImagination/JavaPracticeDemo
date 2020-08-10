package 内存溢出;

import java.util.ArrayList;
import java.util.List;

public class JavaMemory2 {
    private List list = new ArrayList();
    private List list2 = new ArrayList();

    static float freeMem = 0f;
    static float totalMem = 0f;
    static float maxMem = 0f;
    static float usedMemRate = 0f;

    static {
        System.out.println("-----------系统初始时内存情况---------------");
        showMem();
    }

    public static void showMem() {
        freeMem = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        totalMem = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        maxMem = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        usedMemRate = (totalMem - freeMem) / maxMem * 100.0f;

        StringBuilder sb = new StringBuilder();
        sb.append("最大内存:" + maxMem + " MB\n")
                .append("当前最大内存:" + totalMem + " MB\n")
                .append("当前空闲内存:" + freeMem + " MB\n")
                .append("当前已使用的内存:" + (totalMem - freeMem) + " MB\n")
                .append("实际jvm剩余内存:" + (maxMem - (totalMem - freeMem)));
        System.out.println(sb.toString());
    }

    public void f() {
        B b1 = new B();
        list.add(b1);
        System.out.println("--------------------");
        showMem();
    }

    public void f2() {
        List al = list;
        list2 = list;
 list = null; //此时并没有回收堆中内存b1, 因为还存在引用  //https://www.jianshu.com/p/2d88e5070361
// list.clear(); //可以回收内存b1
        B b2 = new B();
        al.add(b2);

        System.out.println("--------------------");
        showMem();

        B b3 = new B();
        al.add(b3);
    }

    public static void main(String[] args) {
        JavaMemory2 jmp = new JavaMemory2();
        jmp.f();
        jmp.f2();
    }
}

class B {
    public byte[] data1 = new byte[(int) (Runtime.getRuntime().maxMemory() * 0.4)];
    public int b;
}

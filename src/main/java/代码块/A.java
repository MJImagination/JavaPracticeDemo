package 代码块;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/8/10
 */
public class A {
    public A() {
        System.out.println("父类构造方法");
    }
    {
        System.out.println("父类非静态代码块");
    }

    static {
        System.out.println("父类静态代码块");
    }
}

class CCC{
    public CCC(){
        System.out.println("CCC造方法");
    }
    static {
        System.out.println("ccc静态代码块");
    }
}

class BB extends A{
    public BB() {
        System.out.println("子类构造方法");
    }
    {
        System.out.println("子类非静态代码块");
    }

    static {
        System.out.println("子类静态代码块");
    }

    public static void main(String[] args){
//        BB b = new BB();  静态代码块的代码只会在类第一次初始化的时候执行一次。一个类可以有多个静态代码块
        BB b = new BB();
        System.out.println("main函数");
    }
}

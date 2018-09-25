package 代码块;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/9/25
 */
public class Demo2 {
    static {       //在主方法所在类中定义静态块
        System.out.println("在主方法所在类中定义静态块");
    }


    public static void main(String[] args){
        {
            //A - 普通代码块：在方法或语句中定义的代码块
            System.out.println("普通代码块");
        }
        new B();
        new B();
        new B();
    }
}


class B {
    {       // B - 构造块：直接写在类中的代码块
        System.out.println("1、构造块");
    }
    static {
        System.out.println("0、静态代码块");
    }

    public B(){
        System.out.println("2、构造方法");
    }


}

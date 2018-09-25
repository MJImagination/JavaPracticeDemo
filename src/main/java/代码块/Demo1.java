package 代码块;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/9/25
 */
public class Demo1 {
    {       // B - 构造块：直接写在类中的代码块
        System.out.println("1、构造块");
    }
    static {
        System.out.println("0、静态代码块");
    }

    public Demo1(){
        System.out.println("2、构造方法");
    }



    public static void main(String[] args){
        System.out.println("system.out");
        {
            //A - 普通代码块：在方法或语句中定义的代码块
            System.out.println("普通代码块");
        }
        Demo1.abc();
        {
            System.out.println("普通代码块2");
        }
        new Demo1();
        new Demo1();
        {
            System.out.println("普通代码块3");
        }
        new Demo1();
    }


    public static void abc(){
        System.out.println("abc");
    }
}



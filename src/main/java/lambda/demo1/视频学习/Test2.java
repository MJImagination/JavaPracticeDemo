package lambda.demo1.视频学习;

/**
 * https://blog.csdn.net/weixin_42022555/article/details/81943263
 */
interface test {
    public void run(String string);
}

@FunctionalInterface
interface In2 {
    public String run(String string);
}

class Person222 {
    public String goWalking(String string) {
        return string.concat(" 引用方法");
    }
}

interface ArrayTest {
    public String[] run(int length);
}


public class Test2 {
    public static void main(String[] args) {
        //1、静态方法引用
        printString("静态方法引用", System.out::println);
        //   实质代码: printString("静态方法引用", (string) -> System.out.println(string));


        //2、对象方法引用

        //实质代码:      test t1 = (string) -> new Person().goWalking(string);
        //实质代码:     System.out.println(t1.run("对象"));
        In2 in2 = new Person222()::goWalking;
        System.out.println(in2.run("对象"));        //输出:对象 引用方法

        In2 in2_1 = new In2Son();
        System.out.println(in2_1.run("对象 ====> 真实调用方式"));


        //3.数组构造方法引用
        //实质代码:     test t1 = (length) -> new String[length];
        ArrayTest arrayTest = String[]::new;
        String[] arr = arrayTest.run(5);

    }

    public static void printString(String str, test t) {
        t.run(str);
    }



}

class In2Son implements In2{
    @Override
    public String run(String string) {
        return new Person222().goWalking(string);
    }
}

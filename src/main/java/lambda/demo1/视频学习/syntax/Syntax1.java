package lambda.demo1.视频学习.syntax;

import lambda.demo1.视频学习.interfaces.*;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/27
 */
public class Syntax1 {
    public static void main(String[] args) {
        //1.lambda表达式的基础语法：
        //lambda是一个匿名函数
        //参数列表 方法体

        // () : 用来描述参数列表
        // {} : 用来描述方法体
        // -> : Lambda运算符，读作 goes to

        //无参无返回
        LambdaNoneReturnNoneParameter lambda1 = () -> {
            System.out.println("hello world");
        };
        lambda1.test();


        //无返回值，单个参数

        LambdaNoneReturnSingleParameter lambda2 = (int a) -> {
            System.out.println(a);
        };
        //单个参数
        //如果参数列表只有一个参数，此时括号可以省略
        LambdaNoneReturnSingleParameter lambda2_2 = a -> {
            System.out.println(a);
        };

        LambdaNoneReturnSingleParameter lambda2_3 = (a) -> System.out.println(a * 2);
        lambda2.test(10);
        lambda2_2.test(10);
        lambda2_3.test(8);


        //匿名内部类写法
        LambdaNoneReturnSingleParameter lambda2_4 = new LambdaNoneReturnSingleParameter() {
            @Override
            public void test(int a) {
                System.out.println(a * 2);
            }
        };
        lambda2_4.test(1);

        //无返回，多个参数
        //由于在接口的抽象方法中，已经定义了参数的数量和类型，所以Lambda表达式中，参数的类型可以省略
        //备注：如果需要省略类型，则每一个参数类型都要省略
        LambdaNoneReturnMutipleParameter lambda3 = (a, b) -> {
            System.out.println(a + b);
        };
        LambdaNoneReturnMutipleParameter lambda3_1 = (int a, int b) -> {
            System.out.println(a + b);
        };
        lambda3.test(1, 3);
        lambda3_1.test(1, 3);

        //有返回，无参数
        LambdaSingleReturnNoneParameter lambda4 = () -> {
            System.out.println("lambda4");
            return 100;
        };
        int result4 = lambda4.test();
        System.out.println(result4);

        //有返回，单个参数
        LambdaSingleReturnSingleParameter lambda5 = (int a) -> {
            System.out.println(a);
            return 100;
        };
        System.out.println(lambda5.test(5));

        //如果方法体只有一条语句，大括号可以省略,如果单条语句是是return ,则 return也要省略
        LambdaSingleReturnSingleParameter lambda5_1 = a -> a * 3;
        System.out.println(lambda5_1.test(2));


    }

}

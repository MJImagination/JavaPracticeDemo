package lambda.demo1.视频学习.syntax;

import lambda.demo1.视频学习.interfaces.LambdaSingleReturnSingleParameter;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/27
 */
public class Syntax2 {
    public static void main(String[] args) {
        //方法引用：
        //可以快速的将一个Lambda表达式的实现指向一个已经实现的方法。
        //语法：方法的隶属者::方法名
        //方法隶属者，如果方法是static，隶属这是类，如果不是static，则是类的实例对象


        //注意：
        //1.参数数量和类型一定要和接口中定义的方法一致
        //2.返回值的类型一定要和接口中定义的方法一致

        LambdaSingleReturnSingleParameter lambda1 = a -> a * 2;
        System.out.println(lambda1.test(3));

        LambdaSingleReturnSingleParameter lambda2 = a -> change(a);
        System.out.println(lambda2.test(3));

        //方法引用：引用了change方法的实现
        LambdaSingleReturnSingleParameter lambda3 = Syntax2::change;
        System.out.println(lambda3.test(3));
    }

    private static int change(int a){
        return a * 2;
    }
}

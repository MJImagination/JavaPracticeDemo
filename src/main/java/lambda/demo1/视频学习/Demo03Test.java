package lambda.demo1.视频学习;

import java.util.function.Consumer;

/**
 * https://www.cnblogs.com/chunzhi716/p/14057165.html
 * https://www.cnblogs.com/newcityboy/p/11297662.html
 */
public class Demo03Test {
    //定义一个方法，参数传递String类型对的数组和两个Consumer接口，泛型使用String
    public static void printInfo(String[] arr, Consumer<String> con1, Consumer<String> con2) {
        //遍历字符串数组
        for (String message : arr) {
            //使用andThen方法连接两个Consumer接口，消费字符串
            con1.andThen(con2).accept(message);
        }
    }

    public static void main(String[] args) {
        //定义一个字符串类型的数组
        String[] arr = {"张明银,男", "李敏,女", "胡敏,男"};
        //调用printInfo方法，传递一个字符串数组和两个Lambda表达式
        printInfo(arr, (message) -> {
            //对message进行切割，获取姓名，并按照指定的格式输出
            String name = message.split(",")[0];
            System.out.print("姓名：" + name);
        }, (message) -> {
            String name = message.split(",")[1];
            System.out.println("。性别：" + name + "。");
        });
    }
}
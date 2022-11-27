package lambda.demo1.视频学习;

import java.util.function.Consumer;

/*
    https://www.cnblogs.com/chunzhi716/p/14057165.html
    Consumer接口的默认方法andThen
    作用：需要两个Consumer接口，可以把两个Consumer接口组合到一起，再对数据进行消费
    例如：
        Consumer<String> con1
        Consumer<String> con2
        String s = "Hello";
        con1.accept(s);
        con2.accept(s);
        连接两个Consumer接口 再进行消费
        con1.andThen(con2).accept(s);   谁写前边谁先消费
 */
public class Test02AndThen {
    // 定义一个方法，方法的参数传递一个字符串和两个Consumer接口，Consumer接口的泛型使用字符串
    public static void method(String s, Consumer<String> con1, Consumer<String> con2) {
        /*con1.accept(s);
        con2.accept(s);*/
        // 使用andThen方法，把两个Consumer接口连接到一起，再消费数据
        con1.andThen(con2).accept(s); // con1连接到con2，先执行con1消费数据，再执行con2消费数据
        // 如果有多个Consumer接口，就可以用andThen一直连接
    }

    public static void main(String[] args) {
        // 调用method方法，传递一个字符串，两个Lambda表达式
        method("Hello",
                (s) -> {
                    // 把字符串转换为大写输出
                    System.out.println(s.toUpperCase());
                },
                (s) -> {
                    // 把字符串转换为小写输出
                    System.out.println(s.toLowerCase());
                });
    }
}
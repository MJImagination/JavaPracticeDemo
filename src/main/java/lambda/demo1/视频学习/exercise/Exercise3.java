package lambda.demo1.视频学习.exercise;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2021/4/27
 */
public class Exercise3 {
    public static void main(String[] args) {
        //打印
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5, 6);
        list.forEach(integer -> System.out.println(integer));
        list.forEach(System.out::println);  // 理解 Syntax2例子



        //其他例子  有道云笔记 《关于函数式接口的传参方式》
        //百度快照：http://cache.baiducontent.com/c?m=Yq-YMZvPnS8pKts5b1I7lwU3YMkRwYEJpXXVUdiQrXrPpYQu3ysue4nCdKK7fhv3CBjxEHm-WahfK_0zcZ1F5S7U4mhsPgFpGyD341QLpCmyPXD_J-H16ORzeEu6adLopGOEBQMEQGGzXKPBO3lyBK4pPMZz3FeAe6uxgX0EzxXTzXBgq8j-_9Y-47JOb2xzV6vu0BQNblLMaSnSUdrRnK&p=8b2a9712939e50f840bd9b7d0d129c&newp=c3769a47cc8a01f534bd9b7d0d128e231610db2151d4d401298ffe0cc4241a1a1a3aecbf2c251706d0c07a6c06ad4c59e1f53c703d0034f1f689df08d2ecce7e35c1676e1c&s=40174d861a013f07&user=baidu&fm=sc&query=%BA%AF%CA%FD%BD%D3%BF%DA%B5%B1%B7%BD%B7%A8%B4%AB%C8%EB&qid=d25d0a770012ea23&p1=2
        //https://blog.csdn.net/weixin_42022555/article/details/81943263
        Consumer consumer = i -> System.out.println(i + "aaa");
         consumer.accept("vvvv");
        Consumer consumer2 = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(10 + "aaa");
            }
        };
//        Consumer consumer3 = Exercise3::test2;


        IntConsumer intConsumer = i -> System.out.println(i * 10);

        //传统面向对象的方式，直接将对象的引用传进去
        test(consumer);
        //函数式方式，将函数式接口中唯一的方法，通过方法引用的方式，将定义的 lambda表达式 传入
        test(consumer::accept);
        test(intConsumer::accept);
    }

    private static void test(Consumer<? super Integer> consumer) {
        consumer.accept(10);
    }


    private static void test2(T a) {
        System.out.println(a +"fadf");
    }

}

package lambda.demo1.视频学习;

/**
 * @Description: 理解lambda做了什么
 * @Author: MJ
 * @Date: Created in 2021/4/27
 */
public class Test1 {
    public static void main(String[] args) {
        //使用接口类实现
        CompareAble compareAble = new MyCompare();
        System.out.println(compareAble.campare(2, 3));

        //使用匿名内部类
        CompareAble compareAble1 = new CompareAble() {
            @Override
            public int campare(int a, int b) {
                return a - b;
            }
        };
        System.out.println(compareAble1.campare(2, 3));

        //使用lambda方式实现
        System.out.println("==================");
        CompareAble compareAble2 = (a, b) -> a - b;
        System.out.println(compareAble2.campare(2, 4));


        System.out.println("========compareAble3 ==========");
        CompareAble compareAble3 = Test1::test;
        System.out.println(compareAble3.campare(2, 4));

        System.out.println("========compareAble4 ==========");
        CompareAble compareAble4 = (a, b) -> {
            return 1;
        };
        CompareAble compareAble5 = (a, b) -> 1;

        System.out.println("========consumer ==========");
        Consumer consumer = i -> System.out.println(i + "10");
        Consumer consumer2 = Test1::test2;
        consumer2.print(3);


        test(Test1::test2);

    }


    private static int test(int a, int b) {
        return a - b;
    }

    private static void test2(Object a) {
        System.out.println(a + "fadf");
    }

    private static void test(Consumer<? super String> consumer) {
        consumer.print("super");
    }

}

class MyCompare implements CompareAble {
    @Override
    public int campare(int a, int b) {
        return a - b;
    }
}

@FunctionalInterface
interface CompareAble {
    int campare(int a, int b);

    //default不影响
    default int jdk8(int a) {
        return a;
    }
}

@FunctionalInterface
interface Consumer<T> {
    void print(T t);
}




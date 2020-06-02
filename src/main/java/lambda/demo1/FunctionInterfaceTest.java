package lambda.demo1;

import org.junit.Test;

/**
 * 函数接口测试
 * Created by Kevin on 2018/2/17.
 * https://www.cnblogs.com/yulinfeng/p/8452379.html
 */
public class FunctionInterfaceTest {

    @Test
    public void testLambda() {
        func(new FunctionInterface() {
            @Override
            public void test() {
                System.out.println("Hello World!");
            }
        });
        //使用Lambda表达式代替上面的匿名内部类
        func(() -> System.out.println("Hello World"));
    }

    private void func(FunctionInterface functionInterface) {
        functionInterface.test();
    }
}
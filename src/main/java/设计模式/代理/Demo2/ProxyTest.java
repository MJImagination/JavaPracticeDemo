package 设计模式.代理.Demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        ComputerFactory cf = new ComputerFactory();

        // 代理对象
        SaleComputer proxy_store = (SaleComputer) Proxy.newProxyInstance(cf.getClass().getClassLoader(), cf.getClass().getInterfaces(), new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("sale")){
                    System.out.println("走过路过别错过");
                    Double money = (Double) args[0];
                    money = money * 1.25;
                    // 使用真实对象调用此方法
                    Object obj = method.invoke(cf, money);
                    System.out.println("手快有手慢没");
                    return obj;
                }else {
                    // 使用真实对象调用此方法
                    Object obj = method.invoke(cf, args);
                    return obj;
                }
            }
        });

        String str = proxy_store.sale(8000);
        System.out.println(str);
    }
}
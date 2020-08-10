// 真实对象实现接口

package 设计模式.代理.Demo2;

public class ComputerFactory implements SaleComputer {
    @Override
    public String sale(double money) {
        System.out.println(money+"厂家价");
        return "我是厂家";
    }
}
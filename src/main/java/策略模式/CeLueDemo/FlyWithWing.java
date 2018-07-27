package 策略模式.CeLueDemo;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/11
 */
public class FlyWithWing implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("用翅膀飞");
    }
}

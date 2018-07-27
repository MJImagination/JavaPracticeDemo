package 策略模式.CeLueDemo;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/11
 */
public class Test {
    public static void main(String[] args){
        Duck duck = new ADuck();
        FlyBehavior flyBehavior = new FlyWithWing();
        duck.setFlyBehavior(flyBehavior);
        duck.performFlyBehavior();
    }
}

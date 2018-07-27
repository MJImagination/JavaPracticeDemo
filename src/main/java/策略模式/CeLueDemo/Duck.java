package 策略模式.CeLueDemo;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/7/11
 */
public abstract class Duck {
    public FlyBehavior flyBehavior;

    public Duck() {
        super();
    }

    public Duck(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public abstract void quack();

    public void performFlyBehavior() {
        flyBehavior.fly();
    }
}


package Super_This理解;

public abstract class Fruit {

    public Discount discount; //折扣

    //设置折扣信息
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    //这里需要子类重写该方法主要是为了得到子类相关信息
    public abstract void showDiscount();

}
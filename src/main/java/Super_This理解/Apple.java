package Super_This理解;

//Apple类
public class Apple extends Fruit{

    public void showDiscount(){
        System.out.println("我是apple");
        this.discount.IshowDiscount();


        /**
         * A是儿子，B是老子。
         *
         * A调用B的x方法：
         * 1）this. （或前面不加，默认表示this.），相当于：A说“用我的x方法”，如果A没有x方法，用的就是他老子B的（等效于super.）；如果A覆写了x方法，用的就是他自己的。
         * 2）super. 相当于：A说“用我老子的x方法”，就直接调用他老子B的x方法。
         */
    }

}
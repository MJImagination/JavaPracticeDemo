package Super_This理解;

public class Supermarket {

    public static void main(String[] args) {

        Fruit apple = new Apple();

        apple.setDiscount(new Discount10());
        apple.showDiscount();
    }

}
package lambda.demo1;

/**
 * @Description: andThen拆解
 * @Author: MJ
 * @Date: Created in 2021/4/28
 */
interface Z {
    void a(int a);

    default Z andThen(Z z){
        a(3);
        z.a(5);
        return null;
    }

}

class zzz{
    public static void main(String[] args) {
        Z z = new ZSon();
        z.andThen(new ZSon());
    }
}

class ZSon implements Z{

    @Override
    public void a(int a) {
        System.out.println(a);
    }
}

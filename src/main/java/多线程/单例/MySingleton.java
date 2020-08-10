package 多线程.单例;

/**
 * https://blog.csdn.net/a78270528/article/details/79700490
 * https://www.cnblogs.com/tangZH/p/10031337.html
 *
 * @Description:
 * @Author: MJ
 * @Date: Created in 2020/6/17
 */
public class MySingleton {
    private int nn= 11;
    private volatile static MySingleton mySingleton = null;

    public static MySingleton getInstance() {
        if (mySingleton == null) {
            synchronized (MySingleton.class) {
                if (mySingleton == null) {
                    mySingleton = new MySingleton();
                }
            }
        }
        return mySingleton;
    }

    public void doSometing(String a) {
        int n = 1;
        System.out.println(n);
        System.out.println(nn);
        if(a.equals("3")){
            n =2;
            nn=22;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(finalI);
                    MySingleton.getInstance().doSometing(finalI +"");
                }
            }).start();
        }

    }
}

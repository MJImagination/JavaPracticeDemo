package 多线程.简单示例;

class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }


    @Override
    public void run() {
//        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println(this.name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        MyThread a = new MyThread("A");
        MyThread b = new MyThread("B");
        a.start();
        b.start();
    }
}
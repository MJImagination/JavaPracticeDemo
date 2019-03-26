package 线程;

import java.util.Map;

public class ExampleCurrentThreadTest {

    public void testInit() throws Exception{
        ExampleCurrentThread thread = new ExampleCurrentThread();
    }

    public void testRun() throws Exception {
        ExampleCurrentThread thread = new ExampleCurrentThread();
        thread.start();
        Thread.sleep(1000);
    }

    public static void main(String[] args){
        ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<Map<String, String>>();
//        threadLocal.get().put()
    }
}
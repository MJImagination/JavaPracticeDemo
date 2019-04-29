package 多线程.生产者消费者.myTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *    日期格式化工具类,使用ThreadLocal解决SimpleDateFormat非线程安全问题
 */
public class DateTools {
    
    private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<>();
    
    public static SimpleDateFormat getSimpleDateFormat(String datePattern) {
        SimpleDateFormat sdf = null;
        sdf = t1.get();
        if(sdf == null) {
            sdf = new SimpleDateFormat(datePattern);
            t1.set(sdf);
        }
        return sdf;
    }

    public static  void main(String[] args){
        System.out.println(DateTools.getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
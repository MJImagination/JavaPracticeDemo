package 日期;

import java.util.Calendar;
import java.util.List;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/11/22
 */
public class Test {
    public static void main(String[] args){
        String[] imageType = {"jpg","jpeg","gif","png","bmp"};
        String aa = "http://192.168.65.211:8090/fileservice/upload/file/2018/11/22/2018112209563869631.jpg";
        System.out.println(aa.substring(aa.lastIndexOf("/")) );
        boolean flag = false;
        for (String s : imageType) {
            System.out.println(aa.endsWith(s));
            return;
        }
    }
}

package 字符串.处理;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/2/11
 */
public class 大小写 {


    public static void main(String[] args) {

        //首字母大写，其他小写
        String aa = "fFDfefERFEF";
        if (null != aa && !"".equals(aa.trim())) {
            System.out.println(aa.substring(0, 1).toUpperCase() + aa.substring(1).toLowerCase());
        }
    }
}

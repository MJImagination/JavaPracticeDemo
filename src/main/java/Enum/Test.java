package Enum;

import java.util.UUID;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/12/21
 */
public class Test {
    public static void main(String[] args){
        System.out.println(ConsumerEnum.valueOf("SEAL"));
        System.out.println(ConsumerEnum.getCode("普通签章"));
        System.out.println(ConsumerEnum.getMsg(1));
        System.out.println(ConsumerEnum.SEAL);

        String aa = "1976-08-24";
        System.out.println( UUID.randomUUID().toString().replace("-", ""));
    }
}

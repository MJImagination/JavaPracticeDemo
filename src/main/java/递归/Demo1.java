package 递归;

import cn.hutool.core.exceptions.ValidateException;

/**
 * @Description:  阶乘
 * @Author: MJ
 * @Date: Created in 2021/4/15
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(jieceng(-1));
    }

    public static int jieceng(int num){
        if(num <0){
            throw new ValidateException("非法参数");
            
        }
        if(num ==1 || num ==0){
            return 1;
        }
        return num * jieceng(num -1);
    }
}

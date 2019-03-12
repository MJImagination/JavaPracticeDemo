package 异常.exception;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/2/26
 */
public class Demo {
    public static void main(String[] args){
        AssertException.notNull(ExceptionCode.ORDER, null, "未找到该业务:" + "dfdf" + "流程实例");
        System.out.println("DFF");
    }
}

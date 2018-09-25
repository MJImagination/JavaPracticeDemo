package 代理模式.简单理解;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/9/25
 */
public class Real implements NetWork{   //真实上网操作
    @Override
    public void browse() {
        System.out.println("上网浏览信息");
    }
}

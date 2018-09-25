package 代理模式.简单理解;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/9/25
 */
public class Proxy implements NetWork{   //代理上网
    private NetWork netWork;

    public Proxy(NetWork netWork){
        this.netWork = netWork;

    }

    public void check(){
        System.out.println("检查用户是否合法");
    }
    @Override
    public void browse() {
        this.check();
        this.netWork.browse();
    }
}

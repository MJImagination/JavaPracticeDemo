package 回调.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/12/23
 */
public class Hotel {
    List<Object> params = new ArrayList<>();

    /**
     * 叫醒服务
     * @param time
     * @param callback
     */
    public boolean serviceWakeUp(String time, CallBackInterface callback){
        System.out.println(time + "时间到了，开始叫醒服务");
        callback.run(params);
        return true;
    }
}

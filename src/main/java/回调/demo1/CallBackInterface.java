package 回调.demo1;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/12/23
 */
public interface CallBackInterface {
    T run(List<Object> params);
}

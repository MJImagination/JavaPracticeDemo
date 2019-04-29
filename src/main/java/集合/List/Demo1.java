package 集合.List;

import com.alibaba.fastjson.JSON;
import util.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/4/17
 */
public class Demo1 {
    public static void main(String[] args){
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(((LinkedList<String>) list).getFirst());
        System.out.println(((LinkedList<String>) list).getFirst());


        Map<String,Object> map=new HashMap<String,Object>();


        map.put("opinion",list);
        map.put("code", "df");
        System.out.println(JSON.toJSONString(map));


        System.out.println(org.apache.commons.lang3.StringUtils.join(list,","));
    }
}

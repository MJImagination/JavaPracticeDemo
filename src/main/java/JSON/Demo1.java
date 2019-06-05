package JSON;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/6/5
 */
public class Demo1 {
    public static void main(String[] args) {
        String jsonStr = "{\"code\":\"1\",\"msg\":\"??1,??0\",\"obj\":{\"errorLits\":[],\"successLits\":[\"e519aac0666711e96281fa1c85620eb5\"]}}";
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONArray jsonArray = JSON.parseObject(jsonStr).getJSONObject("obj").getJSONArray("successLits");
        for (int i = 0; i < jsonArray.size(); i++) {
            System.out.println(jsonArray.getString(i));
        }
    }
}

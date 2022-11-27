package 集合.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/3/6
 */
public class MyHashMap<K, V> extends HashMap<K, V> {
    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }



    public static void main(String[] args){
        String a= "abc";
        String b= "abc";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        Map<String,String> test1 = new HashMap<>();

        test1.put(a,"1");
        test1.put(b,"2");
        System.out.println(test1.size());
        for (String s : test1.keySet()) {
            System.out.println(s);
        }
        ConcurrentHashMap map = new ConcurrentHashMap<>();
        map.put("a","a");
    }
}

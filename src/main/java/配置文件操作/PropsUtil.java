package 配置文件操作;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性文件操作工具类
 */
public class PropsUtil {

    // 配置属性集合
    private static Map<String, String> map = new HashMap<String, String>();
    // 配置文件路径
    private static String filePath = "properties";

    static {
        String classPath = PropsUtil.class.getResource("/").getPath();
        File file = new File(classPath);
        loadProps(file);
    }

    /**
     * 加载属性文件
     */
    private static void loadProps(File file) {
        FileInputStream fis = null;
        if (file.isDirectory()) {
            File[] subfiles = file.listFiles();
            for (File subfile : subfiles) {
                loadProps(subfile);
            }
        } else if (file.isFile()) {
            try {
                fis = new FileInputStream(file);
                Properties pro = new Properties();
                pro.load(fis);
                storeToMap(pro);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 属性文件转为 Map
     */
    private static Map<String, String> storeToMap(Properties props) {
        for (String key : props.stringPropertyNames()) {
            map.put(key, props.getProperty(key));
        }
        return map;
    }

    /**
     * 获取字符型属性
     */
    public static String getString(String key) {
        String value = "";
        if (map.containsKey(key)) {
            value = map.get(key);
        }
        return value;
    }

    /**
     * 获取字符型属性（带有默认值）
     */
    public static String getString(String key, String defalutValue) {
        String value = defalutValue;
        if (map.containsKey(key)) {
            value = map.get(key);
        }
        return value;
    }

}

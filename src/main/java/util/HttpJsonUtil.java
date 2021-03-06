package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpJsonUtil {
    /**
     * Get请求
     *
     * */
    public static String readContentFromGet(String GET_URL) throws IOException{
        URL getUrl = new URL(GET_URL);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//设置编码,否则中文乱码
        StringBuffer lines=new StringBuffer();
        String line=null;
        while ((line = reader.readLine()) != null){
            lines.append(line);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        return lines.toString();
    }
    /**
     * post请求
     *
     * */
    public static String readContentFromPost(String POST_URL, String params) throws IOException{
        URL postUrl = new URL(URLEncoder.encode(POST_URL,"utf-8"));
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        // Post 请求不能使用缓存
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
        // 要注意的是connection.getOutputStream会隐含的进行connect。
        connection.connect();
        DataOutputStream out = new DataOutputStream(connection
                .getOutputStream());
       // String content = "";//"key=j0r53nmbbd78x7m1pqml06u2&type=1&toemail=cngolon@gmail.com" + "&activatecode=" + URLEncoder.encode("中国聚龙", "utf-8");
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
        out.writeBytes(params);
        out.flush();
        out.close(); // flush and close
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//设置编码,否则中文乱码
        String line=null;
        StringBuffer lines=new StringBuffer();
        while ((line = reader.readLine()) != null){
            lines.append(line);
        }
        reader.close();
        // 断开连接
        connection.disconnect();
        return lines.toString();
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        //如果参数中有中文可以使用URLEncoder.encode(params,"utf-8")转义
        //http://api.map.baidu.com/place/v2/search?
           
        String GET_URL = "http://api.map.baidu.com/place/v2/search?ak=F454f8a5efe5e577997931cc01de3974&output=json&query="+URLEncoder.encode("银行","utf-8")+"&page_size=10&page_num=0&scope=1&region="+URLEncoder.encode("北京","utf-8");
        System.out.println(GET_URL);
        try {
            System.out.println(readContentFromGet(GET_URL));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
       
}
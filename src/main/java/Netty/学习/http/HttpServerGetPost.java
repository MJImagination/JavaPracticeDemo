package Netty.学习.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 访问  127.0.0.1:1239/test?gfdg=dfgdfg  get/post请求
 *
 * https://www.cnblogs.com/aspwebchh/p/8300945.html
 */
public class HttpServerGetPost {

    public static void main(String[] arg) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(1239), 0);
        server.createContext("/test", new TestHandler());
        server.start();
    }

    static class TestHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) {
            String response = "hello world";

            try{
                //获得查询字符串(get)
                String queryString =  exchange.getRequestURI().getQuery();
                Map<String,String> queryStringInfo = formData2Dic(queryString);
                //获得表单提交数据(post)
                String postString = IOUtils.toString(exchange.getRequestBody());
                Map<String,String> postInfo = formData2Dic(postString);

                exchange.sendResponseHeaders(200,0);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.write(("parameters: "  + queryStringInfo.toString()).getBytes());
                os.write(("body:" + postString.toString()).getBytes());

                os.close();
            }catch (IOException ie) {

            } catch (Exception e) {

            }
        }
    }

    public static Map<String,String> formData2Dic(String formData ) {
        Map<String,String> result = new HashMap<>();
        if(formData== null || formData.trim().length() == 0) {
            return result;
        }
        final String[] items = formData.split("&");
        //lambda 表达式
        Arrays.stream(items).forEach(item ->{
            final String[] keyAndVal = item.split("=");
            if( keyAndVal.length == 2) {
                try{
                    final String key = URLDecoder.decode( keyAndVal[0],"utf8");
                    final String val = URLDecoder.decode( keyAndVal[1],"utf8");
                    result.put(key,val);
                }catch (UnsupportedEncodingException e) {}
            }
        });
        return result;
    }
}
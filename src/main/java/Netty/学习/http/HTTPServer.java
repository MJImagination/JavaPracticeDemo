package Netty.学习.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统http服务器   输入telnet 127.0.0.1 1239 回车  ctrl + ]  回车（输入内容可见），idea会打印对应内容
 *https://blog.csdn.net/u013412772/article/details/80475159
 * https://www.cnblogs.com/walle2014/p/3815490.html
 */
public class HTTPServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss=new ServerSocket(1239);

            while(true){
                Socket socket=ss.accept();
                BufferedReader bd=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                /**
                 * 接受HTTP请求
                 */
                String requestHeader;
                int contentLength=0;
                while((requestHeader=bd.readLine())!=null&&!requestHeader.isEmpty()){
                    System.out.println(requestHeader);
                    /**
                     * 获得GET参数
                     */
                    if(requestHeader.startsWith("GET")){
                        int begin = requestHeader.indexOf("/?")+2;
                        int end = requestHeader.indexOf("HTTP/");
                        String condition=requestHeader.substring(begin, end);
                        System.out.println("GET参数是："+condition);
                    }
                    /**
                     * 获得POST参数
                     * 1.获取请求内容长度
                     */
                    if(requestHeader.startsWith("Content-Length")){
                        int begin=requestHeader.indexOf("Content-Lengh:")+"Content-Length:".length();
                        String postParamterLength=requestHeader.substring(begin).trim();
                        contentLength=Integer.parseInt(postParamterLength);
                        System.out.println("POST参数长度是："+Integer.parseInt(postParamterLength));
                    }
                }
                StringBuffer sb=new StringBuffer();
                if(contentLength>0){
                    for (int i = 0; i < contentLength; i++) {
                        sb.append((char)bd.read());
                    }
                    System.out.println("POST参数是："+sb.toString());
                }
                //发送回执
                PrintWriter pw=new PrintWriter(socket.getOutputStream());
                
                pw.println("HTTP/1.1 200 OK");
                pw.println("Content-type:text/html");
                pw.println();
                pw.println("<h1>访问成功！</h1>");
                
                pw.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
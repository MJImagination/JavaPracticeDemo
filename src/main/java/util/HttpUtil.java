package util;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author fanl
 * @date 
 */
public class HttpUtil {
	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);

	public static final int TIMEOUTCONNECTION = 30000;		//设置连接超时时间（毫秒）
	public static final int TIMEOUTSOCKET     = 30000;		//设置默认的套接字超时（so_timeout毫秒）。这是为了等待数据超时。
	

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据 如：param=id=1&username=1232&sign=
	 * @return 返回JSON格式的字符串
	 */
	public static String httpRequest(String requestUrl, String requestMethod,
			String outputStr) {
		String responseStr = "";
		if ("GET".equals(requestMethod.toUpperCase())) {
			if (null == outputStr || "".equals(outputStr)) {
				responseStr = doGet(requestUrl);
			} else {
				responseStr = doGet(requestUrl + "?" + outputStr);
			}
		} else if ("POST".equals(requestMethod.toUpperCase())) {
			responseStr = doPost(requestUrl, outputStr);
		}
		return responseStr;
	}

	/**
	 * 发送GET请求
	 * 
	 * @param url
	 *            请求地址 带有参数直接拼接在URL后面
	 * @return 返回JSON格式的字符串
	 */
	private static String doGet(String url) {
		StringBuffer resultBuffer = new StringBuffer();
		try {
			URL localURL = new URL(url);

			URLConnection connection = localURL.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

			httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			httpURLConnection.setConnectTimeout(6000000);   
			httpURLConnection.setReadTimeout(6000000);    

			
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader reader = null;
			String tempLine = null;

			try {
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
				reader = new BufferedReader(inputStreamReader);

				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
				}

			} finally {

				if (reader != null) {
					reader.close();
				}

				if (inputStreamReader != null) {
					inputStreamReader.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}

			}
			int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == 200) {
				log.info("GET请求：" + url + "成功");
			} 
		} catch (Exception e) {
			log.error("GET请求：" + url + "失败，出现异常");
			e.printStackTrace();
		} 
		return resultBuffer.toString();
	}

	/**
	 * 发送POST请求
	 * 
	 * @param url
	 *            请求地址 带有参数直接拼接在URL后面
	 * @param parameterMap
	 *            参数列表
	 * @return 返回JSON格式的字符串
	 */
	private static String doPost(String url, String outputStr) {
		StringBuffer resultBuffer = new StringBuffer();
		int responseCode = 0;//响应码
		HttpURLConnection httpURLConnection = null;
		try {
			URL localURL = new URL(url);

			URLConnection connection = localURL.openConnection();
			httpURLConnection = (HttpURLConnection) connection;

			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			httpURLConnection.setRequestProperty("Content-Length", String
					.valueOf(outputStr.length()));
			httpURLConnection.setConnectTimeout(6000000);   
			httpURLConnection.setReadTimeout(6000000); 

			OutputStream outputStream = null;
			OutputStreamWriter outputStreamWriter = null;
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader reader = null;
			String tempLine = null;

			try {
				outputStream = httpURLConnection.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.flush();

				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
				reader = new BufferedReader(inputStreamReader);

				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
				}

			} finally {

				if (outputStreamWriter != null) {
					outputStreamWriter.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}

				if (reader != null) {
					reader.close();
				}

				if (inputStreamReader != null) {
					inputStreamReader.close();
				}

				if (inputStream != null) {
					inputStream.close();
				}

			}
			responseCode = httpURLConnection.getResponseCode();
			if (responseCode == 200) {
				log.info("POST请求：" + url + "?" + outputStr.toString() + "成功");
			} 
		} catch (Exception e) {
			log.error("POST请求：" + url + "?" + outputStr.toString() + "失败,出现异常");
			e.printStackTrace();
		} 
		return resultBuffer.toString();
	}
	
	/**
	 * 发送POST请求
	 * 
	 * @param url
	 *            请求地址 带有参数直接拼接在URL后面
	 * @param parameterMap
	 *            参数列表
	 * @return 返回JSON格式的字符串
	 */
	public static String doPost(String url, Map<String, Object> parameterMap) {
		String resultBuffer = new String();
		StringBuffer parameterBuffer = new StringBuffer();
		if (parameterMap != null) {
			Iterator<String> iterator = parameterMap.keySet().iterator();
			String key = null;
			Object value = null;
			while (iterator.hasNext()) {
				key = (String) iterator.next();
				if (parameterMap.get(key) != null) {
					value = parameterMap.get(key);
				} else {
					value = "";
				}

				parameterBuffer.append(key).append("=").append(value);
				if (iterator.hasNext()) {
					parameterBuffer.append("&");
				}
			}
		}
		resultBuffer = doPost(url, parameterBuffer.toString());
		return resultBuffer.toString();
	}
	
	
	/**
	 * 转发请求
	 * @param action
	 * @param paramTemp
	 * @param timeoutConnection     设置连接超时时间（毫秒）
	 * @param timeoutSocket			设置默认的套接字超时（so_timeout毫秒）。这是为了等待数据超时。
	 * @return String 请求返回结果
	 */
	public static String buildRequest(String url,Map<String, String> nameValuePair,int timeoutConnection,int timeoutSocket,
			String charset){
		String response = "";
//			//构造HttpClient的实例  
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();  
// 
//			//根据默认超时限制初始化requestConfig
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeoutSocket).setConnectTimeout(timeoutConnection).build();
		
		try {
			HttpPost httpPost = new HttpPost(url);  
			//设置请求器的配置
	        httpPost.setConfig(requestConfig);
			//设置httpPost请求参数  
			if(null!=charset&&(charset.compareToIgnoreCase("GBK")==0||charset.compareToIgnoreCase("GB3212")==0)){
				//设置httpPost请求参数  
				httpPost.setEntity(new UrlEncodedFormEntity(generatNamePair(nameValuePair),"GBK"));  
			}else{
				//设置httpPost请求参数  
				httpPost.setEntity(new UrlEncodedFormEntity(generatNamePair(nameValuePair),"UTF-8"));  
			}
			
			//使用execute方法发送HTTP Post请求，并返回HttpResponse对象  
			HttpResponse httpResponse = httpClient.execute(httpPost);  
				
			int statusCode = httpResponse.getStatusLine().getStatusCode();  
			if(statusCode==HttpStatus.SC_OK){  
				response = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");  
			}else{  
				//response = "{\"code\":\"request fail\",\"msg\":\"请求失败，请正确配置系统登录路径\"}";
				response ="false";
			}
		}catch (ClientProtocolException e) {
			e.printStackTrace();
			//response = "{\"is_success\":\"F\",\"code\":\"ClientProtocolException\",\"msg\":\"请求失败，请正确配置系统登录路径\"}";
			response ="false";
		} catch (IllegalStateException e) {
			e.printStackTrace();
			//response = "{\"is_success\":\"F\",\"code\":\"IllegalStateException\",\"msg\":\"请求失败，请正确配置系统登录路径\"}";
			response ="false";
		} catch (IOException e) {
			e.printStackTrace();
			//response = "{\"is_success\":\"F\",\"code\":\"IOException\",\"msg\":\"请求失败，请正确配置系统登录路径\"}";
			response ="false";
		} catch (Exception e) {
			e.printStackTrace();
			//response = "{\"is_success\":\"F\",\"code\":\""+e.getMessage()+"\",\"msg\":\"请求失败，请正确配置系统登录路径\"}";
			response ="false";
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return response;
	}
	
    /** 
	 * 使用PSOT方式，必须用NameValuePair数组传递参数  
     * 把Parameter类型集合转换成NameValuePair类型集合 
     * @param params 参数集合 
     * @return List<BasicNameValuePair>
     */  
    private static List<NameValuePair> generatNamePair(Map<String, String> paramTemp){ 
        List<NameValuePair> result = new ArrayList<NameValuePair>(0);  
        if(null!=paramTemp&&paramTemp.size()>0){
        	for(Iterator<String> i = paramTemp.keySet().iterator(); i.hasNext();) {
        		Object obj = i.next();
        		result.add(new BasicNameValuePair(obj.toString(), paramTemp.get(obj)));
        	}
        }
        return result;  
    }
    
   
	
	/**
	 * 建立post请求
	 * @param url
	 * @param nameValuePair
	 * @param timeoutConnection     设置连接超时时间（毫秒）
	 * @param timeoutSocket			设置默认的套接字超时（so_timeout毫秒）。这是为了等待数据超时。
	 * @param charset				编码类型
	 * @return String 请求返回结果
	 */
	public static String buildXmlRequest(String url,String params,int timeoutConnection,int timeoutSocket,
			String charset){
		String responseStr = "";
		//获得httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
	        //根据默认超时限制初始化requestConfig
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeoutSocket).setConnectTimeout(timeoutConnection).build();
	 
			HttpPost httpPost = new HttpPost(url);  
			StringEntity entity = null;
			//设置httpPost请求参数  
			if(null!=charset&&(charset.compareToIgnoreCase("GBK")==0||charset.compareToIgnoreCase("GB3212")==0)){
				entity = new StringEntity(params, "GBK"); 
			}else{
				entity = new StringEntity(params,"UTF-8"); 
			}
			entity.setContentEncoding(charset);  
			httpPost.addHeader("Content-Type", "text/xml"); 
			httpPost.setEntity(entity);  
			//设置请求器的配置
	        httpPost.setConfig(requestConfig);
			//使用execute方法发送HTTP Post请求，并返回HttpResponse对象  
			HttpResponse httpResponse = httpClient.execute(httpPost);  
				
			int statusCode = httpResponse.getStatusLine().getStatusCode();  
			if(statusCode==HttpStatus.SC_OK){  
				responseStr = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");  
			}else{  
				responseStr = "{\"error_response\":\"request failed\"}";
			}
		} catch (UnsupportedEncodingException e) {
			responseStr = "{\"is_success\":\"F\",\"error_code\":\"UnrecoverableKeyException\"}";
		} catch (ParseException e) {
			responseStr = "{\"is_success\":\"F\",\"error_code\":\"ParseException\"}";
		} catch (IOException e) {
			responseStr = "{\"is_success\":\"F\",\"error_code\":\"IOException\"}";
		}finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return responseStr;
	}
	
	
	
	
}


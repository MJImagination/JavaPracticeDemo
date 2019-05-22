package 工具类.http;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import 工具类.common.StringUtils;

public class HttpPost {

	private Logger logger = Logger.getLogger(HttpPost.class);

	// 从连接池中获取连接
	private int timeoutConnectionRequest = 1000;
	// 建立连接
	private int timeoutConnect = 2000;
	// 数据传输
	private int timeoutSocketTimeout = 10000;
	// 编码
	private String encode = HTTP.UTF_8;

	/**
	 * 获取从连接池中获取连接的超时时间 默认1秒
	 */
	public int getTimeoutConnectionRequest() {
		return timeoutConnectionRequest;
	}

	/**
	 * 设置从连接池中获取连接的超时时间 默认1秒
	 * @param timeoutConnectionRequest  毫秒
	 */
	public void setTimeoutConnectionRequest(int timeoutConnectionRequest) {
		this.timeoutConnectionRequest = timeoutConnectionRequest;
	}

	/**
	 * 获取建立连接的超时时间 默认2秒
	 */
	public int getTimeoutConnect() {
		return timeoutConnect;
	}

	/**
	 * 设置建立连接的超时时间 默认2秒
	 * @param timeoutConnect 毫秒
	 */
	public void setTimeoutConnect(int timeoutConnect) {
		this.timeoutConnect = timeoutConnect;
	}

	/**
	 * 获取建立连接的超时时间
	 */
	public int getTimeoutSocketTimeout() {
		return timeoutSocketTimeout;
	}

	/**
	 * 设置数据传输的超时时间 默认3秒
	 * @param timeoutSocketTimeout  毫秒
	 */
	public void setTimeoutSocketTimeout(int timeoutSocketTimeout) {
		this.timeoutSocketTimeout = timeoutSocketTimeout;
	}

	public String getEncode() {
		return encode;
	}

	/**
	 * 设置编码 默认UTF-8
	 * @param encode
	 */
	public void setEncode(String encode) {
		this.encode = encode;
	}
	
	/**
	 * 发送post请求，Multi，带附件
	 * @param url 路径
	 * @return
	 */
	public String post(String url, Map<String, String> params, List<Map<String, File>> files) {
		if (StringUtils.isNullOrEmpty(url)) {
			return null;
		}
		logger.info("原始post请求:" + url);
		
		// 返回值
		String returnValue = "";
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		//配置httpPost
		org.apache.http.client.methods.HttpPost httpPost = new org.apache.http.client.methods.HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeoutConnectionRequest)
				.setConnectTimeout(timeoutConnect)
				.setSocketTimeout(timeoutSocketTimeout).build();
		httpPost.setConfig(requestConfig);

		CloseableHttpResponse response = null;
		try {
			
			MultipartEntityBuilder mBuilder = MultipartEntityBuilder.create();
			mBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			if (files != null && files.size() > 0) {
				for(int i = files.size() -1; i >= 0; i--){
					Map<String, File> file = files.get(i);
					String key = file.keySet().iterator().next();
					mBuilder.addBinaryBody(key, file.get(key));
				}
			}
			if (params != null && params.size() > 0) {
				Iterator<String> it = params.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					mBuilder.addTextBody(key, params.get(key));
				}
			}
			mBuilder.setCharset(CharsetUtils.get(encode));
			HttpEntity reqEntity = mBuilder.build();
			httpPost.setEntity(reqEntity);
            
			
			response = client.execute(httpPost);
			// 获取响应消息实体
			HttpEntity entity = response.getEntity();
			// 响应状态
			logger.info("status:" + response.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				logger.info("contentEncoding:" + entity.getContentEncoding());
				returnValue = EntityUtils.toString(entity, encode);
				logger.info("response content:" + returnValue);
			}

		} catch (Exception e) {
			logger.error("post请求提交失败", e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (client != null)
					client.close();
			} catch (Exception e) {
				logger.error("关闭连接错误", e);
			}
		}
		return returnValue;
	}

	/**
	 * 发送post请求，普通post
	 * @param url  路径
	 * @param params  参数
	 * @return
	 */
	public String post(String url, Map<String, String> params) {
		if (StringUtils.isNullOrEmpty(url)) {
			return null;
		}
		logger.info("原始post请求:" + url);
		List<BasicNameValuePair> paramsLists = new ArrayList<BasicNameValuePair>(); 
		if (params != null && params.size() > 0) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				paramsLists.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		// 返回值
		String returnValue = "";
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		//配置httpPost
		org.apache.http.client.methods.HttpPost httpPost = new org.apache.http.client.methods.HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeoutConnectionRequest)
				.setConnectTimeout(timeoutConnect)
				.setSocketTimeout(timeoutSocketTimeout).build();
		httpPost.setConfig(requestConfig);

		CloseableHttpResponse response = null;
		try {
			UrlEncodedFormEntity paramEntity = new UrlEncodedFormEntity(paramsLists, encode);
			httpPost.setEntity(paramEntity);
			
			response = client.execute(httpPost);
			// 获取响应消息实体
			HttpEntity entity = response.getEntity();
			// 响应状态
			logger.info("status:" + response.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				logger.info("contentEncoding:" + entity.getContentEncoding());
				returnValue = EntityUtils.toString(entity, encode);
				logger.info("response content:" + returnValue);
			}

		} catch (Exception e) {
			logger.error("post请求提交失败", e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (client != null)
					client.close();
			} catch (Exception e) {
				logger.error("关闭连接错误", e);
			}
		}
		return returnValue;
	}

	/**
	 * 发送post请求，普通post
	 * @param url  路径
	 * @param params  参数
	 * @return
	 */
	public String postJson(String url, Map<String, Object> params) {
		if (StringUtils.isNullOrEmpty(url)) {
			return null;
		}
		logger.info("原始post请求:" + url);
		
		// 返回值
		String returnValue = "";
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		//配置httpPost
		org.apache.http.client.methods.HttpPost httpPost = new org.apache.http.client.methods.HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeoutConnectionRequest)
				.setConnectTimeout(timeoutConnect)
				.setSocketTimeout(timeoutSocketTimeout).build();
		httpPost.setConfig(requestConfig);


		CloseableHttpResponse response = null;
		try {
			StringEntity s = new StringEntity(JSON.toJSONString(params), Charset.forName("UTF-8"));
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");//发送json数据需要设置contentType
			httpPost.setEntity(s);
			response = client.execute(httpPost);
			// 获取响应消息实体
			HttpEntity entity = response.getEntity();
			// 响应状态
			logger.info("status:" + response.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				logger.info("contentEncoding:" + entity.getContentEncoding());
				returnValue = EntityUtils.toString(entity, encode);
				logger.info("response content:" + returnValue);
			}

		} catch (Exception e) {
			logger.error("post请求提交失败", e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (client != null)
					client.close();
			} catch (Exception e) {
				logger.error("关闭连接错误", e);
			}
		}
		return returnValue;
	}
	
	
	
	
	
	
	
	/**
	 * 发送post请求，普通post
	 * @param url  路径
	 * @param params  参数
	 * @return
	 */
	public String postJson(String url, Object object) {
		if (StringUtils.isNullOrEmpty(url)) {
			return null;
		}
		logger.info("原始post请求:" + url);
		
		// 返回值
		String returnValue = "";
		CloseableHttpClient client = HttpClientBuilder.create().build();
		
		//配置httpPost
		org.apache.http.client.methods.HttpPost httpPost = new org.apache.http.client.methods.HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeoutConnectionRequest)
				.setConnectTimeout(timeoutConnect)
				.setSocketTimeout(timeoutSocketTimeout).build();
		httpPost.setConfig(requestConfig);


		CloseableHttpResponse response = null;
		try {
			StringEntity s = new StringEntity(JSON.toJSONString(object), Charset.forName("UTF-8"));
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");//发送json数据需要设置contentType
			httpPost.setEntity(s);
			response = client.execute(httpPost);
			// 获取响应消息实体
			HttpEntity entity = response.getEntity();
			// 响应状态
			logger.info("status:" + response.getStatusLine());
			// 判断响应实体是否为空
			if (entity != null) {
				logger.info("contentEncoding:" + entity.getContentEncoding());
				returnValue = EntityUtils.toString(entity, encode);
				logger.info("response content:" + returnValue);
			}

		} catch (Exception e) {
			logger.error("post请求提交失败", e);
		} finally {
			try {
				if (response != null)
					response.close();
				if (client != null)
					client.close();
			} catch (Exception e) {
				logger.error("关闭连接错误", e);
			}
		}
		return returnValue;
	}
	
}

package util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

public class HttpGet {

	private Logger logger = LoggerFactory.getLogger(HttpGet.class);

	// 从连接池中获取连接
	private int timeoutConnectionRequest = 1000;
	// 建立连接
	private int timeoutConnect = 5000;
	// 数据传输
	private int timeoutSocketTimeout = 5000;
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
	 * 发送get请求
	 * @param url 路径
	 * @return
	 */
	public String get(String url) {
		if (StringUtils.isNullOrEmpty(url)) {
			return null;
		}
		return get(url,null);
	}

	/**
	 * 发送get请求
	 * @param url  路径
	 * @param params  参数
	 * @return
	 */
	public String get(String url, Map<String, String> params) {
		if (StringUtils.isNullOrEmpty(url)) {
			return null;
		}
		logger.info("原始get请求:" + url);
		if (params != null && params.size() > 0) {
			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (url.indexOf("?") < 0) {
					url += "?";
				} else {
					url += "&";
				}
				url += key;
				url += "=";
				if (params.get(key) != null) {
					url += params.get(key);
				}
			}
		}
		logger.info("参数封装get请求:" + url);
		// 返回值
		String returnValue = "";
		CloseableHttpClient client = HttpClientBuilder.create().build();
		org.apache.http.client.methods.HttpGet httpGet = new org.apache.http.client.methods.HttpGet(url);
		// 配置超时时限
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(timeoutConnectionRequest)
				.setConnectTimeout(timeoutConnect)
				.setSocketTimeout(timeoutSocketTimeout).build();
		httpGet.setConfig(requestConfig);

		CloseableHttpResponse response = null;
		try {
			response = client.execute(httpGet);
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
			logger.error("get请求提交失败", e);
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


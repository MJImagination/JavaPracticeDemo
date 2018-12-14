package util;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.client.methods.HttpGet;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 
 * @author khli org.apache.http请求方式
 * 
 */
public class HttpClientUtil {
	private static final CloseableHttpClient client;
	public static final String CHARSET = "UTF-8";

	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(120000).setSocketTimeout(100000)
					.build();
		//开启重试机制
		client = HttpClientBuilder.create().setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
				.setDefaultRequestConfig(config).build();
		
	}
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * http post请求
	 */
	public static String post(String url, Map<String, String> map) {
		String responseContent = "";
		// 创建默认的httpClient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			//logger.info("HttpClient post uri " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseContent = EntityUtils.toString(entity, "UTF-8");
					//logger.info("HttpClient post response " + responseContent);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/**
	 * http get请求
	 */
	public static String get(String url, Map<String, String> map) {
		String responseContent = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httpget
		HttpGet httpget = getHttpGet(url, map);
		try {
			//logger.info("HttpClient get uri " + httpget.getURI());
			// 执行get请求
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// 打印响应状态
				//logger.info("HttpClient get response statusLine " + response.getStatusLine());
				if (entity != null) {
					responseContent = EntityUtils.toString(entity, "UTF-8");
					// 打印响应内容长度
					//logger.info("HttpClient get response content length " + entity.getContentLength());
					// 打印响应内容
					//logger.info("HttpClient get response content " + responseContent);
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	private static HttpGet getHttpGet(String url, Map<String, String> map) {
		String params = "";
		if (map != null && map.size() > 0) {
			for (Entry<String, String> entry : map.entrySet()) {
				params += "&" + entry.getKey() + "=" + entry.getValue();
			}
			if (params.length() > 0) {
				params = params.substring(1);
			}
		}
		HttpGet httpget = new HttpGet(url + "?" + params);
		return httpget;
	}

	/**
	 * postContent请求
	 */
	public static String postContent(String url, String content) {
		String responseContent = "";
		try {
			URL httpurl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) httpurl.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/raw");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setConnectTimeout(3000);
			connection.getOutputStream().write(content.getBytes("UTF-8"));
			connection.getOutputStream().flush();
			connection.getOutputStream().close();
			int resCode = connection.getResponseCode();
			if (resCode == 200) {
				responseContent = getString(connection.getInputStream());
				//logger.info("responseContent:" + responseContent);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseContent;
	}
	
	public static String postByString(String url, String param) throws ClientProtocolException, IOException {
		String result = null;
		try {
			  HttpPost httpPost = new HttpPost(url);
			  httpPost.setHeader("Content-Type", "application/json; charset=utf-8"); 
			  StringEntity httpPostEntity = new StringEntity(param, "UTF-8");
			  httpPost.setEntity(httpPostEntity);
			  CloseableHttpResponse response = client.execute(httpPost);
			  int statusCode = response.getStatusLine().getStatusCode();
				if (statusCode != 200) {
					httpPost.abort();
					throw new RuntimeException("HttpClient,error status code :" + statusCode);
				}
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "utf-8");
				}
				EntityUtils.consume(entity);
				response.close();
		} catch (Exception e) {
			e.printStackTrace();;
		} 
		return result;
	}

	private static String getString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	

    /**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam){
        return httpPost(url, jsonParam, false);
    }

    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse){
        //post请求返回结果
        DefaultHttpClient httpClient = new DefaultHttpClient();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "UTF-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSON.parseObject(str);
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return jsonResult;
    }

}

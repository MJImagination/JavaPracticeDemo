package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SendSmsUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(SendSmsUtils.class);
//	private static final String msg_url = Message.getMessage("msg_url");
//	//private static final String msg_url = "http://192.168.101.9/webapps/wlwalarm";
//
//	public static void sendSms(String content, String phone) {
//		String regExp = "^[1][0-9]{10}$";//1开头并且有11位
//		//phone为null或者不匹配则退出
//		if (StringUtils.isNullOrEmpty(phone) || !phone.matches(regExp)) return;
//
//		//TODO 将来插入到短信表中
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("phone", phone);
//		map.put("content", content);
//		String result = HttpClientUtil.post(msg_url, map);
//		logger.info("告警信息发送短信，返回结果:" + result);
//	}
}

package 工具类.common;

import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;



/**
 * 转换编码类
 * @author weijl
 */
public class Encode {
	
	private static Logger logger = Logger.getLogger(Encode.class);
	
	/**
	 * iso 转 GBK
	 * @param s
	 * @return
	 */
	public static String isoToGbk(String s) {
		if (s == null)
			return null;
		logger.info("iso : "+ s);
		try {
			byte[] c = s.getBytes("ISO8859-1");
			String tm = new String(c, "GBK");
			logger.info("gbk : "+ tm);
			return tm;
		} catch (UnsupportedEncodingException ex) {
			logger.error("编码转换出错",ex);
			return "";
		}

	}
	
	/**
	 * GBK 转 ISO
	 * @param s
	 * @return
	 */
	public static String gbkToIso(String s) {
		if (s == null)
			return null;
		logger.info("gbk : "+ s);
		try {
			byte[] c = s.getBytes("GBK");
			String tm = new String(c, "ISO8859-1");
			logger.info("iso : "+ tm);
			return tm;
		} catch (UnsupportedEncodingException ex) {
			logger.error("编码转换出错",ex);
			return "";
		}

	}

	/**
	 * ISO 转 UTF8
	 * @param s
	 * @return
	 */
	public static String isoToUTF8(String s) {
		if (s == null)
			return null;
		logger.info("iso : "+ s);
		try {
			byte[] c = s.getBytes("ISO8859-1");
			String tm = new String(c, "UTF-8");
			logger.info("utf-8 : "+ tm);
			return tm;
		} catch (UnsupportedEncodingException ex) {
			logger.error("编码转换出错",ex);
			return "";
		}

	}

	/**
	 * UTF8 转 GBK
	 * @param s
	 * @return
	 */
	public static String utf8ToGbk(String s) {
		if (s == null)
			return null;
		logger.info("UTF8 : "+ s);
		try {
			byte[] c = s.getBytes("UTF-8");
			String tm = new String(c, "GBK");
			logger.info("GBK : "+ tm);
			return tm;
		} catch (UnsupportedEncodingException ex) {
			logger.error("编码转换出错",ex);
			return "";
		}
	}
}

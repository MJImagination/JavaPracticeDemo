package 工具类.crypt;
import java.security.MessageDigest;
import org.apache.log4j.Logger;

public class MD5 {
	
	private static Logger logger = Logger.getLogger(MD5.class);
	
	/**
	 * 计算MD5值
	 * 
	 * @param value 需要计算的字符串
	 * @return 计算后的MD5值，32位
	 * @author weijl
	 */
	public static String MD5(String value) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = value.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			logger.error("计算MD5错误",e);
			return "";
		}
	}

}

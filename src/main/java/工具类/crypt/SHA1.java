package 工具类.crypt;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class SHA1 {
	
	private static Logger logger = Logger.getLogger(MD5.class);
	
	/***
	 * SHA加密 生成40位SHA码
	 * 
	 * @param 待加密字符串
	 * @return 返回40位SHA码
	 */
	public static String SHA1(String inStr) {
		
		try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA");
            digest.update(inStr.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (Exception e) {
        	logger.error("计算SH1错误",e);
        }
        return "";
	}

}

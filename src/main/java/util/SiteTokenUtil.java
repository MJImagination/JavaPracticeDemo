package util;

/**
 * @ClassName: DESUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author fanl
 * @date 2017-4-7 下午4:33:27
 */

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * DES加密和解密工具,可以对字符串进行加密和解密操作 。
 */
public class SiteTokenUtil {
	
	
	/** 字符串默认键值 */
	private static String strDefaultKey = "HESC-DES";
	
	
	/** 加密工具 */
	private static  Cipher encryptCipher = null;
	
	
	/** 解密工具 */
	private static  Cipher decryptCipher = null;

	/**
	 * 默认构造方法，使用默认密钥
	 * 
	 * @throws Exception
	 */
	public SiteTokenUtil() {
		this(strDefaultKey);
	}
	
	
	/**
	 * 指定密钥构造方法
	 * 
	 * @param strKey
	 *            指定的密钥
	 * @throws Exception
	 */
	public SiteTokenUtil(String strKey)  {
		try {
			Key key = getKey(strKey.getBytes());
			encryptCipher = Cipher.getInstance("DES");
			encryptCipher.init(Cipher.ENCRYPT_MODE, key);
			decryptCipher = Cipher.getInstance("DES");
			decryptCipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
	 * 
	 * @param arrBTmp
	 *            构成该字符串的字节数组
	 * @return 生成的密钥
	 * @throws Exception
	 */
	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	
	/**
	 * @throws Exception
	 * 
	 * @Title: getToken
	 * @Description: TODO 解密token
	 * @param @param requestToken
	 * @param @return 设定参数
	 * @return String 返回类型
	 * @throws
	 */
	public String getToken(String requestToken) {
		String rToken= "false";
		if(null != requestToken && !"".equals(requestToken)){
			try {
				new SiteTokenUtil();
				String decryptStr =  SiteTokenUtil.decrypt(requestToken);
				String token = decryptStr.substring(13, decryptStr.length());
				Long originTime = Long.valueOf(decryptStr.substring(0, 13));
				if(System.currentTimeMillis() - originTime < 180000){
				    rToken = token;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
       	return rToken;
	}


	/**
	 * 解密字符串
	 * 
	 * @param strIn
	 *            需解密的字符串
	 * @return 解密后的字符串
	 * @throws Exception
	 */
	public static String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}
	
	/**
	 * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 * @throws Exception
	 *             本方法不处理任何异常，所有异常全部抛出
	 * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
	 */
	public static  byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	/**
	 * 解密字节数组
	 * 
	 * @param arrB
	 *            需解密的字节数组
	 * @return 解密后的字节数组
	 * @throws Exception
	 */
	public static  byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}
}
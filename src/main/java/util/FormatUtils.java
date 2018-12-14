package util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormatUtils {
	public FormatUtils() {
	}

	public static String getPercent(String p1, String p2) {
		String str;
		if (p2.equals("0")) {
			p2 = "1";
		}
		double p3 = Double.valueOf(p1).doubleValue()
				/ Double.valueOf(p2).doubleValue();
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(1);
		str = nf.format(p3);
		return str;
	}
	
	public static String getFormatPercent(String numerator,
			String denominator){
		if (numerator == null || denominator == null || "".equals(numerator)
				|| "".equals(denominator))
			return "0.0%";

		try {
			int n = Integer.parseInt(numerator);
			int d = Integer.parseInt(denominator);
			if (d == 0 || n == 0) {
				return "0.0%";
			}
			DecimalFormat df = new DecimalFormat();
			df.applyPattern("#0.0%");
			return df.format((n * 1.0) / d);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/* 
	 * 毫秒转化时分秒毫秒 
	 */  
	public static String formatTime(Long ms) {  
	    Integer ss = 1000;  
	    Integer mi = ss * 60;  
	    Integer hh = mi * 60;  
	    Integer dd = hh * 24;  
	  
	    Long day = ms / dd;  
	    Long hour = (ms - day * dd) / hh;  
	    Long minute = (ms - day * dd - hour * hh) / mi;  
	    Long second = (ms - day * dd - hour * hh - minute * mi) / ss;  
	      
	    StringBuffer sb = new StringBuffer();  
	    if(day > 0) {  
	        sb.append(day+"天");  
	    }  
	    if(hour > 0) {  
	        sb.append(hour+"小时");  
	    }  
	    if(minute > 0) {  
	        sb.append(minute+"分");  
	    }  
	    if(second > 0) {  
	        sb.append(second+"秒");  
	    }
	    return sb.toString();  
	}  
}

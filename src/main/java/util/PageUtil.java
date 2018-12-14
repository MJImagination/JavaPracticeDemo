package util;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {
	
	public static String getParamsString(Map<String, Object> params){
		String paramsString = "";
		if(params!=null&&params.size()>0){
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				
				if(entry.getValue()==null||entry.getValue().toString().contains("%")){
					continue;
				}
				paramsString += "~"+entry.getKey()+"*"+entry.getValue();
			}
			paramsString = paramsString.substring(1, paramsString.length());
		}
		return paramsString;
	}
	
	public static Map<String, Object> setParamsString(String ParamsString){
		Map<String, Object> params = new HashMap<String, Object>();
		String[] paramseach = ParamsString.split("~");
		for (String parameach : paramseach) {
			String[] keyvalue = parameach.split("\\*");
			params.put(keyvalue[0], keyvalue[1]);
		}
		return params;
	}

}

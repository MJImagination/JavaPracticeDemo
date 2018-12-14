package util;

import com.alibaba.fastjson.JSON;

public class Msg {
	
	//操作结果编码：成功1，失败0
	private String code;
	//操作结果提示
	private String msg;
	//返回值
	private Object obj;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	/**
	 * 转化成JSON格式的数据
	 */
	@Override
	public String toString() { 
		return JSON.toJSONString(this);
	}

}

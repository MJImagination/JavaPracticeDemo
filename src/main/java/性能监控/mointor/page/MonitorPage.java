package 性能监控.mointor.page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import com.alibaba.fastjson.JSON;
import util.DateUtils;
import 工具类.common.StringUtils;
import 性能监控.mointor.MonitorShowServlet;
import 性能监控.mointor.pojo.CountItem;
import 性能监控.mointor.pojo.FunItem;
import 性能监控.mointor.pojo.Monitor;


public class MonitorPage {
	/**
	 * 页面展示
	 */
   public static byte[] getDisplayPage(String encodeing) throws Exception{
		StringBuffer sbHtml = new StringBuffer();
		
		sbHtml.append("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encodeing+"\">");
		sbHtml.append("<style type=\"text/css\">");
		sbHtml.append("*{font-size: 12px;padding:0;}table{border-collapse: collapse;border: 1px solid #C7CBD2;width: 100%;margin-top: 5px;}  .trtitle{background-color: #d2d6de;font-weight: bold;} .trback{background:#d9d9d9;}  .titleinfo{line-height:30px;background-color: #d2d6de; padding: 5px;}td{text-align: right;line-height: 20px;padding: 0 3px;border: 1px solid #C7CBD2;}td.tleft{text-align: left;}#concurrent{width: 100%;height:400px;margin-top: 5px;}");
		sbHtml.append("</style>");
		sbHtml.append("<title>系统性能监控 Monitor</title>");
		sbHtml.append("</head><body>");

		Monitor.flushData();
		
		sbHtml.append("<div id=\"concurrent\"></div>");
		
		sbHtml.append("<div class=\"titleinfo\">队列长度：");
		sbHtml.append(Monitor.getHistoryList().size());
		sbHtml.append("/");
		sbHtml.append(Monitor.getCOUNTSIZE());
		sbHtml.append("&nbsp;&nbsp;&nbsp;超时过滤：");
		sbHtml.append(Monitor.getINITTIME());
		sbHtml.append("ms</div>");
		

		List<CountItem> countList = Monitor.getCountList();
		sbHtml.append("<table><tr class=\"trtitle\"><td class=\"tleft\">方法名</td><td width=\"100\">累积调用次数</td><td width=\"100\">平均时间(ms)</td><td width=\"100\">最长时间(ms)</td><td width=\"100\">最短时间(ms)</td></tr>");
		for (int i = 0; i < countList.size(); i++) {
			CountItem c = countList.get(i);
			if(i%2==0){
				sbHtml.append("<tr  class=\"trback\"><td class=\"tleft\">");
			}else{
				sbHtml.append("<tr><td class=\"tleft\">");
			}
			
			sbHtml.append(c.getFun());
			sbHtml.append("</td><td>");
			sbHtml.append(c.getCount());
			sbHtml.append("</td><td>");
			sbHtml.append(c.getAvr());
			sbHtml.append("</td><td>");
			sbHtml.append(c.getMax());
			sbHtml.append("</td><td>");
			sbHtml.append(c.getMin());
			sbHtml.append("</td></tr>");
		}
		sbHtml.append("</table>");

		List<FunItem> topHistoryList = new ArrayList<FunItem>();
		topHistoryList.addAll(Monitor.getHistoryList());
		Collections.sort(topHistoryList);
		sbHtml.append("<table><tr class=\"trtitle\"><td class=\"tleft\">方法名(最耗时TOP50)</td><td width=\"100\">用时(ms)</td><td class=\"tleft\">参数</td><td width=\"100\">IP</td><td width=\"120\">发生时间</td></tr>");
		int topSize = (topHistoryList.size() < 50) ? topHistoryList.size() : 50;
		for (int i = 0; i < topSize; ++i) {
			FunItem f = (FunItem) topHistoryList.get(i);
			if(i%2==0){
				sbHtml.append("<tr  class=\"trback\"><td class=\"tleft\">");
			}else{
				sbHtml.append("<tr><td class=\"tleft\">");
			}
			sbHtml.append(f.getFun());
			sbHtml.append("</td><td>");
			sbHtml.append(f.getTime());
			sbHtml.append("</td><td class=\"tleft\">");
			sbHtml.append(f.getParam());
			sbHtml.append("</td><td>");
			sbHtml.append(f.getIp());
			sbHtml.append("</td><td>");
			sbHtml.append(DateUtils.getFormatDateTime(f.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
			sbHtml.append("</td></tr>");
		}
		sbHtml.append("</table>");

		List<FunItem> historyList = Monitor.getHistoryList();
		sbHtml.append("<table><tr class=\"trtitle\"><td class=\"tleft\">方法名(实时记录)</td><td width=\"100\">用时(ms)</td><td class=\"tleft\">参数</td><td width=\"100\">IP</td><td width=\"120\">发生时间</td></tr>");
		int historySize = (historyList.size() < 100) ? historyList.size() : 100;
		for (int i = historySize-1; i >= 0; --i) {
			FunItem f = (FunItem) historyList.get(i);
			if(i%2==0){
				sbHtml.append("<tr  class=\"trback\"><td class=\"tleft\">");
			}else{
				sbHtml.append("<tr><td class=\"tleft\">");
			}
			sbHtml.append(f.getFun());
			sbHtml.append("</td><td>");
			sbHtml.append(f.getTime());
			sbHtml.append("</td><td class=\"tleft\">");
			sbHtml.append(f.getParam());
			sbHtml.append("</td><td>");
			sbHtml.append(f.getIp());
			sbHtml.append("</td><td>");
			sbHtml.append(DateUtils.getFormatDateTime(f.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));
			sbHtml.append("</td></tr>");
		}
		sbHtml.append("</table>");
		
		//加载图表所需要的js文件
		URL comUrl = MonitorShowServlet.class.getResource("/concurrent.js");
		URL grayUrl = MonitorShowServlet.class.getResource("/gray.js");
		URL highUrl = MonitorShowServlet.class.getResource("/highcharts.js");
		URL jqueryUrl = MonitorShowServlet.class.getResource("/jQuery-2.1.4.min.js");
		
		Map<Object, Object> map=new HashMap<Object, Object>();
		map.put("allList", Monitor.getAllCountList());
		map.put("timeList",  Monitor.getTimeList());
		map.put("validList",Monitor.getValidCountList());
		map.put("overList",Monitor.getOverCountList());
		String mapstr= JSON.toJSONString(map);
		
		sbHtml.append("\n");
		sbHtml.append("<script>");
		sbHtml.append(getFileStr(jqueryUrl));
		sbHtml.append("\n");
		sbHtml.append(getFileStr(highUrl));
		sbHtml.append("\n");
		sbHtml.append(getFileStr(grayUrl));
		sbHtml.append("\n");
		sbHtml.append(getFileStr(comUrl));
		sbHtml.append("\n");
		sbHtml.append("$(function() {getconcurrent("+mapstr+");});");
		sbHtml.append("\n");
		sbHtml.append("</script>");
		sbHtml.append("\n");
		sbHtml.append("</body></html>");
		
		return sbHtml.toString().getBytes(encodeing);  
		
     }
  
  
     /**
	 * 设置页面参数
	 * countSize 最大长度
	 * initTime 阀值时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
    public static byte[] setPageSet(String encodeing,String countSize,String initTime) throws IOException{
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append("<!doctype html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset="+encodeing+"\">");
        sbHtml.append("<title>设置结果</title></head><body style=\"text-align:center;padding:50px;\">");
        
		boolean isOK = false;
		if (!StringUtils.isNullOrEmpty(countSize) && !StringUtils.isNullOrEmpty(initTime)) {
			try {
				Monitor.initQueue(Integer.valueOf(countSize),Integer.valueOf(initTime));
				isOK = true;
			} catch (Exception e) {
			}
		}
		if (isOK) {
			sbHtml.append("<h1>OK</h1>");
		} else {
			sbHtml.append("<h1>请输入正确的参数</h1>");
		}

		sbHtml.append("<div>");
		sbHtml.append(DateUtils.getCurFormatDateTime());
		sbHtml.append("</div>");
		sbHtml.append("</body></html>");
		return sbHtml.toString().getBytes(encodeing);
    }
  
    /**
	 * 获取文件中的内容，返回字符串
	 * @param url
	 * @param encodeing
	 * @return
	 * @throws Exception
	 */
	public static String getFileStr(URL url) throws Exception {
		URLConnection urlConnection = url.openConnection();
		InputStream inputStream = urlConnection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		
		return sb.toString();
	}
}

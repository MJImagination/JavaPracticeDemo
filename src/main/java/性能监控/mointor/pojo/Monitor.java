package 性能监控.mointor.pojo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import com.hesc.trundle.common.DateUtils;
import com.hesc.trundle.message.Message;

public class Monitor {
	
	//队列长度
	private static int COUNTSIZE = Integer.valueOf(Message.getConfig("fun_monitor_countsize", "100000"));
	//并发队列长度(12个小时数据，一分钟统计一次并发数)
	private static int RENTSIZE = Integer.valueOf(Message.getConfig("fun_monitor_rentsize", "720"));
	//初始过滤，只有当执行时间大于该值的数字才列入监控
	private static long INITTIME = Long.valueOf(Message.getConfig("fun_monitor_inittime", "500"));
	//规定时间内的所有历史记录
	private static Queue<FunItem> HISTORY = new ArrayBlockingQueue<FunItem>(COUNTSIZE);
	//统计概况
	private static List<CountItem> countList = new ArrayList<CountItem>();
	//历史记录
	private static List<FunItem> historyList = new ArrayList<FunItem>();
	//并发监控记录
	private static TreeMap<String,ConcurrentItem> CONCURRENT = new TreeMap<String,ConcurrentItem>();
	//每日并发记录总数
	private static TreeMap<String,Integer> allCountDayMap = new TreeMap<String,Integer>();
	//每日并发有效次数
	private static TreeMap<String,Integer> validCountDayMap = new TreeMap<String,Integer>();
	//每日并发超时次数
	private static TreeMap<String,Integer> overCountDayMap = new TreeMap<String,Integer>();
	//并发数时间
	private static List<String>  timeList = new ArrayList<String>();
	//并发数总次数
	private static List<Integer> allCountList = new ArrayList<Integer>();
	//并发有效次数
	private static List<Integer> validCountList = new ArrayList<Integer>();
	//并发超时次数
	private static List<Integer> overCountList = new ArrayList<Integer>();
	/**
	 * 增加记录
	 * @param item
	 */
	public synchronized static void add(FunItem item){
		//并发监控
		addConcurrent(item);
		//性能监控
		if(item.getTime() <= INITTIME) return;
		if(HISTORY.size() == COUNTSIZE){
			HISTORY.poll();
		}
		HISTORY.offer(item);
		
	}
	
	/**
	 * 增加并发记录，
	 * 
	 * @param item
	 * @throws ParseException
	 */
	public static void addConcurrent(FunItem item){
		long createTime = item.getCreatetime();
		String strCreatetime = DateUtils.getFormatDateTime(item.getCreatetime(), "yyyy-MM-dd HH:mm");
		String strDayCreatetime = DateUtils.getFormatDateTime(item.getCreatetime(), "yyyy-MM-dd");
		if(CONCURRENT.keySet().contains(strCreatetime)){
			ConcurrentItem temp = CONCURRENT.get(strCreatetime);
			temp.setTotal(temp.getTotal() + 1);
			if(!isStaticUrl(item.getFun())){
				temp.setNum(temp.getNum() + 1);
				validCountDayMap.put(strDayCreatetime, validCountDayMap.get(strDayCreatetime)==null?1:validCountDayMap.get(strDayCreatetime)+1);
			}
			if(item.getTime()>INITTIME){
				temp.setOvertimemun(temp.getOvertimemun() + 1);
				overCountDayMap.put(strDayCreatetime, overCountDayMap.get(strDayCreatetime)==null?1:overCountDayMap.get(strDayCreatetime)+1);
			}
			allCountDayMap.put(strDayCreatetime, allCountDayMap.get(strDayCreatetime)+1);
		}else{
			if(CONCURRENT.size() > 0){
				String lastTime = CONCURRENT.lastKey();
				long longTime = (createTime - DateUtils.getLongFromDateTime(lastTime+":00") ) / 60000;
				if(longTime > 1){
					for(int i = 0; i < longTime - 1; i++){
						ConcurrentItem nItem = new ConcurrentItem();
						String cTime = DateUtils.getFormatDateTime((DateUtils.getLongFromDateTime(lastTime+":00") + 60000*(i+1)), "yyyy-MM-dd HH:mm");
						if(CONCURRENT.size() >= RENTSIZE) CONCURRENT.remove(CONCURRENT.firstKey());
						CONCURRENT.put(cTime, nItem);
					}
				}
			}
			
			ConcurrentItem temp = new ConcurrentItem();
			temp.setTotal(1);
			if(!isStaticUrl(item.getFun())){
				temp.setNum(1);
				validCountDayMap.put(strDayCreatetime, validCountDayMap.get(strDayCreatetime)==null?1:validCountDayMap.get(strDayCreatetime)+1);
			}
			if(item.getTime()>INITTIME){
				temp.setOvertimemun(1);
				overCountDayMap.put(strDayCreatetime, overCountDayMap.get(strDayCreatetime)==null?1:overCountDayMap.get(strDayCreatetime)+1);
			}
			if(CONCURRENT.size() >= RENTSIZE) CONCURRENT.remove(CONCURRENT.firstKey());
			CONCURRENT.put(strCreatetime, temp);
			allCountDayMap.put(strDayCreatetime, allCountDayMap.get(strDayCreatetime)==null?1:allCountDayMap.get(strDayCreatetime)+1);
		}
	}

	/**
	 * 清空记录(不清空历史数据，只清空展示数据)
	 * @param item
	 */
	public static void clear(){
		countList.clear();
		historyList.clear();
		timeList.clear();
		allCountList.clear();
		validCountList.clear();
		overCountList.clear();
	}
	/**
	 * 获取展示概况
	 * @return
	 */
	public static void flushData(){
		countList.clear();
		historyList.clear();
		Map<String,CountItem> counts = new HashMap<String,CountItem>();
		Queue<FunItem> tempqueue = new ArrayBlockingQueue<FunItem>(COUNTSIZE);
		tempqueue.addAll(HISTORY);
		FunItem funItem = null;
		while((funItem = tempqueue.poll()) != null){
			historyList.add(funItem);
			if(counts.keySet().contains(funItem.getFun())){
				CountItem count = counts.get(funItem.getFun());
				count.setAvr( (count.getAvr() * count.getCount() + funItem.getTime()) / (count.getCount() + 1) );
				if(funItem.getTime() > count.getMax()){
					count.setMax(funItem.getTime());
				}else if(funItem.getTime() < count.getMin()){
					count.setMin(funItem.getTime());
				}
				count.setCount(count.getCount() + 1);
			}else{
				CountItem count = new CountItem();
				count.setFun(funItem.getFun());
				count.setCount(1);
				count.setAvr(funItem.getTime());
				count.setMax(funItem.getTime());
				count.setMin(funItem.getTime());
				counts.put(funItem.getFun(), count);
			}
		}
		
		Iterator<String> it = counts.keySet().iterator();
		while(it.hasNext()){
			countList.add(counts.get(it.next().toString()));
		}
		Collections.sort(countList);
		
		flushConcurrent();
	}
	
	
	
	/**
	 * 每分钟的并发数 从Concurrent队列历史数据中获取
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static void flushConcurrent() {
		timeList.clear();
		allCountList.clear();
		validCountList.clear();
		overCountList.clear();
		Iterator<String> keys = CONCURRENT.keySet().iterator();
		ConcurrentItem rentItem = null;
		while(keys.hasNext()){
			String key = keys.next();
			timeList.add(key);
			rentItem = CONCURRENT.get(key);
			allCountList.add(rentItem.getTotal());
			validCountList.add(rentItem.getNum());
			overCountList.add(rentItem.getOvertimemun());
		}
	}
	
	/**
	 * 如果是请求静态资源，则返回 ture 否则返回false
	 * @param url
	 * @return
	 */
	private static boolean isStaticUrl(String url){
		url = url.toLowerCase();
		if (url.endsWith(".js") || url.endsWith(".jpg")
				|| url.endsWith(".jpeg") || url.endsWith(".gif")
				|| url.endsWith(".png") || url.endsWith(".bmp")
				|| url.endsWith(".css")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<CountItem> getCountList() {
		return countList;
	}

	public static List<FunItem> getHistoryList() {
		return historyList;
	}
	
	public static long getINITTIME() {
		return INITTIME;
	}
	

	public static int getCOUNTSIZE() {
		return COUNTSIZE;
	}


	public static List<String> getTimeList() {
		return timeList;
	}

	public static List<Integer> getAllCountList() {
		return allCountList;
	}

	public static List<Integer> getValidCountList() {
		return validCountList;
	}

	
	public static List<Integer> getOverCountList() {
		return overCountList;
	}


	public static TreeMap<String, Integer> getAllCountDayMap() {
		return allCountDayMap;
	}

	public static void setAllCountDayMap(TreeMap<String, Integer> allCountDayMap) {
		Monitor.allCountDayMap = allCountDayMap;
	}

	
	public static TreeMap<String, Integer> getValidCountDayMap() {
		return validCountDayMap;
	}

	public static void setValidCountDayMap(TreeMap<String, Integer> validCountDayMap) {
		Monitor.validCountDayMap = validCountDayMap;
	}

	public static TreeMap<String, Integer> getOverCountDayMap() {
		return overCountDayMap;
	}

	public static void setOverCountDayMap(TreeMap<String, Integer> overCountDayMap) {
		Monitor.overCountDayMap = overCountDayMap;
	}

	/**
	 * 初始化队列
	 * @param countSize 队列长度
	 * @param initTime 初始过滤，只有当执行时间大于该值的数字才列入监控
	 */
	public static void initQueue(int countSize, long initTime){
		COUNTSIZE = countSize;
		INITTIME = initTime;
		HISTORY = new ArrayBlockingQueue<FunItem>(COUNTSIZE);
	}
}

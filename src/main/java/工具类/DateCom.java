package 工具类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * 查询所用到的时间格式
 * 
 * */
public class DateCom {
	// 获取当前天的前31个天数yyyy-mm-dd
	public static final String[] getFormatDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -30);

		Date date = c.getTime();
		String df = format.format(date);// 获取第前6天的年月日
		String[] datestr = new String[31];
		datestr[0] = df;
		for (int i = 0; i < 30; i++) {
			c.add(Calendar.DAY_OF_MONTH, +1);
			Date date2 = c.getTime();
			String dfs = format.format(date2);
			datestr[i + 1] = dfs;
		}
		return datestr;
	}

	// 获取当前月的前6个月的年月份yyyy-mm
	public static final String[] getFormatDateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -5);

		Date date = c.getTime();
		String df = format.format(date);// 获取第前6个月的年月份
		String[] datestr = new String[6];// +1 包含当前本月 yyyy-mm
		datestr[0] = df;

		for (int i = 0; i < 5; i++) {
			c.add(Calendar.MONTH, +1);
			Date date2 = c.getTime();
			String dfs = format.format(date2);
			datestr[i + 1] = dfs;
		}
		return datestr;
	}

    // 获取当前月的前1个月的年月份yyyy-mm
    public static final String getLastDateTime(int index) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();

        c.add(Calendar.MONTH, index);
        Date date2 = c.getTime();
        return format.format(date2);
    }

	// 获取当前年的前6年 yyyy
	public static final String[] getFormatYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -5);

		Date date = c.getTime();
		String df = format.format(date);// 获取前第6年时间
		String[] yeartr = new String[6];
		yeartr[0] = df;

		for (int i = 0; i < 5; i++) {
			c.add(Calendar.YEAR, +1);
			Date date2 = c.getTime();
			String dfs = format.format(date2);
			// System.out.println("vvvvvvvvv:"+dfs);
			yeartr[i + 1] = dfs;
		}
		return yeartr;
	}

	// 获取当前年 yyyy
	public static final String getThisYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		String year = format.format(date);
		return year;
	}

	// 获取前一年 yyyy
	public static final String getLastYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -1);
		Date date = c.getTime();
		String year = format.format(date);
		return year;
	}

	// 获取前前一年 yyyy
	public static final String getLastTwoYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.YEAR, -2);
		Date date = c.getTime();
		String year = format.format(date);
		return year;
	}

	public static String getHHmm() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR));
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		return format.format(date);
	}

	public static String getyyyyMMdd(int dateIndex) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + dateIndex);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		return format.format(date);
	}

    public static Date getyyyyMMddDate(int dateIndex) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + dateIndex);
        return calendar.getTime();
    }

	public static String getWeekOfDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static int getDaysOfMonth(String date) {
		String today = getyyyyMMdd(0).replace("-", "/");
	    if(date.equals(today.substring(0, 7))) {
        	return Integer.parseInt(today.substring(8,10));
        } 
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
		try {
			calendar.setTime(format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}

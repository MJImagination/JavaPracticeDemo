package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtils {
    private static Calendar calendar = Calendar.getInstance();

    public DateUtils() {
    }

    public static final String getFormatDateTime(Date date, String format) {
        SimpleDateFormat mydate = new SimpleDateFormat(format);
        return mydate.format(date);
    }

    public static final String getFormatDateTime(long millis, String format) {
        return getFormatDateTime(new Date(millis), format);
    }

    public static final String getFormatDateTime(long millis) {
        return getFormatDateTime(new Date(millis), "yyyy-MM-dd HH:mm");
    }

    public static final String getCurFormatDateTime(String format) {
        return getFormatDateTime(System.currentTimeMillis(), format);
    }

    public static final String getCurFormatDateTime() {
        return getFormatDateTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm");
    }

    public static final String getCurFormatDate() {
        return getFormatDateTime(System.currentTimeMillis(), "yyyy-MM-dd");
    }

    public static long getLongFromDateTime(String datetime) {
        String[] args = datetime.split(" ");
        String date = args[0];
        String time = args[1];
        String[] dateArgs = date.split("-");
        String[] timeArgs = time.split(":");
        String mm = "00";
        if (timeArgs.length == 3) {
            mm = timeArgs[2];
        }

        long ret = getTimeLong(Integer.parseInt(dateArgs[0]), Integer.parseInt(dateArgs[1]), Integer.parseInt(dateArgs[2]), Integer.parseInt(timeArgs[0]), Integer.parseInt(timeArgs[1]), Integer.parseInt(mm));
        return ret;
    }

    public static long getLongFromDate(String date) {
        String[] args = date.split("-");
        return getTimeLong(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    }

    public static final long getTimeLong(int yy, int mm, int dd, int h, int m, int sec) {
        calendar.clear();
        calendar.set(yy, mm - 1, dd, h, m, sec);
        return calendar.getTimeInMillis();
    }

    public static final long getTimeLong(int yy, int mm, int dd, int h, int m) {
        calendar.clear();
        calendar.set(yy, mm - 1, dd, h, m, 0);
        return calendar.getTimeInMillis();
    }

    public static final long getTimeLong(int yy, int mm, int dd) {
        calendar.clear();
        calendar.set(yy, mm - 1, dd, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static final long getTimeLong(int yy, int mm) {
        calendar.clear();
        calendar.set(yy, mm - 1, 0, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static final long getTotalTime(Date startTime, long month) {
        calendar.setTime(startTime);
        Calendar var10001 = calendar;
        calendar.add(2, (int)month);
        return calendar.getTimeInMillis();
    }

    public static final String getLeaveDateTime(long tagTime) {
        long nowTime = System.currentTimeMillis();
        long leaveTime = 0L;
        if (nowTime > tagTime) {
            leaveTime = (nowTime - tagTime) / 1000L;
        } else {
            leaveTime = (tagTime - nowTime) / 1000L;
        }

        long date = 0L;
        long hour = 0L;
        long minute = 0L;
        long dateTime = 0L;
        long hourTime = 0L;
        String strDate = "";
        String strHour = "";
        String strMinute = "";
        date = leaveTime / 86400L;
        dateTime = date * 86400L;
        hour = (leaveTime - dateTime) / 3600L;
        hourTime = hour * 3600L;
        minute = (leaveTime - dateTime - hourTime) / 60L;
        if (date > 0L) {
            strDate = date + "天";
        }

        if (hour > 0L || minute > 0L && date > 0L) {
            strHour = hour + "小时";
        }

        if (minute > 0L) {
            strMinute = minute + "分";
        }

        return strDate + strHour + strMinute;
    }

    public static final String getLeaveDay(long tagTime) {
        long nowTime = System.currentTimeMillis();
        long leaveTime = 0L;
        if (nowTime > tagTime) {
            leaveTime = (nowTime - tagTime) / 86400000L;
        } else {
            leaveTime = (tagTime - nowTime) / 86400000L;
        }

        return leaveTime + "";
    }

    public static int getWeekDay(int year, int month, int day) {
        if (month < 3) {
            month += 13;
            --year;
        } else {
            ++month;
        }

        return (day + 26 * month / 10 + year + year / 4 - year / 100 + year / 400 + 6) % 7;
    }

    public static String getDateTimeRandomId() {
        return getFormatDateTime(System.currentTimeMillis(), "yyyyMMddHHmmssSS") + (new Random()).nextInt(1000);
    }
}
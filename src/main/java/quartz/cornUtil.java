package quartz;

import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2019/4/2
 */
public class cornUtil {
    /**
     * @param cronExpression corn表达式
     * @param numTimes       下几次运行的时间
     * @return
     */
    public static List<String> getRecentExecTime(String cronExpression, Integer numTimes) {
        List<String> list = new ArrayList<>();
        try {
            CronTriggerImpl cronTrigger = new CronTriggerImpl();
            cronTrigger.setCronExpression(cronExpression);
            // 这里写要准备猜测的cron表达式
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            // 把统计的区间段设置为从现在到2年后的今天（主要是为了方法通用考虑，如那些1个月跑一次的任务，如果时间段设置的较短就不足20条)
            calendar.add(Calendar.YEAR, 2);
            // 这个是重点，一行代码搞定
            List<Date> dates = TriggerUtils.computeFireTimesBetween(cronTrigger, null, now, calendar.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < dates.size(); i++) {
                // 这个是提示的日期个数
                if (i < numTimes) {
                    list.add(dateFormat.format(dates.get(i)));
                } else {
                    break;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * @param cronExpression corn表达式
     * @param numTimes       显示次数
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @return
     */
    public static List<String> getRecentExecTime2(String cronExpression, Integer numTimes, Date startTime, Date endTime) {
        List<String> list = new ArrayList<>();
        try {
            CronTriggerImpl cronTrigger = new CronTriggerImpl();
            cronTrigger.setCronExpression(cronExpression);

            // 这个是重点，一行代码搞定
            List<Date> dates = TriggerUtils.computeFireTimesBetween(cronTrigger, null, startTime, endTime);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < dates.size(); i++) {
                // 这个是提示的日期个数
                if (i < numTimes) {
                    list.add(dateFormat.format(dates.get(i)));
                } else {
                    break;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2019-04-02 10:10:10");
        Date date2 = sdf.parse("2019-05-02 10:10:10");
        getRecentExecTime2("0 0/5 * * * ?", 10, date1, date2);
    }
}

package top.potat.spring.common.utils.time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 日期工具类
 * Created by potato on 2022/3/10.
 */
public class DateUtils {

    public static final String FORMAT_DAY = "yyyy-MM-dd";
    public static final String FORMAT_DAY_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DAY_TIME_NoSeconds = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DAT_TIME_SECONDS_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String FORMAT_TIME = "HH:mm";
    public static final String TYPE_TODAY = "today";
    public static final String TYPE_THIS_WEEK = "thisWeek";
    public static final String TYPE_THIS_MONTH = "thisMonth";
    public static final String TYPE_THIS_QUARTER = "thisQuarter";
    public static final String TYPE_THIS_YEAR = "thisYear";


    /**
     * 获取当天0点时间
     */
    public static Date getDateZero() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DAY);//设置日期格式
        String format = df.format(new Date()) + " 00:00:00";
        return strToDateTime(format);
    }

    /**
     * 获取当前时间带时区
     */
    public static String getDateToStrZone() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DAT_TIME_SECONDS_ZONE);//设置日期格式
        return df.format(new Date());
    }

    /**
     * 获取固定格式时间
     */
    public static String byPatternDateToStr(Date date,String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期 年月日时分秒
     */
    public static Date strToDateTime(String str) {

        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DAY_TIME);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date strToDateTimeNoSeconds(String str) {

        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DAY_TIME_NoSeconds);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将传入的时间设置为零点
     */
    public static Date setDateToZero(Date date){
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DAY);//设置日期格式
        String format = df.format(date) + " 00:00:00";
        return strToDateTime(format);
    }
    /**
     * 将传入的事件设置为23:59:59
     */
    public static Date setDateToEnd(Date date){
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DAY);//设置日期格式
        String format = df.format(date) + " 23:59:59";
        return strToDateTime(format);
    }

    /**
     * 字符串转换为 年月日
     */
    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DAY);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换为 日期时分
     */
    public static Date strToTime(String str) {

        SimpleDateFormat format = new SimpleDateFormat(FORMAT_TIME);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间转换为 字符串 年月日时分秒
     */
    public static String getDayTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DAY_TIME);
        return format.format(date);
    }

    /**
     * 验证字符串是否时日期(不带秒数)
     */
    public static boolean strIsDate(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY_TIME_NoSeconds);
            sdf.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean strIsDateTime(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY_TIME);
            sdf.parse(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *  传入日期字符串和时分 返回时间
     */
    public static Date strToDate(String date,String time){
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DAY_TIME);
        Date dateTime = null;
        try {
            dateTime = format.parse(date + " " + time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateTime;
    }
    /**
     * 传入的时间添加固定秒数
     */
    public static Date setSeconds(Date date,int seconds){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.SECOND, seconds);
        return rightNow.getTime();
    }

    /**
     * 传入的时间添加固定分钟数
     */
    public static Date setMinutes(Date date,int minutes){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MINUTE, minutes);
        return rightNow.getTime();
    }


    /**
     * 传入的时间添加固定天数
     */
    public static Date setDay(Date date,int days){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, days);
        return rightNow.getTime();
    }

    /**
     * 传入的时间添加固定小时
     */
    public static Date setHours(Date date,int hours){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.HOUR, hours);
        return rightNow.getTime();
    }

    /**
     * 传入时间 添加固定年份
     */
    public static Date setYear(Date date,int year){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.YEAR, year);
        return rightNow.getTime();
    }

    /***
     * 传入时间 获取当前 年月日
     */
    public static String getDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DAY);
        return format.format(date);
    }

    /**
     * 获取当前时间 返回字符串时间
     */
    public static String getDay(){
        return getDay(new Date());
    }

    /**
     * 年月日时分秒
     */
    public static String getDateTime(){
        return byPatternDateToStr(new Date(),FORMAT_DAY_TIME);
    }

    /**
     * 验证当前是否在时间段内
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 是否
     */
    public static boolean isHourSlotNow(Date startTime,Date endTime){
        Date startTimeDate = limitedTimeSpike(startTime);
        Date endTimeDate = limitedTimeSpike(endTime);
        Date dateNow = new Date();
        return (startTimeDate.getTime() <= dateNow.getTime()) && (endTimeDate.getTime() > dateNow.getTime());
    }

    /**
     * 根据 时分秒 获取现在 年月日 加上 传的 时分秒
     */
    public static Date limitedTimeSpike(Date hourTime){
        String day = getDay(new Date());
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String str = format.format(hourTime);
        return strToDateTime(day + " " + str);
    }

    /**
     * 获取时间段内相差多少分钟
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 分钟数
     */
    public static long getDifferenceMinute(Date startTime,Date endTime){
        long diff = startTime.getTime() - endTime.getTime();
        return diff / 1000L / 60L;
    }

    /**
     * 获取时间段内相差多少天
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 分钟数
     */
    public static long getDifferenceDay(Date startTime,Date endTime){
        long diff = startTime.getTime() - endTime.getTime();
        return diff / 1000L / 60L/ 60L / 24L;
    }

    /**
     * 获取年初时间
     * @return 返回时间
     */
    public static Date getFirstYearDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear(Calendar.MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND, 0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取年末时间
     * @return 返回时间
     */
    public static Date getLastYearDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear(Calendar.MONTH);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /***
     * 根据月份 获取本年 自定义月份第一天日期
     * @param month 月份
     * @return 时间
     */
    public static Date getFirstDayToMonth(int month){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.MONTH,month-1);
        int firstDay = cale.getActualMinimum(Calendar.DAY_OF_MONTH);//获取月最小天数
        cale.set(Calendar.DAY_OF_MONTH, firstDay);//设置日历中月份的最小天数
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);	//格式化日期yyyy-MM-dd
        String lastDayOfMonth = sdf.format(cale.getTime());
        return strToDate(lastDayOfMonth);
    }

    /**
     * 根据月份 获取本年 自定义月份最后一天日期
     * @param month 月份
     * @return 时间
     */
    public static Date getLastDayToMonth(int month){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.MONTH,month-1);
        int lastDay = cale.getActualMaximum(Calendar.DAY_OF_MONTH);//获取月最大天数
        cale.set(Calendar.DAY_OF_MONTH, lastDay);//设置日历中月份的最大天数
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);	//格式化日期yyyy-MM-dd
        String lastDayOfMonth = sdf.format(cale.getTime());
        return strToDate(lastDayOfMonth);
    }

    /**
     * 获取当月一号的日期
     * @return 当月一号的 Date 对象
     */
    public static Date getFirstDayOfCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDay = currentDate.withDayOfMonth(1);
        return Date.from(firstDay.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取今年本季度的开始时间，返回 Date 对象
     * @return 本季度开始时间的 Date 对象
     */
    public static Date getCurrentQuarterStart() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        int quarterStartMonth;
        if (month >= 1 && month <= 3) {
            quarterStartMonth = 1;
        } else if (month >= 4 && month <= 6) {
            quarterStartMonth = 4;
        } else if (month >= 7 && month <= 9) {
            quarterStartMonth = 7;
        } else {
            quarterStartMonth = 10;
        }
        LocalDate quarterStartDate = LocalDate.of(Year.now().getValue(), quarterStartMonth, 1);
        LocalDateTime quarterStartDateTime = LocalDateTime.of(quarterStartDate, LocalTime.MIN);
        return Date.from(quarterStartDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取今年本季度的结束时间，返回 Date 对象
     * @return 本季度结束时间的 Date 对象
     */
    public static Date getCurrentQuarterEnd() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        int quarterEndMonth;
        if (month >= 1 && month <= 3) {
            quarterEndMonth = 3;
        } else if (month >= 4 && month <= 6) {
            quarterEndMonth = 6;
        } else if (month >= 7 && month <= 9) {
            quarterEndMonth = 9;
        } else {
            quarterEndMonth = 12;
        }
        LocalDate quarterEndDate = LocalDate.of(Year.now().getValue(), quarterEndMonth, 1)
                .with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime quarterEndDateTime = LocalDateTime.of(quarterEndDate, LocalTime.MAX);
        return Date.from(quarterEndDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取这一周的周一时间
     */
    public static Date getTodayOneWeekDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - cal.get(Calendar.DAY_OF_WEEK));// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        // 当前这一周的周一时间
        return cal.getTime();
    }

    /**
     * 获取 n天后每一天的日期(携带星期几)
     */
    public static List<WeekDateInfo> get14WeekDateList(int n){
        if (n <= 0) n = 1;
        List<WeekDateInfo> dateInfos = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < n; i++) {
            LocalDate nextDate = currentDate.plusDays(i);
            String date = nextDate.toString();
            String weekday = nextDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE);
            dateInfos.add(new WeekDateInfo(date, weekday));
        }

        return dateInfos;
    }

    /**
     * 根据指定的起始时间、结束时间和时间间隔来生成对应的时间列表
     * @param startTime 起始小时
     * @param endTime 结束小时
     * @param interval 时间间隔(小时)
     * @return 时间列表
     */
    public static List<String> getTimeIntervals(int startTime, int endTime, int interval) {
        List<String> timeList = new ArrayList<>();
        for (int i = startTime; i <= endTime; i += interval) {
            timeList.add(String.format("%d:00", i));
        }
        return timeList;
    }


    public static class WeekDateInfo{
        private String date;
        private String weekday;
        public WeekDateInfo(String date, String weekday) {
            this.date = date;
            this.weekday = weekday;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeekday() {
            return weekday;
        }

        public void setWeekday(String weekday) {
            this.weekday = weekday;
        }

        @Override
        public String toString() {
            return "WeekDateInfo{" +
                    "date='" + date + '\'' +
                    ", weekday='" + weekday + '\'' +
                    '}';
        }
    }

    /**
     * 根据传入的类型计算对应时间区间
     *
     * @param type 时间类型，支持 "today", "thisWeek", "thisMonth", "thisQuarter", "thisYear"
     * @return 包含开始时间和结束时间的数组，格式为 [startTime, endTime]
     */
    public static Date[] calculateRange(String type) {
        Calendar calendar = Calendar.getInstance();
        Date startTime;
        Date endTime;

        switch (type) {
            case TYPE_TODAY:
                // 设置为当天的开始时间 00:00:00
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startTime = calendar.getTime();

                // 设置为下一天的开始时间 00:00:00，即当天的结束时间
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                endTime = calendar.getTime();
                break;
            case TYPE_THIS_WEEK:
                // 设置为本周第一天（周日） 00:00:00
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startTime = calendar.getTime();

                // 设置为下一周第一天（周日） 00:00:00，即本周的结束时间
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                endTime = calendar.getTime();
                break;
            case TYPE_THIS_MONTH:
                // 设置为当月的第一天 00:00:00
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startTime = calendar.getTime();

                // 设置为下个月的第一天 00:00:00，即当月的结束时间
                calendar.add(Calendar.MONTH, 1);
                endTime = calendar.getTime();
                break;
            case TYPE_THIS_QUARTER:
                int currentMonth = calendar.get(Calendar.MONTH);
                int quarter = currentMonth / 3 * 3;  // 计算当前季度的起始月份

                // 设置为当前季度的第一个月的第一天 00:00:00
                calendar.set(Calendar.MONTH, quarter);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startTime = calendar.getTime();

                // 设置为下一个季度的第一个月的第一天 00:00:00，即当前季度的结束时间
                calendar.add(Calendar.MONTH, 3);
                endTime = calendar.getTime();
                break;
            case TYPE_THIS_YEAR:
                // 设置为当年的第一天 00:00:00
                calendar.set(Calendar.DAY_OF_YEAR, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                startTime = calendar.getTime();

                // 设置为下一年的第一天 00:00:00，即当年的结束时间
                calendar.add(Calendar.YEAR, 1);
                endTime = calendar.getTime();
                break;
            default:
                throw new IllegalArgumentException("不支持的时间类型: " + type);
        }

        return new Date[]{startTime, endTime};
    }

    /**
     * 时间区间格式化
     * @return 2025-02-01 10:00 - 11:00
     */
    public static String formatDateRange(Date start, Date end) {
        if(start == null) start = new Date();
        if(end == null) end = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DAY);
        SimpleDateFormat timeFormat = new SimpleDateFormat(FORMAT_TIME);

        // 提取日期和时间部分
        String datePart = dateFormat.format(start);
        String startTimePart = timeFormat.format(start);
        String endTimePart = timeFormat.format(end);
        // 拼接结果
        return String.format("%s %s-%s", datePart, startTimePart, endTimePart);
    }
}


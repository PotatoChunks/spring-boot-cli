package com.pt.api.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 */
public class DateUtils {

    /**
     * 获取当然0点时间
     */
    public static Date getDateZero() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String format = df.format(new Date()) + " 00:00:00";
        return strToDateTime(format);
    }

    /**
     * 获取传入时间的零点
     */
    public static Date setDateToZero(Date date){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String format = df.format(date) + " 00:00:00";
        return strToDateTime(format);
    }


    /**
     * 日期转换成字符串
     */
    public static String dateToStr(Date date) {
        if (date == null) return "";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换为固定格式
     */
    public static String byPatternDateToStr(Date date,String pattern) {
        if (date == null) return "";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String str = format.format(date);
        return str;
    }

    /**
     * 字符串转换成日期:(年月日 时分秒)
     */
    public static Date strToDateTime(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期:(年月日)
     */
    public static Date strToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 字符串时间 添加天数
     */
    public static String subDay(String date, int days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
            dt = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DAY_OF_MONTH, days);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    /**
     * 时间 添加分钟数
     */
    public static Date setDayMinuit(Date date,int minuit){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MINUTE,minuit);
        return rightNow.getTime();
    }

    /**
     * 时间 添加天数
     */
    public static Date setDay(Date date,int days){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, days);
        return rightNow.getTime();
    }

    /**
     * 时间 添加小时
     */
    public static Date setHours(Date date,int hours){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.HOUR, hours);
        return rightNow.getTime();
    }

    /**
     * 时间 添加几个七天
     */
    public static Date setSevenDay(Date date,int count){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE,count * 7);
        return rightNow.getTime();
    }

    /**
     * 时间 添加年
     */
    public static Date setYear(Date date,int year){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.YEAR, year);
        return rightNow.getTime();
    }

    /**
     * 获取字符串年月日
     */
    public static String getDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 验证字符串时间，是否在30天内
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        //时间格式定义
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前时间日期--nowDate
        String nowDate = format.format(new Date());
        //获取30天前的时间日期--minDate
        Calendar calc = Calendar.getInstance();
        calc.add(Calendar.DAY_OF_MONTH, -30);
        String minDate = format.format(calc.getTime());

        try {
            //设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            //获取字符串转换后的时间--strDate
            String strDate = format.format(format.parse(str));
            //判断传的STR时间，是否在当前时间之前，且在30天日期之后-----测试的时候打印输出结果
            //System.out.println("nowDate.compareTo(strDate):"+ nowDate.compareTo(strDate));
            //System.out.println("strDate.compareTo(minDate):"+ strDate.compareTo(minDate));
            if (nowDate.compareTo(strDate) >= 0 && strDate.compareTo(minDate) >= 0) {
                convertSuccess = true;
            } else {
                convertSuccess = false;
            }

        } catch (ParseException e) {
            convertSuccess = false;
        }

        return convertSuccess;

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
     * 获取本月月初时间
     */
    public static Date getThisMonthInitial(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfMonth = dateFormat.format(calendar.getTime()) + " 00:00:00";
        return strToDate(firstDayOfMonth);
    }

    /**
     * 判断字符串是否为合法的日期格式
     * @param dateStr 待判断的字符串
     */
    public static boolean isStrDate(String dateStr){
        //判断结果 默认为true
        boolean judgeresult=true;
        //1、首先使用SimpleDateFormat初步进行判断，过滤掉注入 yyyy-01-32 或yyyy-00-0x等格式
        //此处可根据实际需求进行调整，如需判断yyyy/MM/dd格式将参数改掉即可
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
            //增加强判断条件，否则 诸如2022-02-29也可判断出去
            format.setLenient(false);
            Date date =format.parse(dateStr);
            System.out.println(date);
        }catch(Exception e){
            judgeresult=false;
        }
        //由于上述方法只能验证正常的日期格式，像诸如 0001-01-01、11-01-01，10001-01-01等无法校验，此处再添加校验年费是否合法
        String yearStr=dateStr.split("-")[0];
        if(yearStr.startsWith("0")||yearStr.length()!=4){
            judgeresult=false;
        }
        return judgeresult;
    }

    /**
     * 时间区间
     */
    public static List<String> findDaysStr(String cntDateBeg, String cntDateEnd) {
        List<String> list = new ArrayList<>();
        //拆分成数组
        String[] dateBegs = cntDateBeg.split("-");
        String[] dateEnds = cntDateEnd.split("-");
        //开始时间转换成时间戳
        Calendar start = Calendar.getInstance();
        start.set(Integer.valueOf(dateBegs[0]), Integer.valueOf(dateBegs[1]) - 1, Integer.valueOf(dateBegs[2]));
        Long startTIme = start.getTimeInMillis();
        //结束时间转换成时间戳
        Calendar end = Calendar.getInstance();
        end.set(Integer.valueOf(dateEnds[0]), Integer.valueOf(dateEnds[1]) - 1, Integer.valueOf(dateEnds[2]));
        Long endTime = end.getTimeInMillis();
        //定义一个一天的时间戳时长
        Long oneDay = 1000 * 60 * 60 * 24L;
        Long time = startTIme;
        //循环得出
        while (time <= endTime) {
            list.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date(time)));
            time += oneDay;
        }
        return list;

    }

    /**
     * 获取本周的时间范围
     * @return 返回长度为2的字符串集合，如：[2022-05-02 00:00:00, 2022-05-08 23:59:59]
     */
    public static List<String> getCurrentWeek() {
        List<String> dataList = new ArrayList<>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置周一为一周之内的第一天
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String sunday = dateFormat.format(calendar.getTime()) + " 23:59:59";
        dataList.add(monday);
        dataList.add(sunday);
        return dataList;
    }


    /**
     * 获取本月的时间范围
     * @return 返回长度为2的字符串集合，如：[2022-11-01 00:00:00, 2022-11-30 23:59:59]
     */
    public static List<String> getCurrentMonth() {
        List<String> dataList = new ArrayList<>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfMonth = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String lastDayOfMonth = dateFormat.format(calendar.getTime()) + " 23:59:59";
        dataList.add(firstDayOfMonth);
        dataList.add(lastDayOfMonth);
        return dataList;
    }

    /**
     * 获取本年的时间范围
     * @return 返回长度为2的字符串集合，如：[2022-01-01 00:00:00, 2022-12-31 23:59:59]
     */
    public static List<String> getCurrentYear() {
        List<String> dataList = new ArrayList<>(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        String firstDayOfYear = dateFormat.format(calendar.getTime()) + " 00:00:00";
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        String lastDayOfYear = dateFormat.format(calendar.getTime()) + " 23:59:59";
        dataList.add(firstDayOfYear);
        dataList.add(lastDayOfYear);
        return dataList;
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
     * 获取时间段内相差多少个七天
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 个数
     */
    public static long getDifferenceSevenDay(Date startTime,Date endTime){
        long diff = startTime.getTime() - endTime.getTime();
        return diff / 1000L / 60L/ 60L / 24L / 7L;
    }
    /**
     * 获取时间段内相差多少小时
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 小时数
     */
    public static long getDifferenceHour(Date startTime,Date endTime){
        long diff = startTime.getTime() - endTime.getTime();
        return diff / 1000L / 60L/ 60L;
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
     * 更具月 获取本年 自定义月份第一天日期
     * @param month 月份
     * @return 时间
     */
    public static Date getFirstDayToMonth(int month){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.MONTH,month-1);
        int firstDay = cale.getActualMinimum(Calendar.DAY_OF_MONTH);//获取月最小天数
        cale.set(Calendar.DAY_OF_MONTH, firstDay);//设置日历中月份的最小天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	//格式化日期yyyy-MM-dd
        String lastDayOfMonth = sdf.format(cale.getTime());
        return strToDate(lastDayOfMonth);
    }

    /**
     * 更具月 获取本年 自定义月份最后一天日期
     * @param month 月份
     * @return 时间
     */
    public static Date getLastDayToMonth(int month){
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.MONTH,month-1);
        int lastDay = cale.getActualMaximum(Calendar.DAY_OF_MONTH);//获取月最大天数
        cale.set(Calendar.DAY_OF_MONTH, lastDay);//设置日历中月份的最大天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	//格式化日期yyyy-MM-dd
        String lastDayOfMonth = sdf.format(cale.getTime());
        return strToDate(lastDayOfMonth);
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
     * 判断当前时间是否大于某个时间
     **/
    public static boolean dateCompare(String dateTime){
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime,dft);
        return LocalDateTime.now().isAfter(localDateTime);

    }

    /**
     * 根据现在时间和未来时间返回 时间差 小于一天返回小时 小于小时返回分钟  其他返回null
     * @param dateNow 现在时间
     * @param endTime 未来时间
     * @return 字符串
     */
    public static String getTimeDifference(Date dateNow,Date endTime){
        Instant instant = endTime.toInstant();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        LocalDateTime now = LocalDateTime.ofInstant(dateNow.toInstant(),ZoneId.systemDefault());
        long daysBetween = ChronoUnit.DAYS.between(now, dateTime);

        if (daysBetween < 1) {
            Duration duration = Duration.between(now, dateTime);
            long hoursBetween = duration.toHours();
            if (duration.toMinutes() <= 0 || hoursBetween < 0) return null;
            if(hoursBetween < 1) return duration.toMinutes() + " 分钟";
            return String.valueOf(hoursBetween) + " 小时";
        } else if (daysBetween > 1) {
            return String.valueOf(daysBetween) + " 天";
        }
        return null;
    }

}


package DateAndTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hx
 * @version 1.0
 * @date 2023/5/10 10:40
 */
public class DateAndTime {

    public static void main(String[] args) throws ParseException {
        new DateAndTime().daysOfTwo_2();
    }

    //如果有时间，差值小于24小时则 显示0天。
    public void daysOfTwo_2() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //跨年不会出现问题
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
//        Date fDate=sdf.parse("2015-12-31");
//        Date oDate=sdf.parse("2016-01-01");
        Date fDate = new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-9");
        Date oDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        long days=(oDate.getTime()-fDate.getTime())/(1000*60);
        System.out.print(days);
    }

    //跨年会有问题
    public void daysOfTwo_1() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        //跨年的情况会出现问题哦
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 1
        Date fDate=sdf.parse("2015-12-31");
        Date oDate=sdf.parse("2016-01-01");
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        int days=day2-day1;
        System.out.print(days);
    }
}

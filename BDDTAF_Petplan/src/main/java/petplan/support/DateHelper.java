package petplan.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateHelper {

    public static Map<Integer, String> months = new HashMap<>();

    static {
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
    }

    public static String getPreviousYearForACTParam(String format) {
        Calendar prevYear = Calendar.getInstance();
        prevYear.add(Calendar.YEAR, -1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(prevYear.getTime());
    }

    public static String getPreviousMonth(String format) {
        Calendar prevMonth = Calendar.getInstance();
        prevMonth.add(Calendar.MONTH, -1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(prevMonth.getTime());
    }

    public static String getNextYear(String format) {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(nextYear.getTime());
    }

    public static String getWithAddedYear(String format, int years) {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, years);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(nextYear.getTime());
    }

    public static String getYearWithAddedYear(String date, String format, int years) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date1 = sdf.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.add(Calendar.YEAR, years);
        return "" + cal.get(Calendar.YEAR);
    }

    public static String getCurrentYear(String format) {
        Calendar year = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(year.getTime());
    }

    public static String getYearFromDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date1 = sdf.parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        return "" + cal.get(Calendar.YEAR);
    }

    public static String getCurrentDateWithAddedDay(String format, int day) {
        Calendar year = Calendar.getInstance();
        year.add(Calendar.DAY_OF_YEAR, day);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(year.getTime());
    }

    public static String getSelectedDateWithAddedDay(String format, String date, int day) throws Exception {
        Date givenDate = new SimpleDateFormat(format).parse(date);
        Calendar givenDateCalendar = Calendar.getInstance();
        givenDateCalendar.setTime(givenDate);
        givenDateCalendar.add(Calendar.DAY_OF_YEAR, day);

        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(givenDateCalendar.getTime());
    }

    public static String getNextMonth(String format) {
        Calendar year = Calendar.getInstance();
        year.add(Calendar.MONTH, 1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(year.getTime());
    }

    public static String getNextDay(String format) {
        Calendar year = Calendar.getInstance();
        year.add(Calendar.DAY_OF_MONTH, 1);
        DateFormat df = new SimpleDateFormat(format);//"MMddyyyy"
        return df.format(year.getTime());
    }

    /**
     * @param month should be integer 1 - 12
     * @return the proper month Name in english. The first Symbol is in Upper case.
     */
    public static String getMonthName(Integer month) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("The argument month should be integer 1 to 12 included. ");
        return months.get(month);
    }

    /**
     * @return the proper month Name in english. The first Symbol is in Upper case.
     */
    public static String getCurrentMonthName() {
        Calendar cal = Calendar.getInstance();
        return months.get(cal.get(Calendar.MONTH) + 1);
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getYearFromDate("12/15/2019","MM/dd/yyyy"));
        System.out.println(getYearWithAddedYear("12/15/2019","MM/dd/yyyy", -5));
        System.out.println(getCurrentYear("yyyy_MM_dd_HH:mm:ss"));
    }
}

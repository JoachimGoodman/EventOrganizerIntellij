import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
    /*int years;
    int months;
    int days;
    int hours;
    int minutes;
    int seconds;

    public Time(int years, int months, int days,int hours, int minutes, int seconds) {
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;

    }*/

    public static void main(String[] args) throws ParseException {


        String time1 = "2006:12:14:15:00:00";
        String time2 = "2006:12:15:23:00:00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();

        Calendar c = Calendar.getInstance();
        //Set time in milliseconds
        c.setTimeInMillis(difference);
        int mYear = c.get(Calendar.YEAR)-1970;
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH)-1;
        int hr = c.get(Calendar.HOUR)-1;
        int min = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);

        System.out.println("Years:" + mYear + " Months:" + mMonth + " Days:" + mDay + " Hours:" + hr + " Minutes:" + min + " Seconds:" + sec);

        float diff = difference;

        System.out.println(Math.ceil(diff/1800000)*250 + "kr.");

        /*Time start = new Time(2005, 11, 13,12,33,22),
             stop = new Time(2006, 12, 24,13,44,33),
             diff;

        diff = difference(start, stop);

        System.out.printf("TIME DIFFERENCE: %d:%d:%d:%d:%d:%d - ", start.years, start.months, start.days, start.hours, start.minutes, start.seconds);
        System.out.printf("%d:%d:%d:%d:%d:%d ", stop.years, stop.months, stop.days, stop.hours, stop.minutes, stop.seconds);
        System.out.printf("= %d:%d:%d:%d:%d:%d\n", diff.years, diff.months, diff.days, diff.hours, diff.minutes, diff.seconds);
        System.out.println((diff.seconds+diff.minutes*60+ diff.hours*3600+diff.days*86400+diff.months*259200+diff.years*31556926)/86400);*/
    }
    /*
    public static Time difference(Time start, Time stop)
    {
        Time diff = new Time(0, 0, 0,0,0,0);

        if(stop.seconds > start.seconds){
            --start.minutes;
            start.seconds += 60;
        }

        if(stop.minutes > start.minutes) {
            --start.hours;
            start.minutes += 60;
        }
        if(stop.hours > start.hours){
            --start.days;
            start.hours += 24;
        }
        if(stop.days > start.days){
            --start.months;
            start.days += 30;
        }
        if(stop.months > start.months){
            --start.years;
            start.months += 12;
        }

        diff.seconds = start.seconds - stop.seconds;
        diff.minutes = start.minutes - stop.minutes;
        diff.hours = start.hours - stop.hours;
        diff.days = start.days - stop.days;
        diff.months = start.months - stop.days;
        diff.years = start.years - stop.years;

        return(diff);
    }*/
}
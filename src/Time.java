import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {

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
    }
}
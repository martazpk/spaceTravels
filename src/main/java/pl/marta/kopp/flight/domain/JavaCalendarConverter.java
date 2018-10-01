package pl.marta.kopp.flight.domain;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class JavaCalendarConverter {
    private static String datePattern = "MM/dd/yyyy HH:mm:ss:SSS";

    public static Calendar stringToCalendar(String stringDate) {

        if (stringDate == null) {
            return null;
        }

        Calendar calendar = new GregorianCalendar();
        try {
            Timestamp newDate = Timestamp.valueOf(stringDate);
            calendar.setTime(newDate);
        } catch (Exception e) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
            try {
                calendar.setTime(simpleDateFormat.parse(stringDate));
            } catch (ParseException pe) {
                calendar = null;
            }
        }

        return calendar;
    }

    public static String calendarToString(Calendar calendar) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
        String calendarString = simpleDateFormat.format(calendar.getTime());
        return calendarString;
    }
}

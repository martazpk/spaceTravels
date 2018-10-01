package pl.marta.kopp.flight.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class JavaCalendarConverterTest {
    private static String dateStringPattern = "MM/dd/yyyy HH:mm:ss:SSS";
    private static String dateString = "01/10/2018 14:32:55:123";
    private static Calendar calendar;

    @BeforeClass
    public static void getTestDate() throws ParseException {

        Date date = new SimpleDateFormat(dateStringPattern).parse(dateString);
        calendar = Calendar.getInstance();
        calendar.setTime(date);
    }

    @Test
    public void testStringToCalendar() {

        Calendar calendarResult = JavaCalendarConverter.stringToCalendar(dateString);
        System.out.println("StringToCalendar: " + calendarResult);
        assertEquals(calendar, calendarResult);
    }

    @Test
    public void testCalendarToString() {

        String calendarStringResult = JavaCalendarConverter.calendarToString(calendar);
        System.out.println("CalendarToString: " + calendarStringResult);
        assertEquals(dateString, calendarStringResult);
    }
}
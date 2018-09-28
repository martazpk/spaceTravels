package flight.domain;

import flight.dto.FlightDto;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class FlightTest {

    private static final Calendar DEPARTURE = new GregorianCalendar(2013, 1, 28, 13, 24, 56);
    private static final Calendar ARRIVAL = new GregorianCalendar(2014, 1, 23, 12, 12, 12);
    private static final int MAX_VALUE_OF_SEATS = 100;
    private static final String PRICE = "100";

    @Test
    public void shouldReturnDto() throws Exception {
        FlightDto initialDto = new FlightDto(DEPARTURE,ARRIVAL,MAX_VALUE_OF_SEATS,PRICE);
        Flight flight =new Flight(initialDto);
        FlightDto result=flight.asDto();

        assertEquals(DEPARTURE,result.getDeparture());
    }

}
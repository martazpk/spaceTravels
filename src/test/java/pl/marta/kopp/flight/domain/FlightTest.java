package pl.marta.kopp.flight.domain;

import pl.marta.kopp.flight.dto.FlightDto;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class FlightTest {

    private static final String DEPARTURE = "01/10/2018 16:30:50:000";
    private static final String ARRIVAL = "01/10/2020 16:30:50:000";
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
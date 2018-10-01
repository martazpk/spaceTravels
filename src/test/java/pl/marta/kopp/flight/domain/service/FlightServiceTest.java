package pl.marta.kopp.flight.domain.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.marta.kopp.flight.dto.FlightDto;
import pl.marta.kopp.flight.repository.FlightRepository;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FlightServiceTest {

    @Autowired
    private FlightRepository repository;
    private FlightService service;

    private static final String DEPARTURE = "01/10/2018 14:32:55:123";
    private static final String ARRIVAL = "01/10/2042 14:32:55:123";
    private static final int MAX_NUMBER_OF_SEAT = 10;
    private static final String PRICE = "10000.00";
    private static final String NEW_PRICE = "120.00";
    private static final long INCORRECT_ID=1124534L;

    @Before
    public void setUp() throws Exception {
        service = new FlightService(repository);
    }

    @Test
    public void get() throws Exception {
        long identifier =givenFlight(DEPARTURE, ARRIVAL,MAX_NUMBER_OF_SEAT,PRICE);
        FlightDto result=service.get(identifier);

        assertEquals(DEPARTURE,result.getDeparture());
        assertEquals(PRICE,result.getPriceValue());
    }


    private long givenFlight(String departure, String arrival, int maxNumberOfSeat, String price) {
        FlightDto initialDto=new FlightDto(departure,arrival,maxNumberOfSeat,price);
        return service.add(initialDto);
    }


    @Test(expected = FlightDoesNotExistException.class)
    public void shouldThrowExceptionWhenIncorrectIdentifierIsGiven() throws Exception {
        service.get(INCORRECT_ID);
    }

    @Test
    public void shouldDeleteFlightWhenIdentifierIsGiven() throws Exception {
        long identifier =givenFlight(DEPARTURE, ARRIVAL,MAX_NUMBER_OF_SEAT,PRICE);
        service.delete(identifier);

        assertFalse(repository.isExists(identifier));
    }

    @Test
    public void shouldChangePrice() throws Exception {
        long identifier =givenFlight(DEPARTURE, ARRIVAL,MAX_NUMBER_OF_SEAT,PRICE);
        FlightDto dtoWithNewPrice=new FlightDto(DEPARTURE,ARRIVAL,MAX_NUMBER_OF_SEAT,NEW_PRICE);
        service.update(identifier,dtoWithNewPrice);
        FlightDto result=service.get(identifier);

        assertEquals(NEW_PRICE,result.getPriceValue());
    }

}
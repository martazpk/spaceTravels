package pl.marta.kopp.flight.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.marta.kopp.flight.domain.service.FlightService;
import pl.marta.kopp.flight.dto.FlightDto;


import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTest {

    @Autowired
    private MockMvc chrome;
    @Autowired
    private FlightService service;

    private static final String MAX_NUMBER_OF_SEATS="123";
    private static final String PRICE="12000.00";
    private static final String NEW_PRICE="300000.00";
    private static final String ARRIVAL = "01/10/2018 14:32:55:123";
    private static final String DEPARTURE = "01/10/2123 14:32:55:123";


    @Test
    public void shouldCreateNewFlight() throws Exception {
        MockHttpServletResponse response=chrome.perform(MockMvcRequestBuilders.post("/flight")
        .param("departure",DEPARTURE)
        .param("arrival",ARRIVAL)
        .param("maxNumberOfSeats",MAX_NUMBER_OF_SEATS)
        .param("priceValue",PRICE)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        String id=response.getContentAsString();
        MockHttpServletResponse create=aFlightBy(Long.valueOf(id));
        assertEquals("{\"departure\":\"01/10/2123 14:32:55:123\",\"arrival\":\"01/10/2018 14:32:55:123\"," +
                "\"maxNumberOfSeats\":\"123\",\"priceValue\":\"12000.00\"}",create.getContentAsString());

       
    }

    private MockHttpServletResponse aFlightBy(Long id) throws Exception {
        return  chrome.perform(MockMvcRequestBuilders.get("/flight/{id}",id)).andReturn().getResponse();
    }

    @Test
    public void shouldGetFlight() throws Exception {
        long id=givenFlight(DEPARTURE,ARRIVAL,MAX_NUMBER_OF_SEATS,PRICE);
        MockHttpServletResponse response=aFlightBy(id);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("{\"departure\":\"01/10/2123 14:32:55:123\",\"arrival\":\"01/10/2018 14:32:55:123\"," +
                "\"maxNumberOfSeats\":\"123\",\"priceValue\":\"12000.00\"}",response.getContentAsString());

    }

    private long givenFlight(String departure, String arrival, String maxNumberOfSeats, String price) {
        FlightDto dto=new FlightDto(departure,arrival,maxNumberOfSeats,price);
        return service.add(dto);
    }

    @Test
    public void shouldRemoveFlight() throws Exception {
        long id=givenFlight(DEPARTURE,ARRIVAL,MAX_NUMBER_OF_SEATS,PRICE);

        MockHttpServletResponse response=chrome.perform(MockMvcRequestBuilders.delete("/flight/{id}",id))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }


    @Test
    public void shouldToChangePrice() throws Exception {
        long id=givenFlight(DEPARTURE,ARRIVAL,MAX_NUMBER_OF_SEATS,PRICE);

        MockHttpServletResponse response=chrome.perform(MockMvcRequestBuilders.put("/flight/{id}",id)
                .param("departure",DEPARTURE)
                .param("arrival",ARRIVAL)
                .param("maxNumberOfSeats",MAX_NUMBER_OF_SEATS)
                .param("priceValue",NEW_PRICE)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        MockHttpServletResponse result=aFlightBy(id);
        assertEquals("{\"departure\":\"01/10/2123 14:32:55:123\",\"arrival\":\"01/10/2018 14:32:55:123\"," +
                "\"maxNumberOfSeats\":\"123\",\"priceValue\":\"300000.00\"}",result.getContentAsString());
    }

}
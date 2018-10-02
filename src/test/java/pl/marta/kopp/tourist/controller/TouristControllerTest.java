package pl.marta.kopp.tourist.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.marta.kopp.tourist.domain.service.TouristService;
import pl.marta.kopp.tourist.dto.TouristDto;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TouristControllerTest {

    @Autowired
    private MockMvc chrome;
    @Autowired
    private TouristService service;

    private static final String NAME="marta";
    private static final String SURNAME="kopp";
    private static final String NEW_SURNAME="superkopp";
    private static final String SEX="FEMALE";
    private static final String DATE_OF_BIRTH = "01/10/2018 14:32:55:123";
    private static final long WRONG_ID = 1234567L;

    @Test
    public void shouldCreateNewTourist() throws Exception {
        MockHttpServletResponse response=chrome.perform(MockMvcRequestBuilders.post("/tourist")
                .param("name",NAME)
                .param("surname",SURNAME)
                .param("dateOfBirth",DATE_OF_BIRTH)
                .param("sex",SEX)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        String id=response.getContentAsString();
        MockHttpServletResponse create=aTouristBy(Long.valueOf(id));
        assertEquals("{\"name\":\"marta\",\"surname\":\"kopp\",\"sex\":\"FEMALE\"," +
                "\"country\":null,\"description\":null,\"dateOfBirth\":\"01/10/2018 14:32:55:123\"}",create.getContentAsString());

    }

        private MockHttpServletResponse aTouristBy(Long id) throws Exception {
        return chrome.perform(MockMvcRequestBuilders.get("/tourist/{id}",id)).andReturn().getResponse();
    }

    @Test
    public void shouldRemoveTourist() throws Exception {
        long id=givenTourist(NAME,SURNAME,SEX,DATE_OF_BIRTH);
        MockHttpServletResponse response=chrome.perform(MockMvcRequestBuilders.delete("/tourist/{id}",id))
                .andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    private long givenTourist(String name, String surname, String sex, String dateOfBirth) {
        TouristDto dto=new TouristDto.Builder(name,surname).sex(sex).dateOfBirth(dateOfBirth).build();
        return service.add(dto);
    }

    @Test
    public void shouldGetTourist() throws Exception {
        long id=givenTourist(NAME,SURNAME,SEX,DATE_OF_BIRTH);
        MockHttpServletResponse response=chrome.perform(MockMvcRequestBuilders.get("/tourist/{id}",id)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());
        assertEquals("{\"name\":\"marta\",\"surname\":\"kopp\",\"sex\":\"FEMALE\"," +
                "\"country\":null,\"description\":null,\"dateOfBirth\":\"01/10/2018 14:32:55:123\"}",response.getContentAsString());
    }

    @Test
    public void shouldChangeName() throws Exception {
        long id=givenTourist(NAME,SURNAME,SEX,DATE_OF_BIRTH);

        MockHttpServletResponse response=chrome.perform(MockMvcRequestBuilders.put("/tourist/{id}",id)
                .param("name",NAME)
                .param("surname",NEW_SURNAME)
                .param("dateOfBirth",DATE_OF_BIRTH)
                .param("sex",SEX)).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        MockHttpServletResponse result = aTouristBy(id);
        assertEquals("{\"name\":\"marta\",\"surname\":\"superkopp\",\"sex\":\"FEMALE\"," +
                "\"country\":null,\"description\":null,\"dateOfBirth\":\"01/10/2018 14:32:55:123\"}",result.getContentAsString());
    }

    @Test
    public void shouldReturnNotFoundStatusWhenTouristDoesNotExist() throws Exception {
        MockHttpServletResponse response = aTouristBy(WRONG_ID);

        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
    }

}
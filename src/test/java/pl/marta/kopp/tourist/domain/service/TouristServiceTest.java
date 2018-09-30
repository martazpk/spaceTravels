package pl.marta.kopp.tourist.domain.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.marta.kopp.tourist.dto.TouristDto;
import pl.marta.kopp.tourist.repository.TouristRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TouristServiceTest {

    @Autowired
    private TouristRepository repository;
    private TouristService service;

    private static final String NAME="Dracula";
    private static final String COUNTRY="Poland";
    private static final String SURNAME="Alibaba";
    private static final long INCORRECT_IDENTIFIER=12345L;

    @Before
    public void setUp() throws Exception {
        service = new TouristService(repository);

    }

    @Test
    public void shouldGetTouristDtoWhenIdIsGiven() throws Exception {
        long id=givenTourist(NAME,SURNAME);
        TouristDto result=service.get(id);
        assertEquals(NAME,result.getName());
        assertEquals(SURNAME,result.getSurname());
    }

    private long givenTourist(String name, String surname) {
        TouristDto initialDto=new TouristDto.Builder(name, surname).build();
        return service.add(initialDto);
    }

    @Test(expected = TouristDoesNotExistException.class)
    public void shouldThrowExceptionWhenIncorrectIdIsGiven() throws Exception {
        service.get(INCORRECT_IDENTIFIER);
    }


    @Test
    public void shouldDeleteTouristWhenIdIsGiven() throws Exception {
        long identifier = givenTourist(NAME, SURNAME);
        service.delete(identifier);
        assertFalse(repository.isExist(identifier));

    }

    @Test
    public void shouldAddCountry() throws Exception {
        long identifier = givenTourist(NAME, SURNAME);
        TouristDto dtoWithCountry=new TouristDto.Builder(NAME,SURNAME).country(COUNTRY).build();
        service.update(identifier,dtoWithCountry);
        TouristDto result=service.get(identifier);

        assertEquals(COUNTRY,result.getCountry());
    }

}
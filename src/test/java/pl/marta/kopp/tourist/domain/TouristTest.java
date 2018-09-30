package pl.marta.kopp.tourist.domain;

import org.junit.Test;
import pl.marta.kopp.tourist.dto.TouristDto;

import static org.junit.Assert.*;

public class TouristTest {
    private static final String NAME = "some name";
    private static final String SURNAME = "some surname";


    @Test
    public void shouldReturnTouristDto() throws Exception {
        TouristDto initDto=new TouristDto.Builder(NAME,SURNAME).build();
        Tourist tourist= new Tourist(initDto);
        TouristDto result = tourist.asDto();

        assertTrue(result.getClass()==TouristDto.class);
        assertEquals(NAME,result.getName());

    }

}
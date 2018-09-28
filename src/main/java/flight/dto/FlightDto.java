package flight.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
@Getter
@Setter
public class FlightDto {
    private Calendar departure;
    private Calendar arrival;
    private int maxNumberOfSeats;
    private String priceValue;

    public FlightDto(Calendar departure, Calendar arrival, int maxNumberOfSeats, String priceValue) {
        this.departure = departure;
        this.arrival = arrival;
        this.maxNumberOfSeats = maxNumberOfSeats;
        this.priceValue = priceValue;
    }

    private FlightDto() {
    }
}

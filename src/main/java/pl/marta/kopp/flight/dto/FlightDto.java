package pl.marta.kopp.flight.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
@Getter
@Setter
public class FlightDto {
    private String departure;
    private String arrival;
    private int maxNumberOfSeats;
    private String priceValue;

    public FlightDto(String departure, String arrival, int maxNumberOfSeats, String priceValue) {
        this.departure = departure;
        this.arrival = arrival;
        this.maxNumberOfSeats = maxNumberOfSeats;
        this.priceValue = priceValue;
    }


    private FlightDto() {
    }
}

package pl.marta.kopp.flight.domain;

import pl.marta.kopp.flight.dto.FlightDto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import pl.marta.kopp.tourist.domain.Tourist;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue
    private long id;
    private Calendar departure;
    private Calendar arrival;
    private int maxNumberOfSeats;
    @Embedded
    private Price price;
    @ManyToMany
    private Set<Tourist> tourists;

    public Flight(FlightDto dto) {
        this.departure = dto.getDeparture();
        this.arrival = dto.getArrival();
        this.maxNumberOfSeats = dto.getMaxNumberOfSeats();
        this.price = new Price(dto.getPriceValue());
        this.tourists = new HashSet<>();
    }

    private Flight() {
    }

    public FlightDto asDto() {
       return new FlightDto(this.departure,this.arrival,this.maxNumberOfSeats,this.price.value());

    }


    public void update(FlightDto dto) {
        this.arrival=dto.getArrival();
        this.departure=dto.getDeparture();
        this.maxNumberOfSeats=dto.getMaxNumberOfSeats();
        this.price=new Price(dto.getPriceValue());
    }
}

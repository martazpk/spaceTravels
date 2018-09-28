package flight.domain;

import flight.dto.FlightDto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import tourist.domain.Tourist;

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

    FlightDto asDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, FlightDto.class);

    }


}

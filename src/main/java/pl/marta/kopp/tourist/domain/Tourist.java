package pl.marta.kopp.tourist.domain;

import pl.marta.kopp.flight.domain.Flight;
import lombok.Getter;
import lombok.Setter;
import pl.marta.kopp.flight.domain.JavaCalendarConverter;
import pl.marta.kopp.tourist.dto.TouristDto;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Tourist {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private Sex sex;
    private String country;
    private String description;
    private Calendar dateOfBirth;
    @ManyToMany(mappedBy = "tourists")
    private Set<Flight> flights;

    public Tourist(TouristDto touristDto) {
        this.name = touristDto.getName();
        this.surname = touristDto.getSurname();
        this.sex = Sex.valueOf(touristDto.getSex());
        this.country = touristDto.getCountry();
        this.description = touristDto.getDescription();
        this.dateOfBirth = JavaCalendarConverter.stringToCalendar(touristDto.getDateOfBirth());

        this.flights = new HashSet<>();
    }

    private Tourist() {
    }

    public TouristDto asDto() {
       return new TouristDto.Builder(this.name,this.surname)
               .country(this.country)
               .dateOfBirth(JavaCalendarConverter.calendarToString(this.dateOfBirth))
               .description(this.description)
               .sex(this.sex.toString()).build();

    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void update(TouristDto dto) {
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.dateOfBirth = JavaCalendarConverter.stringToCalendar(dto.getDateOfBirth());
        this.description = dto.getDescription();
        this.country = dto.getCountry();
        this.sex = Sex.valueOf(dto.getSex());
    }
}

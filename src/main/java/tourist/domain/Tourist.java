package tourist.domain;

import flight.Flight;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import tourist.dto.TouristDto;

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
    @Temporal(TemporalType.DATE)
    private Calendar dateOfBirth;
    @ManyToMany
    private Set<Flight> flights;

    public Tourist(TouristDto touristDto) {
        this.name = touristDto.getName();
        this.surname = touristDto.getSurname();
        this.sex = touristDto.getSex();
        this.country = touristDto.getCountry();
        this.description = touristDto.getDescription();
        this.dateOfBirth = touristDto.getDateOfBirth();

        this.flights=new HashSet<>();
    }

    private Tourist() {
    }

    TouristDto asDto(){
       ModelMapper modelMapper=new ModelMapper();
       TouristDto dto=modelMapper.map(this,TouristDto.class);
       return dto;
    }
}

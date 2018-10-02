package pl.marta.kopp.tourist.dto;

import lombok.Getter;
import lombok.Setter;
import pl.marta.kopp.tourist.domain.Sex;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Getter
@Setter
public class TouristDto {
    private String name;
    private String surname;
    private String sex;
    private String country;
    private String description;
    private String dateOfBirth;

    public TouristDto(Builder builder) {
        this.name=builder.name;
        this.surname=builder.surname;
        this.sex=builder.sex;
        this.country=builder.country;
        this.description=builder.description;
        this.dateOfBirth=builder.dateOfBirth;

    }

    private TouristDto() {
    }

    public static class Builder {
        private String name;
        private String surname;
        private String sex;
        private String country;
        private String description;
        private String dateOfBirth;

        public Builder(String name, String surname) {
            this.name=name;
            this.surname=surname;
        }


        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder dateOfBirth(String calendar) {
            this.dateOfBirth = calendar;
            return this;
        }

        public TouristDto build() {
            return new TouristDto(this);
        }
    }

}


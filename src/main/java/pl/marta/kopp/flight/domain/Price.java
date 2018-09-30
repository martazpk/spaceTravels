package pl.marta.kopp.flight.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
@Getter
@Setter
@Embeddable
public class Price {
    private BigDecimal value;

    public Price(String value) {
        this.value = new BigDecimal(value);
    }

    private Price() {
    }

    public String value() {
        return value.toString();
    }
}

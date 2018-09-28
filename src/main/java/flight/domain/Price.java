package flight.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Price {
    private BigDecimal value;

    public Price(String value) {
        this.value = new BigDecimal(value);
    }

    private Price() {
    }
}

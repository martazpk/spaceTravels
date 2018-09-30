package pl.marta.kopp.flight.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.marta.kopp.flight.domain.Flight;

import java.util.List;

public interface FlightRepository {
    Flight get(long id);

    void add(Flight flight);

    boolean isExists(long id);

    void delete(long id);

    void update(Flight flight);

    List<Flight> getAll(Specification<Flight> specification);

}

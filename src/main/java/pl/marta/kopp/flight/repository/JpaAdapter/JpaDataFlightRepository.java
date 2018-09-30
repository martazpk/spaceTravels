package pl.marta.kopp.flight.repository.JpaAdapter;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import pl.marta.kopp.flight.domain.Flight;
import pl.marta.kopp.flight.repository.FlightRepository;
import pl.marta.kopp.flight.repository.crudJpa.CrudJpaFlightRepository;

import java.util.List;
@Repository
public class JpaDataFlightRepository implements FlightRepository {
    private final CrudJpaFlightRepository repository;

    public JpaDataFlightRepository(CrudJpaFlightRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flight get(long id) {
        return repository.findOne(id);
    }

    @Override
    public void add(Flight flight) {
        repository.save(flight);
    }

    @Override
    public boolean isExists(long id) {
        return repository.exists(id);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public void update(Flight flight) {
        repository.save(flight);
    }

    @Override
    public List<Flight> getAll(Specification<Flight> specification) {
        return repository.findAll(specification);
    }
}

package pl.marta.kopp.flight.repository.crudJpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.marta.kopp.flight.domain.Flight;

@Repository
public interface CrudJpaFlightRepository extends CrudRepository<Flight,Long>,JpaSpecificationExecutor<Flight> {
}

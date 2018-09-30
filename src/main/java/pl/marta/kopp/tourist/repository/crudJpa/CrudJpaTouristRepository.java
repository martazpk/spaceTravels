package pl.marta.kopp.tourist.repository.crudJpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.marta.kopp.tourist.domain.Tourist;

@Repository
public interface CrudJpaTouristRepository extends CrudRepository<Tourist,Long>,JpaSpecificationExecutor<Tourist> {
}

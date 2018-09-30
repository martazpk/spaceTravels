package pl.marta.kopp.tourist.repository.adapterJpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import pl.marta.kopp.tourist.domain.Tourist;
import pl.marta.kopp.tourist.repository.TouristRepository;
import pl.marta.kopp.tourist.repository.crudJpa.CrudJpaTouristRepository;

import java.util.List;

@Repository
public class JpaDataTouristRepository implements TouristRepository {
    private final CrudJpaTouristRepository repository;

    public JpaDataTouristRepository(CrudJpaTouristRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tourist get(Long id) {
        return repository.findOne(id);
    }

    @Override
    public boolean isExist(long id) {

        return repository.exists(id);
    }

    @Override
    public void add(Tourist tourist) {
        repository.save(tourist);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public void update(Tourist tourist) {
        repository.save(tourist);
    }

    @Override
    public List<Tourist> findAll(Specification<Tourist> specification) {

        return repository.findAll(specification);
    }
}

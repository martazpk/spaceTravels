package pl.marta.kopp.tourist.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.marta.kopp.tourist.domain.Tourist;

import java.util.List;

public interface TouristRepository {
    Tourist get(Long id);

    boolean isExist(long id);

    void add(Tourist tourist);

    void delete(long id);

    void update(Tourist tourist);

    List<Tourist> findAll(Specification<Tourist> specification);
}

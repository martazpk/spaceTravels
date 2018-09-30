package pl.marta.kopp.tourist.service;

import org.springframework.stereotype.Service;
import pl.marta.kopp.tourist.domain.Tourist;
import pl.marta.kopp.tourist.dto.TouristDto;
import pl.marta.kopp.tourist.repository.TouristRepository;
@Service
public class TouristService {
    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public TouristDto get(long id){
        if(repository.isExist(id)){
            return repository.get(id).asDto();
        } else throw new TouristDoesNotExistException(id);
    }

    public long add(TouristDto dto){
        Tourist tourist=new Tourist(dto);
        repository.add(tourist);
        return tourist.getId();
    }

    public void delete(long id){
        if(repository.isExist(id)){
            repository.delete(id);
        }
        else throw new TouristDoesNotExistException(id);
    }
    
    public void update(long id,TouristDto dto){
        if(repository.isExist(id)){
            Tourist tourist = repository.get(id);
            tourist.update(dto);
            repository.add(tourist);

        }
    }
}

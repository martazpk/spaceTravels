package pl.marta.kopp.flight.domain.service;

import org.springframework.stereotype.Service;
import pl.marta.kopp.flight.domain.Flight;
import pl.marta.kopp.flight.dto.FlightDto;
import pl.marta.kopp.flight.repository.FlightRepository;


@Service
public class FlightService {
    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public FlightDto get(long id){
        if(repository.isExists(id)){
            return repository.get(id).asDto();
        } else throw new FlightDoesNotExistException(id);
    }

    public long add(FlightDto dto){
        Flight flight=new Flight(dto);
        repository.add(flight);
        return flight.getId();
    }

    public void delete(long id){
        if(repository.isExists(id)){
            repository.delete(id);
        }
        else throw new FlightDoesNotExistException(id);
    }

    public void update(long id,FlightDto dto){
        if(repository.isExists(id)){
            Flight flight = repository.get(id);
            flight.update(dto);
            repository.add(flight);

        }
    }
}

package pl.marta.kopp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.marta.kopp.flight.domain.service.FlightDoesNotExistException;
import pl.marta.kopp.tourist.domain.service.TouristDoesNotExistException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({FlightDoesNotExistException.class, TouristDoesNotExistException.class})
    public ResponseEntity<String>notFound(RuntimeException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}

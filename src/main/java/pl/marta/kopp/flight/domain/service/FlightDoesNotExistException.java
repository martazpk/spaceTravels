package pl.marta.kopp.flight.domain.service;

public class FlightDoesNotExistException extends RuntimeException {
    public FlightDoesNotExistException(long id) {
        super("Flight "+id+" does not exist.");
    }
}

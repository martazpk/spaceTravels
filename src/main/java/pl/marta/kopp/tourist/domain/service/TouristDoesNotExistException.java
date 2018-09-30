package pl.marta.kopp.tourist.domain.service;

public class TouristDoesNotExistException extends RuntimeException {
    public TouristDoesNotExistException(long id) {
        super("Tourist "+id+" does not exist.");
    }
}

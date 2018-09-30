package pl.marta.kopp.tourist.service;

public class TouristDoesNotExistException extends RuntimeException {
    public TouristDoesNotExistException(long id) {
        super("Tourist "+id+" does not exist.");
    }
}

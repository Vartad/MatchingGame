package main.exceptions;


public class QuitException
        extends RuntimeException {
    public QuitException(String errorMessage) {
        super(errorMessage);
    }
}
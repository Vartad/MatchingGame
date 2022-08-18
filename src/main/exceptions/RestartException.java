package main.exceptions;

public class RestartException
        extends RuntimeException{

    public RestartException(String errorMessage) {
        super(errorMessage);
    }
}



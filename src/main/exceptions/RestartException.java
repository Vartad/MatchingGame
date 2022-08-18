package main.exceptions;

import main.UI;

/**
 * It's Exception class used to restart main part of the game, at the difficulty level choice.
 * @see UI#validateGamePhrases()
 */
public class RestartException
        extends RuntimeException{

    public RestartException(String errorMessage) {
        super(errorMessage);
    }
}



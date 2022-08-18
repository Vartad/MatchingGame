package main.exceptions;

import main.UI;

/**
 * It's Exception class used to quit the game.
 * @see UI#validateGamePhrases()
 */
public class QuitException
        extends RuntimeException {
    public QuitException(String errorMessage) {
        super(errorMessage);
    }
}
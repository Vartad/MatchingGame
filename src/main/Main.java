package main;

import main.exceptions.QuitException;
import main.exceptions.RestartException;

import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;
import static main.CONST.YES;

/**
 * Memory game
 * @author Mateusz Gasior
 * 17.08.2022
 */

public class Main {
    /**
     * Main class of whole project, here the game is launched.
     * The memory game is about to match all words pairs. Number of pairs (4 or 8) depend on the difficulty level
     * (Easy or Hard). Also, a guess chances number (10 or 15) change with difficulty level.
     * Words are loaded from text file.
     *
     */

    public static void main(String[] args) {
        try{
            game();
        } catch (QuitException e) {
            System.out.println(e.getMessage());
        } catch (Exception qe){
            System.out.println("Ehh, something go wrong.");
        }
    }

    private static void game() {
        FilesIO.loadFile("Words.txt");
        Scanner scanner = new Scanner(System.in);
        String input = YES;
        UI.showWelcomeMessage();
        UI.showInstruction();
        while(input.equals(YES)) {
            try {
                HashMap<String, String> validation;
                do {
                    System.out.println("choose difficulty level Easy or Hard.");
                    validation = UI.validateDifficulty(scanner.nextLine());
                    input = validation.get("input");
                }
                while (!parseBoolean(validation.get("valid")));
                Game game = new Game(input, FilesIO.getWords());
                game.run();
                do {
                    System.out.println("Would you like to play again?");
                    validation = UI.validateGameRestart(scanner.nextLine());
                    input = validation.get("input");
                }
                while (!parseBoolean(validation.get("valid")));
            }catch (RestartException re){
                input = YES;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
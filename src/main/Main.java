package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static java.lang.Boolean.parseBoolean;

import main.score.ScoreManager;
import main.exceptions.QuitException;
import main.exceptions.RestartException;
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
        } catch (QuitException qe) {
            System.out.println(qe.getMessage());
        } catch (Exception e){
            System.out.println("Quit game");
        }
    }

    /**
     * game's main flow
     */
    private static void game(){
        ArrayList<String> WORDS = FilesIO.loadResourcesFile("Words.txt");
        Scanner scanner = new Scanner(System.in);
        String input = YES;
        UI.showWelcomeMessage();
        try {
            ScoreManager.top10();
        }catch (Exception e){
            System.out.println("Scores file seems to be damaged, consider deleting it");
        }
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
                Game game = new Game(input, WORDS);
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
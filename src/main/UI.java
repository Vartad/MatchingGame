package main;

import main.exceptions.QuitException;
import main.exceptions.RestartException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static main.CONST.*;

/**
 * This is a model class to hold validation methods and messages printing methods.
 */
public class UI {

    private static String INPUT; // every method assign inserted input value to INPUT
    private static HashMap<String, String > result; // result of Validation. Contains :
    // "valid" : result of validation: "false" or "true"
    // "input" : same input as given or slightly updated

    /**
     * Validates user's input for difficulty level choice. Used in main loop.
     * @param input String data inserted by user. Expected to be {@link CONST#HARD} or {@link CONST#EASY}
     * @return
     *  "valid" : if valid "true", otherwise "false"
     *  "input" : validated input
     * @see Main#main(String[])
     */
    public static HashMap<String, String>validateDifficulty(String input){
        result = new HashMap<>();
        INPUT = input.toLowerCase(Locale.ROOT);
        validateGamePhrases();
        String[] CORRECT_PHRASES = new String[]{EASY, HARD};
        for (String correct_phrase : CORRECT_PHRASES) {
            if (INPUT.contains(correct_phrase)) {
                result.put("input", correct_phrase);
                result.put("valid", "true");
                return result;
            }
        }
        result.put("valid","false");
        return result;
    }

    /**
     * Validates user's input for choice of restarting the game. Used in the main loop.
     * @param input String data inserted by user. Expected to be {@link CONST#YES} or {@link CONST#NO}
     * @return
     *  "valid" : if valid "true", otherwise "false"
     *  "input" : validated input
     */
    public static HashMap<String, String>validateGameRestart(String input){
        result = new HashMap<>();
        INPUT = input.toLowerCase(Locale.ROOT);
        validateGamePhrases();
        String[] CORRECT_PHRASES = new String[]{YES, NO};
        for (String correct_phrase : CORRECT_PHRASES) {
            if (INPUT.contains(correct_phrase)) {
                result.put("input", correct_phrase);
                result.put("valid", "true");
                return result;
            }
        }
        result.put("valid","false");
        return result;
    }

    /**
     * Validates user's input for name after won game.
     * @param input String data inserted by user.
     * @return
     *  "valid" : if valid "true", otherwise "false"
     *  "input" : validated input
     */
    public static HashMap<String, String>validateUserName(String input){
        result = new HashMap<>();
        INPUT = input.toLowerCase(Locale.ROOT);
        validateGamePhrases();
        if (INPUT.length()>15) {
            System.out.println("Try to come up with a nickname");
            result.put("valid", "false");
            return result;
        }
        if (input.length()==0){
            result.put("valid", "false");
            return result;
        }
        if (input.contains(" ")){
            result.put("valid", "false");
            return result;
        }
        if (input.contains("|")){
                System.out.println("You can't use '|' in your name");
                result.put("valid", "false");
                return result;
            }
        result.put("input", input);
        result.put("valid", "true");
        return result;

    }


    /**
     * Validates user's input for options available during whole game. Called at the beginning of every validation method.
     * available options:
     * @throws QuitException to quit the game.
     * @throws RestartException to restart the game.
     * <ul>
     *    <li> help - calls {@link UI#showInstruction() }</li>
     *    <li> quit - end game by throwing {@link QuitException}</li>
     *    <li> restart - restart game by throwing {@link RestartException}</li>
     * </ul>
     *
     */
    public static void validateGamePhrases(){
        if(INPUT.contains("help")) showInstruction();
        if (INPUT.contains("quit")||INPUT.contains("restart")||INPUT.contains("top scores")){
            System.out.println("Are you sure you want to "+ INPUT+"? (yes/no)");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().toLowerCase(Locale.ROOT).equals(YES)) {
                if (INPUT.contains("quit")) throw new QuitException("See you next time :D");
                if (INPUT.contains("restart")) throw new RestartException("let's start over");
                if (INPUT.contains("top scores"))Score.top10();
            }
        }
    }

    /**
     * Validates user's input for tile choice.
     * @param input String data inserted by user. Expected to be {@link Tile#}'s coordinate
     * @param board active {@link Board} in the game.
     * @return
     *  "valid" : if valid "true", otherwise "false"
     *  "input" : validated input
     */
    public static HashMap<String, String>validateTilePick(String input,Board board){
        result = new HashMap<>();
        INPUT = input.toLowerCase(Locale.ROOT);
        validateGamePhrases();
        // Tile's coordinate length is always 2
        if(INPUT.length()!=2){
            result.put("valid","false");
            return result;
        }
        if(board.getTile(input)==null){
            System.out.println("There is no such a tile. Coordinate has to consist of a row and a column  values eg 'A1'");
            result.put("valid","false");
            return result;
        }
        result.put("input", input);
        result.put("valid", "true");
        return result;
    }

    /**
     * Prints help.
     */
    public static void showInstruction(){
        System.out.println("""
                To pick a word give it's coordinate eg "A1"
                
                  1      2
                A word   X
                B X      X
                
                To quit game insert quit.
                To see help  insert help.
                To restart   insert restart.
                to show top scores insert top scores
                
                """);
    }

    /**
     * Prints welcome message.
     */
    public static void showWelcomeMessage(){
        System.out.println("""
                Welcome in memory game! Match all word pairs to win.

                You can choose between two difficulty levels:
                
                Easy - 4 word pairs, 10 guess chances
                Hard - 8 word pairs, 15 guess chances
                
                Time is counted ;)
                
                """);
    }

}

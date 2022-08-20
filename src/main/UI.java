package main;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

import static main.CONST.*;
import main.score.ScoreManager;
import main.exceptions.QuitException;
import main.exceptions.RestartException;

/**
 * This is a model class to hold validation methods and messages printing methods.
 */
public class UI {

    private static String INPUT; // every method assign inserted input value to INPUT
    private static HashMap<String, String > result; // result of Validation. Contains :
    // "valid" : result of validation: "false" or "true"
    // "input" : same input as given or slightly updated

    /**
     * Puts input and validation result in HashMap and returns it.
     * @return
     * Hashmap  { "valid" : TRUE/FALSE,
     *            "input" : input if validation is true
     *            }
     */
    public static HashMap<String, String> validationDecorator() {

        HashMap<String,String> result = new HashMap<>();
        if(INPUT.equals(FALSE)){
            result.put("valid",FALSE);
        }else{
            result.put("input", INPUT);
            result.put("valid", TRUE);
        }
        return result;
    }

    /**
     * Check if inserted string contains allowed phrases.
     * @param allowedPhrases
     * String[], list of allowed phrases.
     */
    public static void allowedPhrases(String[] allowedPhrases){
        for (String correct_phrase : allowedPhrases) {
            if (INPUT.contains(correct_phrase)) {
                INPUT = correct_phrase;
                return;
            }
        }
        INPUT = FALSE;
    }

    /**
     * Validates user's input for difficulty level choice. Used in main loop.
     * @param input String data inserted by user. Expected to be {@link CONST#HARD} or {@link CONST#EASY}
     * @return
     *  "valid" : if valid "true", otherwise "false"
     *  "input" : validated input
     * @see Main#main(String[])
     */
    public static HashMap<String, String>validateDifficulty(String input){
        INPUT = input.toLowerCase(Locale.ROOT);
        validateGamePhrases();
        allowedPhrases(new String[]{EASY, HARD});
        return validationDecorator();
    }

    /**
     * Validates user's input for choice of restarting the game. Used in the main loop.
     * @param input String data inserted by user. Expected to be {@link CONST#YES} or {@link CONST#NO}
     * @return
     *  "valid" : if valid "true", otherwise "false"
     *  "input" : validated input
     */
    public static HashMap<String, String>validateGameRestart(String input){

        INPUT = input.toLowerCase(Locale.ROOT);
        validateGamePhrases();
        allowedPhrases(new String[]{YES, NO});
        return validationDecorator();

    }

    /**
     * Validates user's input for name after won game.
     * @param input String data inserted by user.
     * @return
     *  "valid" : if valid "true", otherwise "false"
     *  "input" : validated input
     */
    public static HashMap<String, String>validateUserName(String input){
        INPUT = input.toLowerCase(Locale.ROOT);
        validateGamePhrases();
        if (INPUT.length()>ALLOWED_NAME_LENGTH) {
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
                if (INPUT.contains("top scores")) ScoreManager.top10();
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
        INPUT = input.toUpperCase(Locale.ROOT);
        // Tile's coordinate length is always 2
        if(input.length()!=2){
            result.put("valid","false");
            return result;
        }
        if(board.getTile(INPUT)==null){
            System.out.println("There is no such a tile. Coordinate has to consist of a row and a column  values eg 'A1'");
            result.put("valid","false");
            return result;
        }
        result.put("input", INPUT);
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

package main;

import java.util.HashMap;
import java.util.Locale;

import static main.CONST.*;

public class UI {

    private static String INPUT;
    private static HashMap<String, String > result;

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
    public static void validateGamePhrases(){
//        if(INPUT.contains("quit")) throw new Exception("quit game"); TODO: add error exception to quit the game;
        if(INPUT.contains("help")) showInstruction();
//        if(INPUT.contains("restart")) throw new Exception("quit game"); TODO: add error exception to restart the game;
    }

    public static void showInstruction(){
        System.out.println("""
                Welcome in memory game! Match all word pairs to win.

                You can choose between two difficulty levels:
                
                Easy - 4 word pairs, 10 guess chances
                Hard - 8 word pairs, 15 guess chances
                
                To pick a word give it's coordinate eg "A1"
                
                  1      2
                A word   X
                B X      X
                
                Time is also counted ;)
                
                To quit game insert quit.
                To see help insert help.
                To restart insert restart.

                """);
    }

}

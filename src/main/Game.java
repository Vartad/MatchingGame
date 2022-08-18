package main;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Boolean.parseBoolean;
import static main.CONST.*;
import static main.FilesIO.saveScoreRecord;

/**
 * This is a model class to hold game information and methods. Here is game logic.
 */
public class Game {
    private Tile previousTile;  //Tile object of a previously picked Tile
    private final Score score;    //Score object to track score and count time.
    private final Board BOARD;  //Board object of a currently running game.
    private final int WORD_PAIRS;    //Number of pairs of words.


    /**
     *{@link Game} constructor. Depending on a chosen level (Hard or Easy), number of chances
     *  (10 or 15) and number of pairs of words (4 or 8) are set. Words used in the game are randomized from inputWords
     * @param difficulty
     *  String, the difficulty level that determines the parameters of a game.
     * @param inputWords
     *  ArrayList of words from which will be randomized words for a game.
     * @see Score keep track of chances.
     */
    public Game(String difficulty, ArrayList<String> inputWords) throws Exception {
        if(difficulty.equals(EASY)){
            score = new Score(difficulty,10);
            WORD_PAIRS = 4;
        } else if (difficulty.equals(HARD)) {
            score = new Score(difficulty,15);
            WORD_PAIRS = 8;
        }else {
            throw new Exception();
        }
        ArrayList<String> randomizedWords = randomizeWords(WORD_PAIRS,inputWords);
        BOARD = new Board(randomizedWords);
    }

    /**
     * Logic method of the Game. Takes care of a correct flow of the game.
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        //allow user to pick tiles till they have guess chances.
        while (score.getChances()>0){
            score.showScore();
            BOARD.show();
            String input;
            HashMap<String, String> validation;
            do {
                System.out.println("\nPick a tile");
                validation = UI.validateTilePick(scanner.nextLine(),BOARD);
                input = validation.get("input");
            }
            while (!parseBoolean(validation.get("valid")));
            Tile chosenTile = BOARD.getTile(input);
            if(chosenTile.getContent().equals("X")){
                //chosen tile is hidden
                chosenTile.setContent(chosenTile.getWORD());
                //check if there is previously picked tile. When it's beginning of a game or tiles were matched
                //then there won't be any.
                if(previousTile != null) {
                    if (previousTile.getWORD().equals(chosenTile.getWORD())) {
                        // Keep them visible, set that they are matched.
                        BOARD.getTile(previousTile.getCoor()).setMatched();
                        BOARD.getTile(chosenTile.getCoor()).setMatched();
                        previousTile.setMatched();
                        previousTile = null;
                        score.incrementGuesses();
                    }else {
                        //picked Tile is not matching previously picked tile. Hide both tiles, decrement chances.
                        BOARD.show();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        previousTile.setContent("X");
                        previousTile = null;
                        chosenTile.setContent("X");
                        score.decrementChances();
                        score.incrementGuesses();
                    }
                }else{
                    //there is no previously picked tile.
                    previousTile = chosenTile;
                }
            }else{
                //chosen tile is visible
                if(chosenTile.isMatched()){
                    System.out.println("You already matched this one, try another.");
                }else {
                    System.out.println("You just choose this one, try another.");
                }
            }
            if(checkGameEnd()){
                gameWon();
                return;
            }
        }
        gameLost();
    }

    /**
     * Randomize words for a game from the given ArrayList of loadedWords. Firstly it picks pairs of words, then it uses
     * {@link Collections#shuffle(List)} to change order of words in the random way.
     * @param wordsPairs
     *  int, number of pairs of words that have to be picked.
     * @param loadedWords
     *  ArrayList of words loaded from file from which to pick.
     * @return words
     *  ArrayList of randomized, shuffled words needed to create {@link Tile}'s and {@link Board}
     */
    private ArrayList<String> randomizeWords(int wordsPairs,ArrayList<String> loadedWords){
        ArrayList<String> words = new ArrayList<>();
        int randNum;
        for(int i=0;i<wordsPairs;i++){
            randNum = (int)(Math.random()*loadedWords.size());
            words.add(loadedWords.get(randNum));
            words.add(loadedWords.get(randNum));
        }
        Collections.shuffle(words);
        return words;
    }

    /**
     * Checks if game should be ended. Checked condition is whether all words are matched.
     * @return true if game end, false otherwise
     */
    private boolean checkGameEnd(){
        for(Map.Entry<String, Tile> entry : BOARD.getBoard().entrySet()) {
            if(!entry.getValue().isMatched()) return false;
        }
        return true;
    }

    /**
     * Method executes set of instructions if user wins:
     * <ul>
     *     <li>allow to save score</li>
     *     <li>shows top 10 scores</li>
     * </ul>
     */
    private void gameWon(){
        score.finish();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Congratulations! You matched all words.\nYour final score is:");
        BOARD.show();
        score.showFinalScore();
        System.out.println("Would you like to save you score?");
        if(scanner.nextLine().toLowerCase(Locale.ROOT).equals("yes")){
            String input;
            HashMap<String, String> validation;
            do {
                System.out.println("\nWhat is your name?");
                validation = UI.validateUserName(scanner.nextLine());
                input = validation.get("input");
            }
            while (!parseBoolean(validation.get("valid")));

            String[] scoreRecord = new String[]{
                    input,
                    DATE_FORMATTER.format(new Date()),
                    Long.toString(score.getEndTime()),
                    Integer.toString(score.getGuesses())
            };
            saveScoreRecord(scoreRecord);
        }
        Score.top10();
    }
    /**
     * Method executes set of instructions if user loses
     */
    private void gameLost(){
        score.finish();
        System.out.printf("Ahh, not this time:)\nYou matched %s out of %s pairs.\n",score.getMatchedPairs(), WORD_PAIRS);
        score.showFinalScore();
        Score.top10();
    }
}

package main;

import java.util.*;

/**
 * This is a model class to hold game information and methods. Here is game logic.
 */
public class Game {
    private int chances;    //Number of Guess chances left.
    private Tile previousTile;  //Tile object of a previously picked Tile
    private final Board BOARD;  //Board object of a currently running game.
    private final String DIFFICULTY;    //Difficulty level chosen by a user.
    private static final String DIVIDING_LINE_SHORT = "-------------------------"; //To separate score from board.
    private static final String DIVIDING_LINE_LONG = "--------------------------------------------------";
    //To separate data between boards prints.

    /**
     *{@link Game} constructor. Depending on a chosen level (Hard or Easy), number of {@link Game#chances}
     *  (10 or 15) and number of pairs of words (4 or 8) are set. Words used in the game are randomized from inputWords
     * @param difficulty
     *  String, the difficulty level that determines the parameters of a game.
     * @param inputWords
     *  ArrayList of words from which will be randomized words for a game.
     */
    public Game(String difficulty, ArrayList<String> inputWords){
        DIFFICULTY = difficulty;
        int wordsPairs = 0;
        if(difficulty.equals("Easy")){
            chances = 10;
            wordsPairs = 4;
        } else if (difficulty.equals("Hard")) {
            chances = 15;
            wordsPairs = 8;
        }
        ArrayList<String> randomizedWords = randomizeWords(wordsPairs,inputWords);
        BOARD = new Board(randomizedWords);
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
        ArrayList<String> words = new ArrayList<String>();
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
     * Logic method of the Game. Takes care of a correct flow of the game.
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        //allow user to pick tiles till they have guess chances.
        while (chances>=1){
            System.out.println(DIVIDING_LINE_LONG);
            showScore();
            System.out.println(DIVIDING_LINE_SHORT);
            BOARD.show();
            System.out.println("\nPick a tile");
            String input = scanner.nextLine();
            Tile chosenTile = BOARD.getTile(input);
            if(chosenTile.getContent().equals("X")){
                //chosen tile is hiden
                chosenTile.setContent(chosenTile.getWORD());
                //check if there is previously picked tile. When it's beginning of a game or tiles were matched
                //then there won't be any.
                if(previousTile != null) {
                    if (previousTile.getWORD().equals(chosenTile.getWORD())) {
                        //matching tiles are matched. Keep them visible, set that they are matched.
                        BOARD.getTile(previousTile.getCoor()).setMatched();
                        BOARD.getTile(chosenTile.getCoor()).setMatched();
                        previousTile.setMatched();
                        previousTile = null;
                        chances++;
                    }else {
                        //picked Tile is not matching previously picked tile. Hide previous tile.
                        previousTile.setContent("X");
                        previousTile = chosenTile;
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
                    System.out.println("You just choose this one, try another one.");
                }
                //For picking the same tile chances counter won't decrement
                chances++;
            }
            chances --;
            if(checkGameEnd()){
                System.out.println("Congratulations! You matched all words.\nYour final score is:");
                showScore();
                break;
            }
        }
    }

    /**
     * Prints data about a currently running game.
     */
    public void showScore(){
        System.out.printf("Difficulty level %s %n",DIFFICULTY);
        System.out.printf("Guess chances    %s %n",chances);
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
}

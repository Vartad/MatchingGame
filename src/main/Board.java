package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static main.CONST.DIVIDING_LINE_LONG;


/**
 * This is a model class to hold board information and methods.
 */
class Board {

    private final int BOARD_WIDTH = 4;
    private final char [] BOARD_HEIGHT = new char[]{'A','B','C','D'};
    private HashMap<String, Tile> board = new HashMap<>();
     /**The Formatting value to print tiles equally spaced. It is the length of the longest
      * word in the running game.
      * @see Board() Constructor
      */
    private final String COLUMNFORMAT;

    /**
     * Constructor of the Board. Create {@link Tile} objects and Append them to the {@link Board#board}. Finds the longest
     * word from words and creates {@link Board#COLUMNFORMAT}.
     * @param words
     *  ArrayList of words, already randomized for a game.
     */
    public Board(ArrayList<String> words) {
        int tmpLongestWordLength = 0;
        int wordCounter = 0;
        for(int i = 0;i< words.size()/4;i++){
            char verticalCoor = BOARD_HEIGHT[i];
            for(int j=0;j<BOARD_WIDTH;j++){
                Tile tile = new Tile(words.get(wordCounter));
                tile.setCoor(verticalCoor + Integer.toString(j + 1));
                tile.setContent("X");
                board.put(tile.getCoor(),tile);
                if (words.get(wordCounter).length() > tmpLongestWordLength) {
                    tmpLongestWordLength = words.get(i).length();
                }
                wordCounter++;
            }
        }
        COLUMNFORMAT = "%" + (tmpLongestWordLength+1) + "s";
    }

    public HashMap<String, Tile> getBoard() {
        return board;
    }

    /**
     * Prints the board.
     */
    public void show(){

        System.out.println(DIVIDING_LINE_LONG+DIVIDING_LINE_LONG);
        //print column headers
        StringBuilder line = new StringBuilder();
        line.append(String.format(COLUMNFORMAT, ' '));
        for (int i =1;i<=BOARD_WIDTH;i++) {
            line.append(String.format(COLUMNFORMAT, i));
        }
        System.out.println(line);
        //print rows
        line = new StringBuilder();
        ArrayList<String> sortedKeys = new ArrayList<>(board.keySet());
        Collections.sort(sortedKeys);
        int rowCounter = 0;
        for(int i = 0;i< board.size()/4;i++){
            line.append(String.format(COLUMNFORMAT, BOARD_HEIGHT[i]));
            for(int j=0;j<BOARD_WIDTH;j++){
                String tileCoor = sortedKeys.get(j+rowCounter);
                line.append(String.format(COLUMNFORMAT, board.get(tileCoor).getContent()));
            }
            rowCounter+=BOARD_WIDTH;
            System.out.println(line);
            line = new StringBuilder();
        }
        System.out.println(DIVIDING_LINE_LONG+DIVIDING_LINE_LONG);
    }

    /**
     * Get a {@link Tile} with a given coordinates from the {@link Board}'s HashMap of {@link Tile}s
     * @param coordinates String coordinates value of a {@link Tile} eg. "A1".
     * @return {@link Tile}
     */
    public Tile getTile(String coordinates) {
        return board.get(coordinates);
    }

}

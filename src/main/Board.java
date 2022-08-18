package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static main.CONST.DIVIDING_LINE_LONG;


/**
 * This is a model class to hold board information and methods.
 */
class Board {

    private final HashMap<String, Tile> board = new HashMap<>();
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
        for (int i = 0; i < words.size(); i++) {
            Tile tile = new Tile(words.get(i));
            if (i < words.size() / 2) {
                tile.setCoor("A" + (i + 1));
            } else {
                tile.setCoor("B" + (words.size() - i));
            }
            tile.setContent("X");
            board.put(tile.getCoor(),tile);
            if (words.get(i).length() > tmpLongestWordLength) {
                tmpLongestWordLength = words.get(i).length();
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
        for (int i =1;i<=board.size()/2;i++) {
            line.append(String.format(COLUMNFORMAT, i));
        }
        System.out.println(line);
        //print rows
        line = new StringBuilder();
        line.append(String.format(COLUMNFORMAT, "A"));
        ArrayList<String> sortedKeys = new ArrayList<>(board.keySet());
        Collections.sort(sortedKeys);
        int i = 0;
        for(String tileCoor : sortedKeys){
            line.append(String.format(COLUMNFORMAT, board.get(tileCoor).getContent()));
            if ((i+1 )%(board.size()/2) ==0) {
                System.out.println(line);
                line = new StringBuilder();
                line.append(String.format(COLUMNFORMAT, "B"));
            }
            i++;
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

package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This is a model class to hold tile information.
 */
class Tile{

    private String coor; //Coordinates on the board like "A1".
    private String content; //The content of the tile that is visible. Can be "X" or a word.
    private boolean matched; //Holds information whether the tile is matched or not.
    private final String WORD; //Holds word of the tile.

    /**
     * Tile constructor.
     * @param word is a word to display on a tile.
     */
    public Tile(String word){
        WORD = word;
        matched = false;
    }

    /**
     * Checks if the tile has been matched.
     * @return true if matched, false otherwise.
     */
    public boolean isMatched() {
        return matched;
    }

    /**
     * Sets Tile's matched parameter as true.
     */
    public void setMatched() {
        this.matched = true;
    }

    public String getWORD() {
        return WORD;
    }

    public String getCoor() {
        return coor;
    }

    public void setCoor(String coor) {
        this.coor = coor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

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

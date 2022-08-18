package main;

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
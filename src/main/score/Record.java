package main.score;

/**
 * This is a model class to hold score data.
 */
public class Record implements Comparable{

    private String name; //users name
    private String date; //time when saving score
    private long guessingTime; //duration of the game
    private int guessingTries; //number of guesses it took user to win the game.
    private String[] variablesList; //score data stored as list
    private String varaiblesString; //score data stored as String

    /**
     * Add data to object from a string taken from a score file
     * @param data
     * String line from the score file.
     */
    public void loadRecord(String data) {

        varaiblesString = data;
        variablesList = varaiblesString.split("\\|");
        this.name = variablesList[0];
        this.date = variablesList[1];
        this.guessingTime = Long.parseLong(variablesList[2]);
        this.guessingTries = Integer.parseInt(variablesList[3]);
    }

    /**
     * Compares two Record objects' guessing time, returns one with shorter time
     * @param o the object to be compared.
     * @return
     * Record object with shorter guessing time
     */
    @Override
    public int compareTo(Object o) {
        long compareTime = ((Record)o).getGuessingTime();
        return (int) (this.guessingTime - compareTime);
    }

    @Override
    public String toString() {
        return varaiblesString;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getGuessingTime() {
        return guessingTime;
    }

    public void setGuessingTime(long guessingTime) {
        this.guessingTime = guessingTime;
    }

    public int getGuessingTries() {
        return guessingTries;
    }

    public void setGuessingTries(int guessingTries) {
        this.guessingTries = guessingTries;
    }

    public String[] getVariablesList() {
        return variablesList;
    }

    public void setVaraiblesString(String varaiblesString) {
        this.varaiblesString = varaiblesString;
    }

}
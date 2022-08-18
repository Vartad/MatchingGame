package main;

/**
 * This is a model class to hold score data.
 */
public class Record implements Comparable{

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

    public void setVariablesList(String[] variablesList) {
        this.variablesList = variablesList;
    }

    public void setVaraiblesString(String varaiblesString) {
        this.varaiblesString = varaiblesString;
    }

}
package main;

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

    public int getGuessingTires() {
        return guessingTires;
    }

    public void setGuessingTires(int guessingTires) {
        this.guessingTires = guessingTires;
    }

    public String[] getVariablesList() {
        return variablesList;
    }

    public void setVariablesList(String[] variablesList) {
        this.variablesList = variablesList;
    }

    public String getVaraiblesString() {
        return varaiblesString;
    }

    public void setVaraiblesString(String varaiblesString) {
        this.varaiblesString = varaiblesString;
    }

    private String name;
    private String date;
    private long guessingTime;
    private int guessingTires;

    private String[] variablesList;
    private String varaiblesString;

    public Record(String data) {

        varaiblesString = data;
        variablesList = varaiblesString.split("\\|");
        this.name = variablesList[0];
        this.date = variablesList[1];
        this.guessingTime = Long.parseLong(variablesList[2]);
        this.guessingTires = Integer.parseInt(variablesList[3]);
    }

    public void print(){
        System.out.println(varaiblesString);
    }

    @Override
    public int compareTo(Object o) {
        long compareTime = ((Record)o).getGuessingTime();
        return (int) (this.guessingTime - compareTime);
    }

    @Override
    public String toString() {
        return varaiblesString;
    }

}
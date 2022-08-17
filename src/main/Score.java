package main;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.pow;
import static main.CONST.HARD;

/**
 * This is a model class to hold game's score information, track it, preform needed operations.
 */
public class Score {
    private int chances;    //Number of Guess chances left.
    private int finalScore;
    private int matchedPairs;
    public final String DIFFICULTY;    //Difficulty level chosen by a user.
    private static long START_TIME;

    /**
     * {@link Score} constructor. Keeps data related with score calculation.
     * @param difficulty
     *  String, level of game difficulty.
     */
    public Score(String difficulty){
        START_TIME = new Date().getTime();
        DIFFICULTY = difficulty;
        finalScore = 0;
        matchedPairs = 0;
    }

    /**
     * Prints data about a currently running game.
     *  @see Score#showFinalScore()
     */
    public void showScore(){
        System.out.printf("Difficulty level %s %n",DIFFICULTY);
        System.out.printf("Guess chances    %s %n",chances);
        System.out.printf("Time             %s %n",time());
    }

    /**
     * Print final score. Calls {@link Score#time()} and {@link Score#calculateFinalScore(long)} just before to
     * calculate achieved results.
     * @see Score#time()
     * @see Score#calculateFinalScore(long)
     * @see Score#showScore()
     */
    public void showFinalScore(){
        long time = time();
        calculateFinalScore(time);
        System.out.printf("Difficulty level   %s %n",DIFFICULTY);
        System.out.printf("Time               %s %n",time);
        System.out.printf("Final score        %s %n",finalScore);
    }

    /**
     * Calculates {@link Score#finalScore} of the game. Score depends on {@link Score#DIFFICULTY}, {@link Score#chances}
     * and time. The harder difficulty level and the more chances left and the shorter time, the higher score is.
     * @param time
     *  long time when the game ends.
     * @see Score#time()
     * @see Score#showScore()
     * @see Score#showFinalScore()
     */
    private void calculateFinalScore(long time){
        int lvlCoff = 1;
        if(DIFFICULTY.equals(HARD)) lvlCoff = 2;
        this.finalScore = (int) ( lvlCoff * (chances+0.1) *1/(pow(time,2))*100000.0);
    }

    /**
     * Counts time between beginning of the game and this moment. Uses:
     * @see Date#getTime()
     * @see TimeUnit#MILLISECONDS
     * @return long
     *  time
     */
    private static long time(){
        long now = new Date().getTime();
        return TimeUnit.MILLISECONDS.
                toSeconds(now - START_TIME);
    }

    public int getChances() {
        return chances;
    }

    public void incrementChances() {
        this.chances++;
    }

    public void decrementChances() {
        this.chances--;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setChances(int chances) {
        this.chances = chances;
    }

    public int getMatchedPairs() {
        return matchedPairs;
    }

    public void incrementMatchedTiles() {
        matchedPairs++;
    }

}

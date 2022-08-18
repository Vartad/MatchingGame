package main;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.pow;
import static main.CONST.DIVIDING_LINE_SHORT;
import static main.CONST.HARD;

/**
 * This is a model class to hold game's score information, track it, preform needed operations.
 */
public class Score {
    private int chances;    //Number of Guess chances left.
    private int guesses; //Number of taken guesses
    private int finalScore;
    private int matchedPairs;

    public long getEndTime() {
        return endTime;
    }

    private long endTime;
    public final String DIFFICULTY;    //Difficulty level chosen by a user.
    private static long START_TIME;

    public int getCHANCES_START() {
        return CHANCES_START;
    }

    private final int CHANCES_START;

    /**
     * {@link Score} constructor. Keeps data related with score calculation.
     * @param difficulty
     *  String, level of game difficulty.
     */
    public Score(String difficulty, int chances){
        guesses = 0;
        CHANCES_START = chances;
        this.chances = chances;
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
        System.out.println(DIVIDING_LINE_SHORT);
        System.out.printf("Difficulty level %s %n",DIFFICULTY);
        System.out.printf("Time             %s %n",time());
        System.out.println(DIVIDING_LINE_SHORT);
    }

    /**
     * Print final score. Calls {@link Score#time()} and {@link Score#calculateFinalScore(long)} just before to
     * calculate achieved results.
     * @see Score#time()
     * @see Score#calculateFinalScore(long)
     * @see Score#showScore()
     */
    public void showFinalScore(){
        System.out.println(DIVIDING_LINE_SHORT);
        System.out.printf("Difficulty level   %s %n",DIFFICULTY);
        System.out.printf("Time               %s %n",endTime);
        System.out.printf("Guessing tries     %s %n",guesses);
        System.out.printf("Final score        %s %n",finalScore);
        System.out.println(DIVIDING_LINE_SHORT);
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
        this.finalScore = (int) ( lvlCoff * (chances+1)/(pow(time,2))*10000.0);
    }

    public void finish(){
        endTime = time();
        calculateFinalScore(endTime);
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

    public void decrementChances() {
        this.chances--;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public int getMatchedPairs() {
        return matchedPairs;
    }

    public void incrementGuesses(){
        guesses++;
    }

    public int getGuesses(){
        return this.guesses;
    }

    public void incrementMatchedTiles() {
        matchedPairs++;
    }

    public static Record[] top10() {
        try {
        ArrayList<Record> records = new ArrayList<>();
        ArrayList<String> allScores ;

            allScores = FilesIO.loadFile(FilesIO.SCORE_FILE);

        System.out.println("TOP 10 SCORES:\n");
        for (String score: allScores)
        {
            records.add(new Record(score));
        }
        Collections.sort(records);
        Record [] top10 = new Record[10];
            int n = Math.min(records.size(), 10);
        for(int i = 0; i < n; i++) {
            top10[i] = records.get(i);
        }
        for(int i=1;i<=top10.length;i++){
            System.out.println(i+". "+ top10[i-1]);
        }
        System.out.println();
        return top10;
        }catch (IOException e){
            System.out.println("No top scores saved yet.\n");
        }
        return new Record[0];
    }

}

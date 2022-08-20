package main.score;

import main.FilesIO;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.pow;
import static main.CONST.*;
import static main.FilesIO.SCORE_FILE;

/**
 * This is a model class to hold game's score information, track it, preform needed operations.
 */
public class ScoreManager {
    private int chances;    //Number of Guess chances left.
    private int guesses; //Number of taken guesses
    private int finalScore; //final Score calculated with formula in calculateFinalScore()
    private int matchedPairs; //number of pairs matched
    private long endTime; //time of playing the game
    public final String DIFFICULTY;    //Difficulty level chosen by a user.
    private static long START_TIME; //time when the game start.

    /**
     * {@link ScoreManager} constructor. Keeps data related with score calculation.
     * @param difficulty
     * String, level of game difficulty.
     * @param chances
     * int number of chances allowed in a game.
     */
    public ScoreManager(String difficulty, int chances){
        guesses = 0;
        this.chances = chances;
        START_TIME = new Date().getTime();
        DIFFICULTY = difficulty;
        finalScore = 0;
        matchedPairs = 0;
    }

    /**
     * Prints data about a currently running game.
     *  @see ScoreManager#showFinalScore()
     */
    public void showScore(){
        System.out.println(DIVIDING_LINE_SHORT);
        System.out.printf("Difficulty level %s %n",DIFFICULTY);
        System.out.printf("Chances          %s %n",chances);
        System.out.printf("Time             %s %n",time());
        System.out.println(DIVIDING_LINE_SHORT);
    }

    /**
     * Print final score. Calls {@link ScoreManager#time()} and {@link ScoreManager#calculateFinalScore(long)} just before to
     * calculate achieved results.
     * @see ScoreManager#time()
     * @see ScoreManager#calculateFinalScore(long)
     * @see ScoreManager#showScore()
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
     * Calculates {@link ScoreManager#finalScore} of the game. Score depends on {@link ScoreManager#DIFFICULTY}, {@link ScoreManager#chances}
     * and time. The harder difficulty level and the more chances left and the shorter time, the higher score is.
     * @param time
     *  long time when the game ends.
     * @see ScoreManager#time()
     * @see ScoreManager#showScore()
     * @see ScoreManager#showFinalScore()
     */
    private void calculateFinalScore(long time){
        int lvlCoff = 1;
        if(DIFFICULTY.equals(HARD)) lvlCoff = 2;
        this.finalScore = (int) ( lvlCoff * (chances+1)/(pow(time,2))*10000.0);
    }

    /**
     * Ends game time recording, calculates score.
     * @see ScoreManager#calculateFinalScore(long)
     */
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

    /**
     * Takes top 10 of scores with the shortest time.
     * @return
     * Record[], list of top 10 scores
     * @see Record
     */
    public static ArrayList<Record> top10() {
        try {

            ArrayList<Record> records = new ArrayList<>();
            ArrayList<String> allScores ;

            allScores = FilesIO.loadFile(FilesIO.SCORE_FILE);

            System.out.println("TOP 10 SCORES:\n");
            for (String score: allScores)
            {
                Record r = new Record();
                r.loadRecord(score);
                records.add(r);
            }
            Collections.sort(records);
            ArrayList<Record> top10 = new ArrayList<>();
            int n = Math.min(records.size(), 10);
            topScoresHeadersRow();
            for(int i = 0; i < n; i++) {
                top10.add(records.get(i));
            }
            StringBuilder row = new StringBuilder();
            for(int i=1;i<=top10.size();i++){
                row.append(String.format(HEADERS_WIDTH[0],i));
                    for (int j = 1; j < HEADERS_WIDTH.length; j++) {
                        row.append(String.format(HEADERS_WIDTH[j], top10.get(i - 1).getVariablesList()[j - 1]));
                    }
                System.out.println(row);
                row = new StringBuilder();
            }
            System.out.println();
            File scoreFile = new File(SCORE_FILE);
            scoreFile.delete();
            for(Record record: top10){
                FilesIO.saveScoreRecord(record.getVariablesList());
            }
            return top10;
        }catch (IOException e){
            System.out.println("No top scores saved yet.\n");
        }
        return new ArrayList<>();
    }

    public static void topScoresHeadersRow(){

        String[] HEADERS=new String[]{
                "Pos",
                "Name",
                "Date",
                "Time",
                "Tries"};

        StringBuilder headers = new StringBuilder();
        for (int i=0;i<HEADERS.length;i++) {
            headers.append(String.format(HEADERS_WIDTH[i],HEADERS[i]));
        }
        System.out.println(headers);
    }

    public int getChances() {
        return chances;
    }

    public void decrementChances() {
        this.chances--;
    }

    public int getMatchedPairs() {
        return matchedPairs;
    }

    public void incrementMatchedTiles() {
        matchedPairs++;
    }

    public int getGuesses(){
        return this.guesses;
    }

    public void incrementGuesses(){
        guesses++;
    }

    public long getEndTime() {
        return endTime;
    }

    public double getFinalScore() {
        return finalScore;
    }

}

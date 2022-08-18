package test;
import static main.CONST.DATE_FORMATTER;
import static main.FilesIO.saveScoreRecord;
import static org.junit.Assert.assertEquals;


import main.Score;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ScoreTest {

    Score score;
    @BeforeEach
    void setUp() {
        score = new Score("Easy",10);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i =0;i<10;i++){
            score.incrementMatchedTiles();
            score.decrementChances();
        }
        score.finish();
        score.showFinalScore();
    }

    @Test
    @DisplayName("final score calcualtion test")
    public void testCalculateFinalScore() {
        assertEquals(score.getFinalScore(),2500.0,0);
    }

    @Test
    public void top10Test() throws IOException {

        //wygeneruj 10 rekordów
        //zapisz 10 rekordów do pliku
        //uruchom top10()
        // sprawdz czy kazda linijka jest taka sama
        int n = 15;
        System.out.println("Randomized score records:");
        for(int i=0;i<n;i++){
            int randNum = (int)(Math.random()*100);
            int randGuesses = (int)(Math.random()*10);
            String[] scoreRecord = new String[]{
                    "User"+randNum,
                    DATE_FORMATTER.format(new Date(ThreadLocalRandom.current().nextInt() * 1000L)),
                    Long.toString(randNum),
                    Integer.toString(randGuesses)
            };
            System.out.println(Arrays.toString(scoreRecord));
            saveScoreRecord(scoreRecord);
        }
        main.Record[] sortedRecords = Score.top10();
        for(int i=1;i<10;i++){
            if(sortedRecords[i-1].getGuessingTime()<=sortedRecords[i].getGuessingTime()){
                assert true;
            }else{
                assert false;
            }
        }
    }
}

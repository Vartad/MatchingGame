package test;
import static org.junit.Assert.assertEquals;

import main.Score;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.concurrent.TimeUnit;

public class ScoreTest {

    Score score;

    @Test
    @DisplayName("final score calcualtion test")
    public void testCalculateFinalScore() {
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
        assertEquals(score.getFinalScore(),2500.0,0);
    }
}

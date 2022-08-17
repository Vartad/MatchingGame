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
        score = new Score("Easy");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        score.incrementChances();
        score.incrementChances();
        score.showFinalScore();
        assertEquals(score.getFinalScore(),50.0,0);
    }
}

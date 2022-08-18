package test;

import main.FilesIO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import static main.FilesIO.saveScoreRecord;
import static org.junit.Assert.assertTrue;
public class SaveScoreTest {

    @Test
    @DisplayName("save Score")
    public void testSaveScore() throws IOException {
        String TEST_SCORE_FILE = FilesIO.SCORE_FILE;
        Files.deleteIfExists(Path.of(TEST_SCORE_FILE));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String[] scoreRecord = new String[]{
                "User",
                formatter.format(new Date()),
                Long.toString(900),
                Integer.toString(15)
        };
        saveScoreRecord(scoreRecord);
        Assertions.assertAll(
                ()-> assertTrue(Files.exists(Path.of(FilesIO.SCORE_FILE)))
        );

    }
}
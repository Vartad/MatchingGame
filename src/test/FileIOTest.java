package test;

import main.FilesIO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static main.CONST.DATE_FORMATTER;
import static main.CONST.TEST_FILE;
import static main.FilesIO.SCORE_FILE;
import static main.FilesIO.saveScoreRecord;

public class FileIOTest {
    
    @BeforeEach
    void setUp() {
        File scoreFile = new File(SCORE_FILE);
        scoreFile.delete();
        File testFile = new File(TEST_FILE);
        testFile.delete();
    }

    @Test
    @DisplayName("load File")
    public void LoadFile() throws IOException {
        FilesIO.writeToFile(TEST_FILE,"testFileContent");
        ArrayList<String> f = FilesIO.loadFile("testFile.txt");
        Assertions.assertEquals("testFileContent", f.get(0));
    }

    @Test
    @DisplayName("save Score")
    public void testSaveScore() throws IOException {
        Files.deleteIfExists(Path.of(SCORE_FILE));
        String[] scoreRecord = new String[]{
                "User",
                DATE_FORMATTER.format(new Date()),
                Long.toString(900),
                Integer.toString(15)
        };
        saveScoreRecord(scoreRecord);
        Assertions.assertTrue(Files.exists(Path.of(SCORE_FILE)));
        ArrayList<String> savedData = FilesIO.loadFile(FilesIO.SCORE_FILE);
        String correctlySavedUser = "User|"+DATE_FORMATTER.format(new Date())+"|900|15";
        String correctlySavedUser1 = "User1|"+DATE_FORMATTER.format(new Date())+"|50|10";
        Assertions.assertEquals(savedData.get(0), correctlySavedUser);
        String[] scoreRecord1 = new String[]{
                "User1",
                DATE_FORMATTER.format(new Date()),
                Long.toString(50),
                Integer.toString(10)
        };
        saveScoreRecord(scoreRecord1);
        ArrayList<String> savedData1 = FilesIO.loadFile(FilesIO.SCORE_FILE);
        if(savedData1.size()==2){
            Assertions.assertAll(
                    ()-> Assertions.assertEquals(savedData1.get(0), correctlySavedUser),
                    ()-> Assertions.assertEquals(savedData1.get(1), correctlySavedUser1)
            );
        }
    }
}

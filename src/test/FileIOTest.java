package test;

import main.FilesIO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static main.CONST.DATE_FORMATTER;
import static main.FilesIO.saveScoreRecord;
import static org.junit.Assert.*;

public class FileIOTest {

    @Test
    @DisplayName("load File")
    public void LoadFile() throws IOException {
        ArrayList<String> f = FilesIO.loadFile("testFile.txt");
        assertEquals("testFileContent", f.get(0));
    }

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
        assertTrue(Files.exists(Path.of(FilesIO.SCORE_FILE)));
        ArrayList<String> savedData = FilesIO.loadFile(FilesIO.SCORE_FILE);
        String correctlySavedUser = "User|"+DATE_FORMATTER.format(new Date())+"|900|15";
        String correctlySavedUser1 = "User1|"+DATE_FORMATTER.format(new Date())+"|50|10";
        assertEquals(savedData.get(0),correctlySavedUser);
        String[] scoreRecord1 = new String[]{
                "User1",
                formatter.format(new Date()),
                Long.toString(50),
                Integer.toString(10)
        };
        saveScoreRecord(scoreRecord1);
        ArrayList<String> savedData1 = FilesIO.loadFile(FilesIO.SCORE_FILE);
        if(savedData1.size()==2){
            Assertions.assertAll(
                    ()-> assertEquals(savedData1.get(0),correctlySavedUser),
                    ()-> assertEquals(savedData1.get(1),correctlySavedUser1)
            );
        }
    }
}

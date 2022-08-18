package test;

import main.FilesIO;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LoadFileTest {

    @Test
    @DisplayName("load File")
    public void LoadFile() throws IOException {
        File f = FilesIO.loadFile("scores.txt");
        assertTrue(f.exists());
    }

}

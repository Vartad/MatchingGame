package main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *  This is a model class to hold all methods and data to manipulate files.
 */
public class FilesIO {
    private static final ArrayList<String> WORDS = new ArrayList<String>(); //words loaded from a file.

    /**
     * Loads data from a "txt" file and saves it as a WORDS variable
     * @param fileName
     */
    public static void loadFile(String fileName) {
        InputStream stream = FilesIO.class.getClassLoader().getResourceAsStream(fileName);
        if( stream != null ){
            InputStreamReader streamReader =
                    new InputStreamReader(stream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            try {
                while (reader.readLine() != null) {
                    WORDS.add(reader.readLine());
                }
            }catch (IOException e){
                System.out.println("File doesn't exist");
            }

        }
    }

    /**
     * Gets WORDS loaded from the file.
     * @return
     */
    public static ArrayList<String> getWords() {
        return WORDS;
    }
}

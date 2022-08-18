package main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *  This is a model class to hold all methods and data to manipulate files.
 */
public class FilesIO {

    public static final String SCORE_FILE = "scores.txt";
    private static final String COL_WIDTH = "14";


    /**
     * Loads data from a "txt" file and saves it as a WORDS variable
     * @param fileName name of a file to load
     */
    public static ArrayList<String> loadFile(String fileName) {
        ArrayList<String> WORDS = new ArrayList<>(); //words loaded from a file.
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
        return WORDS;
    }

    public static void writeToFile(String fileName, String text) {
        try {
            FileWriter myWriter = new FileWriter(fileName,true);
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully saved.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void saveScoreRecord(String[] scoreRecord){
        StringBuilder line = new StringBuilder();
        line.append(scoreRecord[0]);
        for (int i=1;i<scoreRecord.length;i++) {
            line.append(String.format("%"+COL_WIDTH+"s", scoreRecord[i]));
        }
        line.append("\n");
        writeToFile(SCORE_FILE, String.valueOf(line));
    }
}

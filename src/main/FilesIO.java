package main;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  This is a model class to hold all methods and data to manipulate files.
 */
public class FilesIO {

    public static final String SCORE_FILE = "scores.txt";

    /**
     * Loads data from a "txt" file and saves it as a WORDS variable
     * @param fileName name of a file to load
     */
    public static ArrayList<String> loadResourcesFile(String fileName) {
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

    public static ArrayList<String> loadFile(String fileName) throws IOException {
        String path = new File(".").getCanonicalPath();
        File file = new File(path+"/"+fileName);
        Scanner sc = new Scanner(file);
        ArrayList<String> filesData= new ArrayList<>();
        while (sc.hasNextLine())
        {
            filesData.add(sc.nextLine());
        }
        sc.close();
        return filesData;

    }

    public static void writeToFile(String fileName, String text) {
        try {
            FileWriter myWriter = new FileWriter(fileName,true);
            myWriter.write(text);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the score data to a file
     * @param scoreRecord
     * String data of score record
     */
    public static void saveScoreRecord(String[] scoreRecord){
        StringBuilder line = new StringBuilder();
        line.append(scoreRecord[0]);
        for (int i=1;i<scoreRecord.length;i++) {
            line.append(String.format("|%s", scoreRecord[i]));
        }
        line.append("\n");
        writeToFile(SCORE_FILE, String.valueOf(line));
    }
}

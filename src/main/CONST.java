package main;

import java.text.SimpleDateFormat;

/**
 * class contains all global constants used in program
 */
public class CONST {
    public static final String EASY = "easy";
    public static final String HARD = "hard";
    public static final String DIVIDING_LINE_SHORT = "-------------------------"; //To separate score from board.
    public static final String DIVIDING_LINE_LONG = "--------------------------------------------------";
    //To separate data between boards prints.
    public static final String YES = "yes";
    public static final String NO = "no";
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    public static final String TEST_FILE = "testFile.txt";
    public static final         String[] HEADERS_WIDTH =new String[]{
            "|%-4s", //pos
            "|%-17s", //Name
            "|%-11s", //Date
            "|%-5s", //Time
            "|%-5s|" //Tries
    };
    public static final int ALLOWED_NAME_LENGTH = 15;
}

package main;

import java.util.Scanner;

/**
 * Memory game
 * @author Mateusz Gasior
 * 17.08.2022
 */

public class Main {
    /**
     * Main class of whole project, here the game is launched.
     * The memory game is about to match all words pairs. Number of pairs (4 or 8) depend on the difficulty level
     * (Easy or Hard). Also, a guess chances number (10 or 15) change with difficulty level.
     * Words are loaded from text file.
     *
     */
    public static void main(String[] args) {
        FilesIO.loadFile("Words.txt");
        Scanner scanner = new Scanner(System.in);
        String input = "yes";
        while(input.equals("yes")) {
            System.out.println("choose difficulty level Easy or Hard.");
            input = scanner.nextLine();
            Game game = new Game(input, FilesIO.getWords());
            game.run();
            System.out.println("Would you like to play again?");
            input = scanner.nextLine();
        }
    }
}
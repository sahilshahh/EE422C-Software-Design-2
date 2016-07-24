package bonus;

import java.util.ArrayList;
import java.util.Scanner;

class Game {

    private int numberOfGuesses;
    private int maxGuesses;
    private ArrayList<String> guesses;
    private ArrayList<String> results;
    private Scanner s;

    // Constructor
    Game() {
        numberOfGuesses = 0;
        maxGuesses = 16;
        guesses = new ArrayList<String>();
        results = new ArrayList<String>();
        displayIntro();
        s = new Scanner(System.in);
    }


    // Controls all console interactions of the game
    boolean runGame() {
        promptToStart();
        Board game = new Board(promptNumPegs(), promptNumColors());
        maxGuesses = promptNumGuesses();


        System.out.print("\nGenerating secret code...");

        // Loop for the multiple guesses
        while (numberOfGuesses < maxGuesses) {
            System.out.println("\n\nYou have " + (maxGuesses - numberOfGuesses) + (numberOfGuesses == (maxGuesses -
                    1) ? " guess left" : " guesses left"));
            System.out.print("What is your " + (numberOfGuesses > 0 ? "next" : "first") + " guess?\nType in " + game
                    .getNumPegs() + " characters for your guess and press enter.\nEnter " +
                    "Guess: ");
            String guess = s.next();

            System.out.println("");

            if (game.validGuess(guess)) {
                String output = game.checkGuess(guess);
                guesses.add(guess);
                results.add(output);
                numberOfGuesses++;
                System.out.print(guesses.get(numberOfGuesses - 1));

                System.out.print(output);
                if (game.getWinFlag()) {
                    break;
                }
            } else {
                if (guess.toLowerCase().equals("history")) {
                    //print out elements in guesses
                    for (int i = 0; i < guesses.size(); i++)
                        System.out.println((i + 1) + ") " + guesses.get(i) + results.get(i));

                } else
                    System.out.println("Invalid Guess. Please try again!");
            }
        }

        if (!game.getWinFlag())
            System.out.println("\n\nSorry! You did not win, The secret code was: " + game.getCode());

        return promptToPlayAgain();
    }


    // Displays beginning intro, including instructions
    private void displayIntro() {
        System.out.println("Welcome to Mastermind. Here are the rules.\n\nThis is a text version of the classic board" +
                " game Mastermind.\nThe computer will think of a secret code. For a standard game, the code consists of 4 colored pegs" +
                ".\nThe " +
                "pegs may be one of six colors: blue, green, orange, purple, red, or yellow. A color may appear " +
                "more\n" +
                "than once in the code. You try to guess what colored pegs are in the code and what order they are\n" +
                "in. After you make a valid guess the result (feedback) will be displayed. The result consists of a\n" +
                "black peg for each peg you have guessed exactly correct (color and position) in your guess.\nFor " +
                "each" +
                " peg in the guess that is the correct color, but is out of position, you get a white peg.\nFor each" +
                " peg, which is fully incorrect, you get no feedback.\n\nOnly the first letter of the color is" +
                " displayed. B for Blue, R for Red, and so forth.\nWhen entering guesses you only need to enter the " +
                "first character of each color as a capital letter.\n\nYou can customize a game to have 4, 5, or 6 " +
                "pegs, and add Maroon (M) and Cyan (C), and even the number of guesses, from 8 - 16!\n" +
                "If you do not guess your code in the chosen number of guesses, you lose the game.\n");
    }

    // displays starting prompt and scans user input
    private void promptToStart() {
        boolean validSelection = false;
        System.out.print("Are you ready to play? (Y/N): ");
        while (!validSelection) {
            char selection = s.next().charAt(0);
            if (selection == 'Y' || selection == 'y')
                validSelection = true;
            else if (selection == 'N' || selection == 'n') {
                System.out.println("Thank you for playing!");
                s.close();
                System.exit(0);
            } else {
                System.out.println("Invalid input, please enter a Y or N");
            }
        }
    }

    // prompt user input for num of Pegs
    private int promptNumPegs() {
        while (true) {
            System.out.print("How many pegs would you like to play with? (4/5/6): ");
            char selection = s.next().charAt(0);
            if (selection == '4' || selection == '5' || selection == '6') {
                return Character.getNumericValue(selection);
            } else {
                System.out.println("Invalid input, please enter a 4, 5 or 6\n");
            }
        }

    }
    // prompt user input for num of colors
    private int promptNumColors() {
        System.out.println("Next, choose which sets of colors you would like to play with:\n" +
                "6 (Standard): Blue (B), Green (G), Orange (O), Purple (P), Red (R), and Yellow (Y) \n" +
                "7 : Standard + Maroon (M)\n" +
                "8 : Standard + Maroon (M) + Cyan (C)\n");
        while (true) {
            System.out.print("How many colors would you like to play with? (6/7/8): ");
            char selection = s.next().charAt(0);
            if (selection == '6' || selection == '7' || selection == '8') {
                return Character.getNumericValue(selection);
            } else {
                System.out.println("Invalid input, please enter a 6, 7 or 8\n");
            }
        }
    }

    // prompt user input for num of Guesses
    private int promptNumGuesses() {

        while (true) {
            System.out.print("How many guesses would you like to play with? (8 - 16): ");
            String selection = s.next();
            if (Integer.valueOf(selection) >= 8 && Integer.valueOf(selection) <= 16) {
                return Integer.valueOf(selection);
            } else {
                System.out.println("Invalid input, please enter a number between 8 and 16\n");
            }
        }

    }

    // displays continuation prompt and scans user input
    private boolean promptToPlayAgain() {
        System.out.print("Would you like to play again? (Y/N): ");
        while (true) {
            char selection = s.next().charAt(0);
            if (selection == 'Y' || selection == 'y')
                return true;
            else if (selection == 'N' || selection == 'n') {
                System.out.println("Thank you for playing!");
                s.close();
                return false;
            } else {
                System.out.println("Invalid input, please enter a Y or N");
            }
        }
    }
}
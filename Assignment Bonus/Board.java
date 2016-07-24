package bonus;

import java.util.ArrayList;
import java.util.Random;

class Board {

    private String[] code;
    private ArrayList<String> colors;
    private boolean winFlag;
    private int numPegs;

    Board(int pegs, int numColors) {
        numPegs = pegs;
        colors = new ArrayList<String>();
        // Note that the switch cascading is intentional
        switch (numColors) {
            case 8:
                colors.add("C");
            case 7:
                colors.add("M");
            default:
                colors.add("P");
                colors.add("G");
                colors.add("O");
                colors.add("B");
                colors.add("R");
                colors.add("Y");
        }


        code = new String[numPegs];
        winFlag = false;
        code = createCode();
    }

    //creates the random code
    private String[] createCode() {
        String[] code = new String[numPegs];
        Random r = new Random();
        int number;
        for (int i = 0; i < numPegs; i++) {
            number = r.nextInt(5);
            code[i] = colors.get(number);
        }
        return code;
    }

    //checks whether the input guess is valid
    boolean validGuess(String input) {
        char[] array = input.toCharArray();
        if (array.length != numPegs) {
            return false;
        }
        String comparisonList;
        switch (colors.size()) {
            case 8:
                comparisonList = "BGOPRYMC";
                break;
            case 7:
                comparisonList = "BGOPRYM";
                break;
            default:
                comparisonList = "BGOPRY";
        }
        for (int i = 0; i < numPegs; i++) {
            if (comparisonList.indexOf(array[i]) == -1) {
                return false;
            }
        }
        return true;
    }

    //assigns pegs
    String checkGuess(String input) {
        int black = 0;
        int white = 0;
        String[] array = input.split("");

        boolean[] blackPeg = new boolean[code.length];
        boolean[] whitePeg = new boolean[array.length];

        for (int i = 0; i < numPegs; i++) {
            if (array[i].equals(code[i])) {
                black = black + 1;
                blackPeg[i] = whitePeg[i] = true;
            }
        }

        for (int m = 0; m < numPegs; m++) {
            for (int n = 0; n < numPegs; n++) {
                if (!blackPeg[m] && !whitePeg[n] && code[m].equals(array[n])) {
                    white++;
                    blackPeg[m] = whitePeg[n] = true;
                    break;
                }
            }
        }

        return formatOutput(black, white);
    }

    //formats the string to be returned
    private String formatOutput(int black, int white) {
        if (black == 0 && white == 0) {
            return "-> Result: No pegs";
        } else if (black > 0 && white == 0) {
            if (black == numPegs) {
                winFlag = true;
                return "-> Result: " + numPegs + " black pegs!! You win !!\n";
            } else {
                return "-> Result: " + black + (black <= 1 ? " black peg" : " black pegs");
            }

        } else if (white > 0 && black == 0) {
            return "-> Result: " + white + (white <= 1 ? " white peg" : " white pegs");
        } else {
            return "-> Result: " + black + (black <= 1 ? " black peg and " : " black pegs and ") + white + (white <= 1 ? " white peg" : " white pegs");
        }
    }

    //takes the random code and makes it into a string
    String getCode() {
        String codeString = "";
        for (int i = 0; i < numPegs; i++) {
            codeString = codeString + code[i];
        }
        return codeString;
    }

    boolean getWinFlag() {
        return winFlag;
    }

    int getNumPegs() {
        return numPegs;
    }
}

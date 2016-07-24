package bonus;

public class Driver {

    public static void main(String[] args) {
        Game masterMind;

        // Create a game object as long as the user requests to play again
        do {
            masterMind = new Game();
        }
        while (masterMind.runGame());
    }
}

/**
 * This class represents the player of the game.
 */

public class Player {
    
    private int steps;

    /**
     * The constructor of Player initializes the integer steps to 0.
     */
    public Player() {
        steps=0;
    }

    /**
     * This method increases the number of steps by one.
     */
    public void increaseSteps() {
        steps++;
    }

    public int getSteps() {
        return steps;
    }
}

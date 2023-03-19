/**
 * This is the abstract class Player which is the parent of the subclasses HumanPlayer and ComputerPlayer
 */

public abstract class Player {
    
    private String username;
    private int steps;
    private int cardsTaken;

    public Player(String username) {
        steps=0;
        cardsTaken=0;
        this.username=username;
    }


    public void increaseSteps() {
        steps++;
    }


    public void increaseCardsTaken() {
        cardsTaken+=2;
    }


    public int getSteps() { 
        return steps; 
    }


    public int getCardsTaken() { 
        return cardsTaken; 
    }


    public abstract int choseCard();

    public abstract int choseCardDuel(int countryName);

    public abstract void saveCard(int countryName,int coordinates);

    public abstract void removeCards(Card card);
}

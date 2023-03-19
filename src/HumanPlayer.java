/**
 * This class is for the human players.
 */

public class HumanPlayer extends Player {
    
    public HumanPlayer(String username) {
        super(username);
    }

    public int choseCard() { 
        return 1; 
    }

    public int choseCardDuel(int countryName) {
        return 1;
    }

    public void saveCard(int countryName,int coordinates){}
    public void removeCards(Card card){}
}

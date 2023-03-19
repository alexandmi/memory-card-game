/**
 *  This class represents a card that is displayed on the memory table.
 */


public class Card {
    
    private char letter;
    private boolean exist;
    private boolean open;

    /**
     * The constructor of Card initializes the char letter,the boolean exist to true and the
     * boolean open to false,because initially all the cards face down,closed.
     *
     * @param letter This parameter carries the character the card will have.
     */
    public Card(char letter) {
        this.letter=letter;
        exist=true;
        open=false;
    }


    public char getLetter() {
        return letter;
    }


    public boolean getExist() {
        return exist;
    }


    public boolean getOpen() {
        return open;
    }


    public void setLetter(char letter) {
        this.letter=letter;
    }


    public void setExist(boolean exist) {
        this.exist = exist;
    }


    public void setOpen(boolean open) {
        this.open = open;
    }
}
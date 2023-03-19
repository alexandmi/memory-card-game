/**
 * This class represents the table of cards in which the game takes place,which uses them for the game's purposes.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Table {
    
    final int ASCII_A=65;
    private int lines;
    private int rows;
    private int numberOfChosenCards;
    private int numberOfUniqueCards;
    private ArrayList<Card> deck;
    private HashMap<Integer,Card> memoryTable;

    /**
     * The constructor of Table uses the method initializeGameStyle to initialize the game's characteristics.It creates
     * the deck and the memoryTable,and then uses the methods fillDeck,shuffleDeck and distributeDeck to create the
     * deck,shuffle it and put its' cards to the memoryTable respectively.
     *
     * @param gameType This parameter carries the String with the name of the game type and it assigns it to the
     *                  method initializeGameStyle.
     */
    public Table(String gameType) {
        
        initializeGameStyle(gameType);
        deck=new ArrayList<Card>();
        memoryTable=new HashMap<Integer, Card>();
        fillDeck();
        shuffleDeck();
        distributeDeck();
    }


    /**
     * This method initializes the integers lines,rows,numberOfChosenCards and numberOfUniqueCards depending on the
     * game style the user has chosen,basic,double or triple.
     *
     * @param gameStyle This parameter carries the String with the name of the game type and it compares it with the
     *                  Strings "basic" and "double".It does not get compared with the String "triple" because the
     *                  parameter is strictly one of the above three and so there is no need for a third comparison.
     */
    private void initializeGameStyle(String gameStyle) {
        
        if(gameStyle.equals("basic")) {
            lines=4;
            rows=6;
            numberOfChosenCards=2;
            numberOfUniqueCards=12;
        }
        else if(gameStyle.equals("double")) {
            lines=6;
            rows=8;
            numberOfChosenCards=2;
            numberOfUniqueCards=24;
        }
        else {
            lines=6;
            rows=6;
            numberOfChosenCards=3;
            numberOfUniqueCards=12;
        }
    }


    /**
     * This method fills the ArrayList deck with cards.
     * It inserts capital letters of the latin alphabet from ASCII,into a temporary card,which is then added to the
     * deck.Each letter is added numberOfChosenCards times in the deck.The first letter is A and we add in
     * total numberOfUniqueCards letters.
     */
    private void fillDeck() {
        
        Card temp;
        for(int i=ASCII_A; i<ASCII_A+numberOfUniqueCards; i++)
            for(int j=0; j<numberOfChosenCards; j++) {
                temp=new Card((char)i);
                deck.add(temp);
            }
    }


    /**
     * This method shuffles the cards of the deck,using a java method called shuffle.
     */
    private void shuffleDeck() {
        Collections.shuffle(deck);
    }


    /**
     * This method takes the cards from the shuffled deck and puts them in the HashMap memoryTable.
     * Initially each card is assigned into an another temporary card,and then the latter is inserted into the
     * memoryTable.The HashMap key of each card is it's table coordinates.
     */
    private void distributeDeck() {
        Card temp;
        int index=0;

        for(int i=1; i<lines+1; i++)
            for(int j=1; j<rows+1; j++) {
                temp=deck.get(index);
                memoryTable.put(10*i+j,temp);
                index++;
            }
    }


    /**
     * This method prints the memory table in the command line.
     * It prints the coordinates of the rows at the top of the table.At the left side of the table it prints the
     * coordinates of the lines.It prints the cards for each line,first printing their top,
     * then their torso,then the part with the letter and eventually their bottom.For each part of a card it checks
     * whether the card exists or not,and if it doesn't exist it is not printed.At the letter part of each card it also
     * checks if the card is closed,and if so it prints # instead of the letter.
     */
    public void showTable() {
       
        Card temp;
        System.out.printf("  ");
        for(int j=1; j<rows+1; j++) {
            System.out.printf("   "+j+"    ");
        }
        System.out.printf("\n");

        for(int i=1; i<lines+1; i++) {
            System.out.printf("  ");
            for(int j=1; j<rows+1; j++) {
                temp=memoryTable.get(10*i+j);
                if(temp.getExist())
                    System.out.printf(" _____  ");
                else
                    System.out.printf("        ");
            }

            System.out.printf("\n  ");

            for(int j=1; j<rows+1; j++) {
                temp=memoryTable.get(10*i+j);
                if(temp.getExist())
                    System.out.printf("|     | ");
                else
                    System.out.printf("        ");
            }

            System.out.printf("\n"+i+" ");

            for(int j=1; j<rows+1; j++) {
                temp=memoryTable.get(10*i+j);
                if(temp.getExist()) {
                    if(temp.getOpen())
                        System.out.printf("|  %c  | ",temp.getLetter());
                    else
                        System.out.printf("|  #  | ");
                }
                else
                    System.out.printf("        ");
            }

            System.out.printf("\n  ");

            for(int j=1; j<rows+1; j++) {
                temp=memoryTable.get(10*i+j);
                if(temp.getExist())
                    System.out.printf("|_____| ");
                else
                    System.out.printf("        ");
            }
            System.out.printf("\n");
        }
        System.out.println("\n");
    }


    /**
     * This method compares the letters of two cards and finds if they are equal or not.
     * @param coordinates1 This parameter carries the coordinate of the first card which will be compared.
     * @param coordinates2 This parameter carries the coordinate of the second card which will be compared.
     * @return The method returns true if the letters are the same and false if they are different.
     */
    public boolean compareCards(int coordinates1,int coordinates2) {
        
        char character1=memoryTable.get(coordinates1).getLetter();
        char character2=memoryTable.get(coordinates2).getLetter();
        if(character1==character2)
            return true;
        else return false;
    }


    /**
     * This method compares the letters of three cards and finds if they are equal or not.
     * @param coordinates1 This parameter carries the coordinate of the first card which will be compared.
     * @param coordinates2 This parameter carries the coordinate of the second card which will be compared.
     * @param coordinates3 This parameter carries the coordinate of the third card which will be compared.
     * @return The method returns true if the letters are the same and false if they are different.
     */
    public boolean compareCards(int coordinates1,int coordinates2,int coordinates3) {
        char character1=memoryTable.get(coordinates1).getLetter();
        char character2=memoryTable.get(coordinates2).getLetter();
        char character3=memoryTable.get(coordinates3).getLetter();

        if(character1==character2 && character2==character3)
            return true;
        else return false;
    }


    /**
     * This method checks if the memory table is empty.It checks all the cards one by one and if it finds at least one
     * that exists then the boolean empty is set to false.
     * @return The method returns the boolean empty.If the table is empty the boolean is true.If it is not the
     * boolean is false.
     */
    public boolean isTableEmpty() {
        
        boolean empty=false;
        for(int i=1;i<lines+1;i++) {
            for (int j = 1; j < rows + 1; j++) {
                if (memoryTable.get(10 * i + j).getExist()) {
                    empty = false;
                    break;
                }
                else
                    empty = true;
            }
            if (!empty)
                break;
        }
        return empty;
    }


    /**
     * This method sets the boolean open of a card to true.
     * @param coordinates This parameter carries the coordinates of the card which will be opened.
     */
    public void openCard(int coordinates) {
        memoryTable.get(coordinates).setOpen(true);
    }


    /**
     * This method sets the boolean open of a card to false.
     * @param coordinates This parameter carries the coordinates of the card which will be closed.
     */
    public void closeCard(int coordinates) {
        memoryTable.get(coordinates).setOpen(false);
    }


    /**
     * This method sets the boolean exist of a card to false.
     * @param coordinates This parameter carries the coordinates of the card which will be removed.
     */
    public void removeCard(int coordinates) {
        memoryTable.get(coordinates).setExist(false);
    }


    /**
     * This method returns whether the card chosen is open or not.
     * @param coordinates This parameter carries the coordinates of the card we want to see if it's open or not.
     * @return This method returns the method getOpen of the class Card.
     */
    public boolean getOpen(int coordinates) {
        return memoryTable.get(coordinates).getOpen();
    }


    /**
     * This method returns whether the card chosen exists or not.
     * @param coordinates This parameter carries the coordinates of the card we want to see if it exists or not.
     * @return This method returns the method getExist of the class Card.
     */
    public boolean getExist(int coordinates) {
        return memoryTable.get(coordinates).getExist();
    }


    public int getNumberOfChosenCards() {
        return numberOfChosenCards;
    }

    public int getLines() {
        return lines;
    }

    public int getRows() {
        return rows;
    }
}

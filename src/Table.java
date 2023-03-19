/**
 * This class represents the memory table of the game.The cards are saved in a HashMap.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;


public class Table {
    
    public int gameType;
    private int lines;
    private int rows;
    private int numberOfChosenCards;
    private int numberOfUniqueCards;
    private ArrayList<Card> deck;
    private ArrayList<Card> deck2;
    private HashMap<Integer,Card> memoryTable;
    private HashMap<Integer,Card> memoryTable2;

    private Card savedCard1=null;
    private Card savedCard2=null;

    private HashMap<Integer,Card> coordinatesOfCards;
    private HashMap<Integer,Card> coordinatesOfCards2;

    private ActionListener handler;


    public Table(int gameType,ActionListener handler) {
        
        initializeGameType(gameType);
        this.handler=handler;
        deck=new ArrayList<>();
        memoryTable=new HashMap<>();

        fillDeck(deck);
        shuffleDeck(deck);
        distributeDeck(deck,memoryTable);

        coordinatesOfCards=new HashMap<>();
        memoryTable.forEach(coordinatesOfCards::putIfAbsent);

        if(gameType==4) {
            deck2=new ArrayList<>();
            memoryTable2=new HashMap<>();
            fillDeck(deck2);
            shuffleDeck(deck2);
            distributeDeck(deck2,memoryTable2);
            coordinatesOfCards2=new HashMap<>();
            memoryTable2.forEach(coordinatesOfCards2::putIfAbsent);
        }
    }


    /**
     * This method initializes the characteristics of the table.
     * @param gameType
     */
    private void initializeGameType(int gameType) {
        if(gameType==1) {
            lines=4;
            rows=6;
            numberOfChosenCards=2;
            numberOfUniqueCards=12;
        }
        else if(gameType==2) {
            lines=6;
            rows=8;
            numberOfChosenCards=2;
            numberOfUniqueCards=24;
        }
        else if(gameType==3) {
            lines=6;
            rows=6;
            numberOfChosenCards=3;
            numberOfUniqueCards=12;
        }
        else {
            lines=6;
            rows=4;
            numberOfChosenCards=2;
            numberOfUniqueCards=24;
        }
    }


    public void fillDeck(ArrayList<Card> deck) {
        
        Card temp;
        Image countryImage;
        Image worldImage;
        Image blueImage;
        ImageIcon countryIcon;
        ImageIcon worldIcon;
        ImageIcon blueIcon;
        int countryName;

        try {
            worldImage = ImageIO.read(getClass().getResource("icons/24.png"));
            worldIcon = new ImageIcon(worldImage);
            blueImage = ImageIO.read(getClass().getResource("icons/25.png"));
            blueIcon = new ImageIcon(blueImage);

            for (int i = 0; i < numberOfUniqueCards; i++) {
                countryName=i;
                String number = Integer.toString(i);
                countryImage = ImageIO.read(getClass().getResource("icons/" + number + ".png"));

                for (int j = 0; j < numberOfChosenCards; j++) {
                    countryIcon = new ImageIcon(countryImage);
                    temp = new Card(countryIcon, worldIcon,blueIcon,countryName);
                    deck.add(temp);
                }
            }
        }
        catch (Exception ex) { 
            System.out.println(ex); 
        }
    }


    private void shuffleDeck(ArrayList<Card> deck) {
        Collections.shuffle(deck);
    }


    private void distributeDeck(ArrayList<Card> deck,HashMap<Integer,Card> memoryTable) {
        Card temp;
        int index=0;

        for(int i=1; i<lines+1; i++)
            for(int j=1; j<rows+1; j++) {
                temp=deck.get(index);
                temp.setCoordinates(10*i+j);
                temp.addActionListener(handler);
                temp.setActionCommand("Card");
                memoryTable.put(10*i+j,temp);
                index++;
            }
    }


    /**
     * This method is responsible for comparing the cards that opened and removing tem if they are the same,closing them if they
     * are not.
     * @param card
     * @return
     */
    public boolean closeOrRemoveCards(Card card) {
        
        boolean removed;
        if(numberOfChosenCards==2) {
            if(savedCard1.getCountryName()==card.getCountryName()) {
                savedCard1.removeCard();
                savedCard1.removeActionListener(handler);
                savedCard1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                card.removeCard();
                card.removeActionListener(handler);
                card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                coordinatesOfCards.remove(card.getCoordinates());
                if(gameType!=4) coordinatesOfCards.remove(savedCard1.getCoordinates());
                removed=true;
            }
            else {
                savedCard1.closeCard();
                card.closeCard();
                removed=false;
                savedCard1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            savedCard1=null;
        }
        else {
            if(savedCard1.getCountryName()==savedCard2.getCountryName() && savedCard2.getCountryName()==card.getCountryName()) {
                
                savedCard1.removeCard();
                savedCard1.removeActionListener(handler);
                savedCard1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                savedCard2.removeCard();
                savedCard2.removeActionListener(handler);
                savedCard2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                card.removeCard();
                card.removeActionListener(handler);
                card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                coordinatesOfCards.remove(card.getCoordinates());
                coordinatesOfCards.remove(savedCard1.getCoordinates());
                coordinatesOfCards.remove(savedCard2.getCoordinates());
                removed=true;
            }
            else {
                savedCard1.closeCard();
                savedCard2.closeCard();
                card.closeCard();
                removed=false;
            }

            savedCard1=null;
            savedCard2=null;
        }
        return removed;
    }


    public void saveChosenCard(Card card) {
        if(savedCard1==null) {
            savedCard1 = card;
        }
        else
            savedCard2=card;
    }


    /**
     * This method checks at the end of every turn if the table has been emptied.
     * @param aMemoryTable
     * @return
     */
    public boolean isTableEmpty(HashMap<Integer,Card> aMemoryTable) {
        boolean empty=false;
        for(int i=1; i<lines+1; i++) {
            for (int j = 1; j < rows + 1; j++) {
                if (aMemoryTable.get(10 * i + j).getExist()) {
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


    public int getLines() { 
        return lines; 
    }

    public int getRows() { 
        return rows; 
    }

    public int getNumberOfChosenCards() { 
        return numberOfChosenCards; 
    }

    public int getNumberOfUniqueCards() { 
        return numberOfUniqueCards; 
    }

    public Card getSavedCard1() { 
        return savedCard1; 
    }

    public HashMap<Integer, Card> getMemoryTable() { 
        return memoryTable; 
    }

    public HashMap<Integer, Card> getMemoryTable2() { 
        return memoryTable2;
    }

    public HashMap<Integer, Card> getCoordinatesOfCards() {
        return  coordinatesOfCards;
    }
}

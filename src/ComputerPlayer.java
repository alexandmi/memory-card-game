/**
 * This class controls the behaviour of the bots.
 */

import java.util.*;


public class ComputerPlayer extends Player {
    
    private ArrayList<ArrayList<Integer>> savedCards;
    private Table table;
    private int botType;
    private boolean kangarooSaveCard=true;
    private int numberOfCardToBeDrawn=0;
    private int lastCoordinates1=0;

    public ComputerPlayer(String username,Table table,int botType) {
        super(username);
        this.table=table;
        this.botType=botType;
        savedCards  = new ArrayList<>(table.getNumberOfUniqueCards());
        
        for(int i=0;i<table.getNumberOfUniqueCards();i++) {
            ArrayList<Integer> tempArrayList=new ArrayList<>();
            tempArrayList.add(0);
            savedCards.add(i,tempArrayList);
        }
    }


    /**
     * This method controls what card the bot will chose to open.First it searches through his savedCards ArrayList to find
     * pairs.If it doesn't then it choses randomly form the cards still on the table.
     * @return
     */
    public int choseCard() {
        int coordinates=0;
        numberOfCardToBeDrawn++;
        
        for(int i=0;i<table.getNumberOfUniqueCards();i++)
            if(savedCards.get(i).get(0)==1) {
                coordinates=savedCards.get(i).get(numberOfCardToBeDrawn);
                if(coordinates==lastCoordinates1) {
                    coordinates=savedCards.get(i).get(1);
                }
                if(numberOfCardToBeDrawn>=table.getNumberOfChosenCards()) {
                    numberOfCardToBeDrawn=0;
                    savedCards.get(i).set(0,0);
                    lastCoordinates1=0;
                }
                break;
            }

        if(coordinates==0) {
            Random r=new Random();
            do {
                coordinates = (int) table.getCoordinatesOfCards().keySet().toArray()[r.nextInt
                        (table.getCoordinatesOfCards().keySet().toArray().length)];
            } while (coordinates==lastCoordinates1);
            lastCoordinates1=coordinates;
            if(numberOfCardToBeDrawn>=table.getNumberOfChosenCards()) {
                numberOfCardToBeDrawn=0;
                lastCoordinates1=0;
            }
        }
        return coordinates;
    }


    /**
     * This method is a Duel version of the choseCard method.
     * @param countryName
     * @return
     */
    public int choseCardDuel(int countryName) {
        
        int coordinates=0;

        if(savedCards.get(countryName).get(0)==1)
            coordinates=savedCards.get(countryName).get(1);
        if(coordinates==0) {
            Random r=new Random();
            coordinates = (int) table.getCoordinatesOfCards().keySet().toArray()[r.nextInt
                    (table.getCoordinatesOfCards().keySet().toArray().length)];
        }
        return coordinates;
    }


    /**
     * This method is the 'memory' of the bot.Depending the type of bot(goldfish,kangaroo or elephant),it remembers the
     * cards that are opened and it saves them in the savedCards ArrayList.
     * @param countryName
     * @param coordinates
     */
    public void saveCard(int countryName,int coordinates/*ArrayList<ArrayList<Integer>> savecards*/) {
        
        if(botType==1 && savedCards.get(countryName).get(savedCards.get(countryName).size()-1)!=coordinates) {
            if(kangarooSaveCard)
                kangarooSaveCard=false;
            else
                kangarooSaveCard=true;
        }

        if(table.getNumberOfChosenCards()==3 && savedCards.get(countryName).size()>=3) {
            if(savedCards.get(countryName).get(savedCards.get(countryName).size()-2)==coordinates)
                return;
        }

        if(savedCards.get(countryName).size()<table.getNumberOfChosenCards()+1 &&
                savedCards.get(countryName).get(savedCards.get(countryName).size()-1)!=coordinates && botType!=0 && kangarooSaveCard) {
            savedCards.get(countryName).add(coordinates);
            if (savedCards.get(countryName).size() == table.getNumberOfChosenCards() + 1) {
                savedCards.get(countryName).set(0, 1);
            }
        }
    }


    public void removeCards(Card card) {
        savedCards.get(card.getCountryName()).set(0,0);
    }
}
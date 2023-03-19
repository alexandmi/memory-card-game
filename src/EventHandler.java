/**
 * This class is responsible for all the actions that take place in the game.It dictates what the buttons do,and controls
 * the main flow of the game,which is the opening and closing of the memory cards from the players.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;


public class EventHandler implements ActionListener {
   
    private GUI frame;
    private Table table;
    private Card card;
    private int numberOfCardsOpen=0;
    private int currentPlayer=0;
    private int numberOfPlayers;
    private CardLayout cl;


    public EventHandler(GUI frame) {
        this.frame = frame;
    }


    /**
     * This runnable class is responsible for the flow of the robot movement.It cretes a new thread specifically for this
     * purpose.
     */
    public class computerThread implements Runnable {
        
        public void run() {    
           
            boolean removed=false;
            for (Integer coordinates: table.getMemoryTable().keySet()) {
                table.getMemoryTable().get(coordinates).removeActionListener(EventHandler.this);
                table.getMemoryTable().get(coordinates).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            try { 
                Thread.sleep(600); 
            }
            catch(InterruptedException ex) { 
                Thread.currentThread().interrupt(); 
            }

            for(int i=0;i<table.getNumberOfChosenCards();i++) {
                Card tempCard= table.getMemoryTable().get(frame.getPlayers().get(currentPlayer).choseCard());
                tempCard.openCard();
                seeCardsForComputers(tempCard.getCountryName(),tempCard.getCoordinates());

                if(i<table.getNumberOfChosenCards()-1) {
                    table.saveChosenCard(tempCard);
                    try { Thread.sleep(2000); }
                    catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
                }
                else {
                    try { 
                        Thread.sleep(2000); 
                    }
                    catch(InterruptedException ex) { 
                        Thread.currentThread().interrupt(); 
                    }

                    removed=table.closeOrRemoveCards(tempCard);
                    if(removed) {
                        removeCards(tempCard);
                        frame.getPlayers().get(currentPlayer).increaseCardsTaken();
                        if(table.isTableEmpty(table.getMemoryTable())) {
                            endgame();
                            return;
                        }
                    }

                    for (Integer coordinates: table.getMemoryTable().keySet()) {
                        Card temporaryCard=table.getMemoryTable().get(coordinates);
                        if(temporaryCard.getExist()) {
                            temporaryCard.addActionListener(EventHandler.this);
                            temporaryCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                }
            }

            if(!removed)
                currentPlayer++;
            if(currentPlayer>=numberOfPlayers) { 
                currentPlayer=0; 
            }
            changePlayerColor();

            if(frame.getPlayers().get(currentPlayer) instanceof ComputerPlayer) {
                Runnable computerAction = new computerThread();
                Thread t2 = new Thread(computerAction);
                t2.start();

                try {
                    t2.join();
                }
                catch (InterruptedException ex){}
            }
            return;
        }
    }


    /**
     * This method controls the computer players' movements in the Duel game type.
     */
    public class computerThreadDuel implements Runnable {
        public void run() {
            for (Integer coordinates: table.getMemoryTable2().keySet()) {
                table.getMemoryTable2().get(coordinates).removeActionListener(EventHandler.this);
                table.getMemoryTable2().get(coordinates).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            try { 
                Thread.sleep(600); 
            }
            catch(InterruptedException ex) { 
                Thread.currentThread().interrupt(); 
            }

            Card tempCard=table.getMemoryTable2().get(frame.getPlayers().get(1).choseCardDuel(table.getSavedCard1().getCountryName()));
            tempCard.openCard();
            seeCardsForComputers(tempCard.getCountryName(),tempCard.getCoordinates());

            try { 
                Thread.sleep(2000); 
            }
            catch(InterruptedException ex) { 
                Thread.currentThread().interrupt(); 
            }

            boolean removed=table.closeOrRemoveCards(tempCard);
            if(removed) {
                removeCards(tempCard);
                frame.getPlayers().get(1).increaseCardsTaken();
                if(table.isTableEmpty(table.getMemoryTable()) && table.isTableEmpty(table.getMemoryTable2())) {
                    endgame();
                    return;
                }
            }
            try { 
                Thread.sleep(1000); 
            }
            catch(InterruptedException ex) { 
                Thread.currentThread().interrupt(); 
            }

            Random r=new Random();
            int coordinates = (int) table.getCoordinatesOfCards().keySet().toArray()[r.nextInt
                    (table.getCoordinatesOfCards().keySet().toArray().length)];

            tempCard=table.getMemoryTable2().get(coordinates);
            tempCard.openCard();
            seeCardsForComputers(tempCard.getCountryName(),tempCard.getCoordinates());
            table.saveChosenCard(tempCard);

            for (Integer coordinates2: table.getMemoryTable2().keySet()) {
                table.getMemoryTable2().get(coordinates2).addActionListener(EventHandler.this);
                table.getMemoryTable2().get(coordinates2).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            numberOfCardsOpen=1;
            currentPlayer++;
            if(currentPlayer>=2) { 
                currentPlayer=0; 
            }
            changePlayerColor();
            return;
        }
    }


    /**
     * The general control center for the buttons.The most important ones are the Go button,which takes the characteristics
     * given by the user and starts the game,and the Card button,which controls the flow of the game through the clicks of
     * the players.There is a different button CardDuel for when the game type is Duel,because of its different nature.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        cl= (CardLayout) (frame.getCards().getLayout());
        String component = e.getActionCommand();

        switch (component) {
            
            case "PLAY":
                cl.show(frame.getCards(), "CARD2");
                break;

            case "HIGH SCORES":
                try {
                    frame.createHighScores();
                } 
                catch (IOException e1) {
                    e1.printStackTrace();
                }
                cl.show(frame.getCards(),"CARD5");
                frame.setSize(1300,500);
                break;

            case "Go Back":
                cl.show(frame.getCards(), "CARD1");
                frame.setSize(700,600);
                break;

            case "Basic":
                frame.setGameType(1);
                frame.changeGameTypeColor();
                break;

            case "Double":
                frame.setGameType(2);
                frame.changeGameTypeColor();
                break;

            case "Triple":
                frame.setGameType(3);
                frame.changeGameTypeColor();
                break;

            case "Duel":
                frame.setGameType(4);
                frame.changeGameTypeColor();
                break;

            case "Go":

                if(!frame.checkUsernames()){break;}
                if(frame.getGameType()==4 && frame.getNumberOfPlayers()!=2) {
                    PopupWindow.infoBox("Duel is played only with 2 players.To play Duel change the number of players to 2",
                            "Duel game type");
                    break;
                }
                if(frame.getGameType()!=4)
                    frame.startCard3();
                else {
                    frame.createDuel();
                    frame.setSize(1000,700);
                }

                if(frame.getGameType()==2) { 
                    frame.setSize(800,700); 
                }
                else if(frame.getGameType()==3) { 
                    frame.setSize(700,700);
                }

                numberOfPlayers=frame.getNumberOfPlayers();
                table=frame.getTable();
                frame.createPlayers();
                if(frame.getGameType()!=4)
                    cl.show(frame.getCards(),"CARD3");
                else
                    cl.show(frame.getCards(), "CARD4");
                break;

            case "Card":

                Object source=e.getSource();
                card=(Card)source;

                numberOfCardsOpen++;
                card.openCard();
                seeCardsForComputers(card.getCountryName(),card.getCoordinates());

                if(numberOfCardsOpen<table.getNumberOfChosenCards()) {
                    table.saveChosenCard(card);
                }
                else if(numberOfCardsOpen==table.getNumberOfChosenCards()) { 
                    card.doClick();
                }
                else {
                    try { 
                        Thread.sleep(2000); 
                    }
                    catch(InterruptedException ex) { 
                        Thread.currentThread().interrupt(); 
                    }

                    boolean removed=table.closeOrRemoveCards(card);
                    frame.getPlayers().get(currentPlayer).increaseSteps();
                    if(removed) {
                        removeCards(card);
                        frame.getPlayers().get(currentPlayer).increaseCardsTaken();
                        if(numberOfPlayers!=1)
                            frame.getListOfPlayersCards().get(currentPlayer).setText(frame.getPlayers().get(currentPlayer).getCardsTaken()+"");
                        if(table.isTableEmpty(table.getMemoryTable()))
                            endgame();
                    }
                    else
                        currentPlayer++;

                    if(currentPlayer>=numberOfPlayers) { 
                        currentPlayer=0;
                    }

                    numberOfCardsOpen=0;
                    if(numberOfPlayers!=1) {
                        changePlayerColor();
                        if(frame.getPlayers().get(currentPlayer) instanceof ComputerPlayer) {
                            Runnable computerAction = new computerThread();
                            Thread t = new Thread(computerAction);
                            t.start();
                        }
                    }
                }
                break;

            case "CardDuel":

                Object source1=e.getSource();
                card=(Card)source1;
                numberOfCardsOpen++;
                card.openCard();

                if(numberOfCardsOpen==1) {
                    
                    table.saveChosenCard(card);

                    if(frame.getPlayers().get(1) instanceof ComputerPlayer) {
                        Runnable computerAction = new computerThreadDuel();
                        Thread t = new Thread(computerAction);
                        t.start();
                    }
                    currentPlayer++;
                    if(currentPlayer>=2) { 
                        currentPlayer=0; 
                    }
                    changePlayerColor();
                }
                else if(numberOfCardsOpen==2) { 
                    card.doClick(); 
                }
                else {
                    try {
                        Thread.sleep(2000); 
                    }
                    catch(InterruptedException ex) { 
                        Thread.currentThread().interrupt(); 
                    }

                    boolean removed=table.closeOrRemoveCards(card);
                    if(removed) {
                        frame.getPlayers().get(currentPlayer).increaseCardsTaken();
                        if(table.isTableEmpty(table.getMemoryTable()) && table.isTableEmpty(table.getMemoryTable2()))
                            endgame();
                    }
                    numberOfCardsOpen=0;
                }
                break;

            default:
                break;
        }
    }


    /**
     * This method changes the label of the player who currently plays to green.
     */
    private void changePlayerColor() {
        frame.getListOfPlayerNames().get(currentPlayer).setBackground(Color.GREEN);
        if (currentPlayer == 0)
            frame.getListOfPlayerNames().get(currentPlayer + numberOfPlayers - 1).setBackground(Color.LIGHT_GRAY);
        else
            frame.getListOfPlayerNames().get(currentPlayer - 1).setBackground(Color.LIGHT_GRAY);
    }


    /**
     * This method shows to all the computer bots the cards that are being opened,from all players.
     * @param countryName
     * @param coordinates
     */
    private void seeCardsForComputers(int countryName,int coordinates) {
        for(int i=0;i<numberOfPlayers;i++)
            if(frame.getPlayers().get(i) instanceof ComputerPlayer)
                frame.getPlayers().get(i).saveCard(countryName,coordinates);
    }


    /**
     * This method removes the cards that are no longer on the table from the 'memory' of the robots,so they cant chose
     * them anymore.
     * @param card
     */
    private void removeCards(Card card) {
        for(int i=0;i<numberOfPlayers;i++)
            if(frame.getPlayers().get(i) instanceof ComputerPlayer) {
                frame.getPlayers().get(i).removeCards(card);
            }
    }


    /**
     * This method is called when the memory table,so it terminates the game and saves the game and saves the name of the
     * winner and his steps.
     */
    private void endgame() {
        if(numberOfPlayers==1) {
            int steps=frame.getPlayers().get(0).getSteps();
            PopupWindow.infoBox("Congratulations "+frame.getUsernames().get(0).getText()+
                    "! You won in "+steps+" steps.", "GAME OVER");
            cl.show(frame.getCards(),"CARD1");
        }
        else {
            int max=0;
            int winner=-1;
            for(int i=0;i<numberOfPlayers;i++) {
                if(frame.getPlayers().get(i).getCardsTaken()>max) {
                    max=frame.getPlayers().get(i).getCardsTaken();
                    winner=i;
                }
                else if(frame.getPlayers().get(i).getCardsTaken()==max) {
                    winner=-1;
                }
            }
            
            String infoMessage="";
            for(int i=0;i<numberOfPlayers;i++)
                infoMessage+=frame.getUsernames().get(i).getText()+" gathered "+frame.getPlayers().get(i).getCardsTaken()+" cards\n";

            if(winner!=-1) {
                infoMessage += "Congratulations " + frame.getUsernames().get(winner).getText() + "! You won.";
                try {
                    frame.getWinner(frame.getUsernames().get(winner).getText(), frame.getPlayers().get(0).getSteps());
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
            else
                infoMessage+="Equilibrium! No winner!";
            PopupWindow.infoBox(infoMessage, "GAME OVER");
            cl.show(frame.getCards(),"CARD1");
        }
    }
}

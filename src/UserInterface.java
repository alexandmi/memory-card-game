/**
 * This class runs the interface between the user and the game.
 */

import java.util.Scanner;


public class UserInterface {
    
    private Scanner sc;
    private Table table1;
    private Player player1;
    private int card1;
    private int card2;
    private int card3;
    private int numberOfChosenCards;
    private String gameType;

    /**
     * The constructor of the UserInterface uses the method setGameType to initialize the gameType.It also creates the
     * object of the player.
     */
    public UserInterface() {
        sc=new Scanner(System.in);
        setGameType();
        player1=new Player();
    }


    /**
     * This method asks from the user to insert one of the three available game types,and it checks whether his
     * insertion is valid or not.Then it prints out instructions on how to insert a coordinate.
     */
    private void setGameType() {
        System.out.println("\nWelcome to the Memory Card Game!\nSelect a game type(Basic,Double or Triple): ");
        gameType = sc.nextLine();
        gameType=gameType.toLowerCase();

        while(!(gameType.equals("basic") || gameType.equals("double") || gameType.equals("triple"))) {
            System.out.println("You did not insert a valid game type.Please select Basic,Double or Triple: ");
            gameType = sc.nextLine();
        }

        table1=new Table(gameType);
        numberOfChosenCards=table1.getNumberOfChosenCards();

        System.out.println("\nYou select a card by inserting the integer of its' coordinates." +
                "The ones are the row and the tens are the line.\ne.g. The card in the 2nd line,3rd row is 23.\n");
    }


    /**
     * This method prints the table to the user and it opens the cards he chose.
     * First it prints the current state of the table,then asks for a card,uses the method checkCardCoordinates to check
     * the user's input,and then opens the card.Depending on the numberOfChosenCards,it does this 2 or 3 times.At the
     * end it uses the method increaseSteps to increase the player's steps.
     * */
    private void choseCards() {
        if(numberOfChosenCards==2) {
            table1.showTable();
            System.out.println("Chose a card: ");
            card1= checkCoordinates();
            table1.openCard(card1);

            table1.showTable();
            System.out.println("Chose another card: ");
            card2= checkCoordinates();
            table1.openCard(card2);
        }
        else {
            table1.showTable();
            System.out.println("Chose a card: ");
            card1= checkCoordinates();
            table1.openCard(card1);

            table1.showTable();
            System.out.println("Chose another card: ");
            card2= checkCoordinates();
            table1.openCard(card2);

            table1.showTable();
            System.out.println("Chose another card: ");
            card3= checkCoordinates();
            table1.openCard(card3);
        }
        player1.increaseSteps();
    }


    /**
     * This method uses the method compareCards to compare the cards' letters.If they are the same,it uses the method
     * removeCard to remove them from the table,or else it just closes them with the method closeCard.At the end it
     * makes a time delay of 3 seconds for user to see the open cards,before they close or get removed.
     */
    private void removeCards() {
        
        table1.showTable();
        
        if(numberOfChosenCards==2) {
            if(table1.compareCards(card1,card2)) {
                table1.removeCard(card1);
                table1.removeCard(card2);
            }
            else {
                table1.closeCard(card1);
                table1.closeCard(card2);
            }
        }
        else {
            if(table1.compareCards(card1,card2,card3)) {
                table1.removeCard(card1);
                table1.removeCard(card2);
                table1.removeCard(card3);
            }
            else {
                table1.closeCard(card1);
                table1.closeCard(card2);
                table1.closeCard(card3);
            }
        }

        try {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }


    /**
     * This method checks if the user's input of coordinates is valid.
     * First,it takes the input as a string and makes it an integer.If the input is anything different or more than an
     * integer,a NumberFormatException will occur,which will make the user give another input. Then,if the input is an
     * integer,but its digits don't represent the lines and the rows of the table(out of bounds coordinates),it makes
     * the user give another input.If the integer is in bounds but the card had been removed,it makes the user give
     * another input.At last if the card of the integer has already been opened,it makes the user give another input.
     *
     * @return If the integer of the user is correct,the method returns it.
     */
    private int checkCoordinates()
    {
        Scanner scan;
        int tempCard=11;     /**The number 11 is random.This initialization happens to avoid an error*/
        String tempString;
        int lines=table1.getLines();
        int rows=table1.getRows();
        int line;
        int row;
        boolean exceptionExists;

        do {
            do {
                try {
                    scan=new Scanner(System.in);
                    tempString= scan.nextLine();
                    tempCard=Integer.parseInt(tempString);
                    exceptionExists=false;
                }
                catch(NumberFormatException e) {
                    System.out.println("False input.You have to insert one integer with the coordinates of a card: ");
                    exceptionExists=true;
                }
            }
            while(exceptionExists);

            row=tempCard%10;
            line=(tempCard-row)/10;
            if(row<1 || row>rows || line<1 || line>lines) {
                exceptionExists=true;
                System.out.println("Out of bounds coordinates.Please insert valid coordinates: ");
            }
            else if(!table1.getExist(tempCard)) {
                exceptionExists=true;
                System.out.println("This card has been removed.Please select another card:");
            }
            else if(table1.getOpen(tempCard)) {
                exceptionExists=true;
                System.out.println("You have already chosen this card.Please select another card:");
            }
        }
        while(exceptionExists);

        return tempCard;
    }


    /**
     * This method is the basic method of the game,which uses the methods choseCards and removeCards until the table
     * is empty(method isTableEmpty),and the user wins.When that happens it prints the steps the user needed to win.
     */
    public void start() {
        do {
            choseCards();
            removeCards();
        }
        while(!(table1.isTableEmpty()));

        System.out.println("Congratulations! You won in "+player1.getSteps()+" steps!");
    }
}

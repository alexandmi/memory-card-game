/**
 * This class is for the game type Duel,which,due to its different nature from the other types,has its own class and panel,
 * which is called CARD4.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Duel extends JPanel {

    Table table;
    ArrayList<JButton> listOfPlayersNames=new ArrayList<>();

    /**
     * The constructor makes the graphics for the game.
     * @param handler
     * @param usernames
     */
    public Duel(EventHandler handler, ArrayList<JTextField> usernames) {
        
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLUE);

        table = new Table(4,handler);

        Insets insets = new Insets(0, 20, 20, 0);
        Dimension cardsDimension = new Dimension(64, 64);
        JButton tempCard;

        GridBagConstraints playersTurn =new GridBagConstraints();
        Dimension usernamesDimension=new Dimension(100,32);

        JButton player1=new JButton(usernames.get(0).getText());
        player1.setPreferredSize(usernamesDimension);
        player1.setBackground(Color.GREEN);
        playersTurn.gridy=0;
        playersTurn.gridx=1;
        playersTurn.gridwidth=2;
        playersTurn.insets=new Insets(0,30,50,0);
        this.add(player1, playersTurn);

        JButton player2=new JButton(usernames.get(1).getText());
        player2.setPreferredSize(usernamesDimension);
        player2.setBackground(Color.LIGHT_GRAY);
        playersTurn.gridx=5;
        playersTurn.gridwidth=2;
        playersTurn.insets=new Insets(0,30,50,0);
        this.add(player2, playersTurn);

        listOfPlayersNames.add(player1);
        listOfPlayersNames.add(player2);

        for (Integer coordinate : table.getMemoryTable().keySet())
            table.getMemoryTable().get(coordinate).setActionCommand("CardDuel");
        for (Integer coordinate : table.getMemoryTable2().keySet())
            table.getMemoryTable2().get(coordinate).setActionCommand("CardDuel");

        for (int i = 1; i < 7 ; i++) {
           
            GridBagConstraints tempConstraints = new GridBagConstraints();
            for (int j = 1; j < 5; j++) {
                tempCard = table.getMemoryTable().get(i * 10 + j);

                tempCard.setPreferredSize(cardsDimension);
                tempCard.setBackground(Color.blue);
                tempCard.setBorderPainted(false);
                tempCard.setForeground(Color.blue);
                tempCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                if(j==1) {
                    tempConstraints.gridy = i;
                    tempConstraints.insets = new Insets(0, 0, 20, 0);
                }
                else {
                    tempConstraints.gridx = j-1;
                    tempConstraints.insets = insets;
                }
                this.add(tempCard, tempConstraints);
            }

            for(int j=1; j<5; j++) {
                
                tempCard = table.getMemoryTable2().get(i * 10 + j);
                tempCard.setPreferredSize(cardsDimension);
                tempCard.setBackground(Color.blue);
                tempCard.setBorderPainted(false);
                tempCard.setForeground(Color.blue);
                tempCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                tempConstraints.gridx = j+3;
                if(j==1)
                    tempConstraints.insets = new Insets(0,100,20,0);
                else
                    tempConstraints.insets = insets;

                this.add(tempCard, tempConstraints);
            }
        }
    }

    public Table getTable() { 
        return table; 
    }

    public ArrayList<JButton> getListOfPlayersNames() { 
        return listOfPlayersNames; 
    }
}

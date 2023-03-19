/**
 * GUI is the class dedicated to graphics,like the creation of panels.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.io.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.Serializable;


public class GUI extends JFrame implements Serializable {

    private JPanel cards;
    private ArrayList<JButton> buttonsOfGameType;
    private JSpinner spinnerNumberOfPlayers;
    private ArrayList<JComboBox> comboBoxesOfPlayers = new ArrayList<>();
    private ArrayList<JComboBox> comboBoxesOfComputers=new ArrayList<>();
    private ArrayList<JTextField> usernames = new ArrayList<>();
    private Table table;
    private EventHandler handler;
    private int numberOfPlayers=1;
    private HighScoresGUI card5;
    private ArrayList<Player> players;
    private ArrayList<JButton> listOfPlayersNames;
    private ArrayList<JLabel> listOfPlayersCards;
    private int gameType=1;

    /**
     * The constructor creates the first 3 panels of the game.
     * The first,named CARD1,has the Play,the HighScores and the languages buttons.The Play button moves you to the second
     * panel to initialize the game characteristics,the HighScores moves you to the panel named CARD5 for the game highscores,
     * and the language buttons change the language of the game from English to Greek and vice versa(unfortunately we didn't
     * have time to make them work).
     * The second,named CARD2,is the panel for initializing the game.There you chose the game type,the number of players,
     * the type of players and their usernames.When you finish you press the button Go to play the game.
     *
     * @throws IOException
     */
    public GUI() throws IOException {

        handler = new EventHandler(this);
        cards = new JPanel(new CardLayout());
        add(cards);
        createHighScores();

        //  CARD1  //

        JPanel card1 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(card1, BoxLayout.Y_AXIS);
        card1.setLayout(boxlayout);
        card1.setBackground(Color.blue);

        JLabel title = new JLabel("MEMORY CARD GAME WITH FLAGS");
        title.setFont(new Font("SERIF", Font.PLAIN, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buttonPlay = new JButton("PLAY");
        buttonPlay.setBackground(Color.CYAN);
        buttonPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPlay.addActionListener(handler);
        buttonPlay.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton buttonHighScores = new JButton("HIGH SCORES");
        buttonHighScores.setBackground(Color.LIGHT_GRAY);
        buttonHighScores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonHighScores.addActionListener(handler);
        buttonHighScores.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup buttonGroupLanguages = new ButtonGroup();

        JRadioButton buttonEnglish = new JRadioButton("English", true);
        buttonEnglish.addActionListener(handler);
        buttonEnglish.setAlignmentX(Component.CENTER_ALIGNMENT);

        JRadioButton buttonGreek = new JRadioButton("Ελληνικά");
        buttonGreek.addActionListener(handler);
        buttonGreek.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonGroupLanguages.add(buttonEnglish);
        buttonGroupLanguages.add(buttonGreek);

        card1.add(Box.createRigidArea(new Dimension(0, 10)));
        card1.add(title);
        card1.add(Box.createRigidArea(new Dimension(0, 90)));
        card1.add(buttonPlay);
        card1.add(Box.createRigidArea(new Dimension(0, 30)));
        card1.add(buttonHighScores);
        card1.add(Box.createRigidArea(new Dimension(0, 20)));
        card1.add(buttonEnglish);
        card1.add(Box.createRigidArea(new Dimension(0, 10)));
        card1.add(buttonGreek);

        //  CARD 2  //

        JPanel card2 = new JPanel();
        card2.setLayout(new GridBagLayout());
        card2.setBackground(Color.blue);
        GridBagConstraints back=new GridBagConstraints();

        JButton goBack=new JButton("Go Back");
        goBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        goBack.addActionListener(handler);
        back.gridy = 0;
        back.gridx = 0;
        card2.add(goBack,back);
        GridBagConstraints a = new GridBagConstraints();

        Font font;
        Map attributes;

        JLabel labelChoseGameType = new JLabel("Chose a GameType:");
        font = labelChoseGameType.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        labelChoseGameType.setFont(font.deriveFont(attributes));
        a.gridy = 1;
        a.gridwidth = 4;
        card2.add(labelChoseGameType, a);

        GridBagConstraints b = new GridBagConstraints();
        buttonsOfGameType=new ArrayList<>();

        JButton buttonBasic = new JButton("Basic");
        buttonBasic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonBasic.addActionListener(handler);
        buttonBasic.setBackground(Color.green);
        b.gridy = 2;
        b.insets = new Insets(10, 0, 0, 0);
        card2.add(buttonBasic, b);
        buttonsOfGameType.add(buttonBasic);

        JButton buttonDouble = new JButton("Double");
        buttonDouble.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonDouble.addActionListener(handler);
        buttonDouble.setBackground(Color.white);
        b.insets = new Insets(10, 1, 0, 0);
        b.gridx = 1;
        card2.add(buttonDouble, b);
        buttonsOfGameType.add(buttonDouble);

        JButton buttonTriple = new JButton("Triple");
        buttonTriple.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonTriple.addActionListener(handler);
        buttonTriple.setBackground(Color.white);
        b.gridx = 2;
        card2.add(buttonTriple, b);
        buttonsOfGameType.add(buttonTriple);

        JButton buttonDuel = new JButton("Duel");
        buttonDuel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonDuel.addActionListener(handler);
        buttonDuel.setBackground(Color.white);
        b.gridx = 3;
        b.insets = new Insets(10, 1, 0, 0);
        card2.add(buttonDuel, b);
        buttonsOfGameType.add(buttonDuel);

        GridBagConstraints c = new GridBagConstraints();

        JLabel labelChoseNumberOfPlayers = new JLabel("Chose number of players:");
        font = labelChoseNumberOfPlayers.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        labelChoseNumberOfPlayers.setFont(font.deriveFont(attributes));
        c.insets = new Insets(60, -30, 0, 0);
        c.gridy = 3;
        c.gridx = 1;
        c.gridwidth = 2;
        card2.add(labelChoseNumberOfPlayers, c);

        SpinnerNumberModel bounds = new SpinnerNumberModel(1.0, 1.0, 4.0, 1.0);
        spinnerNumberOfPlayers = new JSpinner(bounds);
        spinnerNumberOfPlayers.setEditor(new JSpinner.DefaultEditor(spinnerNumberOfPlayers));
        spinnerNumberOfPlayers.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Double num = (Double) spinnerNumberOfPlayers.getValue();
                numberOfPlayers = num.intValue();
                disablePlayers();
            }
        });
        c.gridx = 2;
        c.insets = new Insets(55, 40, 0, 0);
        card2.add(spinnerNumberOfPlayers, c);

        GridBagConstraints d = new GridBagConstraints();
        ArrayList<JLabel> labelsOfPlayers = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            JLabel tempLabelPlayer=new JLabel("Player "+(i+1));
            font = tempLabelPlayer.getFont();
            attributes = font.getAttributes();
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            tempLabelPlayer.setFont(font.deriveFont(attributes));
            labelsOfPlayers.add(tempLabelPlayer);
        }

        d.insets = new Insets(25, 0, 0, 0);
        d.gridy = 4;
        card2.add(labelsOfPlayers.get(0), d);

        d.insets = new Insets(25, 0, 0, 0);
        d.gridx = 1;
        card2.add(labelsOfPlayers.get(1), d);

        d.insets = new Insets(25, 0, 0, 0);
        d.gridx = 2;
        card2.add(labelsOfPlayers.get(2), d);

        d.insets = new Insets(25, 0, 0, 0);
        d.gridx = 3;
        card2.add(labelsOfPlayers.get(3), d);

        GridBagConstraints e = new GridBagConstraints();

        for (int i = 0; i < 4; i++) {
            JComboBox<String> tempComboBox = new JComboBox<>();
            String number = Integer.toString(i);
            tempComboBox.setName(number);
            tempComboBox.addItem("Normal");
            tempComboBox.addActionListener(handler);
            tempComboBox.setEditable(false);

            if (i >= 1) {
                tempComboBox.addItem("Computer");
                tempComboBox.setEnabled(false);
                tempComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JComboBox comboBox = (JComboBox) e.getSource();
                        Object selected = comboBox.getSelectedItem();
                        assert selected != null;
                        int number = Integer.parseInt(comboBox.getName());
                        if(selected.toString().equals("Computer"))
                            comboBoxesOfComputers.get(number-1).setEnabled(true);
                        else
                            comboBoxesOfComputers.get(number-1).setEnabled(false);
                    }
                });
            }
            comboBoxesOfPlayers.add(tempComboBox);
        }

        e.insets = new Insets(10, 0, 0, 0);
        e.gridy = 5;
        card2.add(comboBoxesOfPlayers.get(0), e);

        e.insets = new Insets(10, 10, 0, 0);
        e.gridx = 1;
        card2.add(comboBoxesOfPlayers.get(1), e);

        e.insets = new Insets(10, 10, 0, 0);
        e.gridx = 2;
        card2.add(comboBoxesOfPlayers.get(2), e);

        e.insets = new Insets(10, 10, 0, 0);
        e.gridx = 3;
        card2.add(comboBoxesOfPlayers.get(3), e);

        GridBagConstraints h =new GridBagConstraints();

        for (int i = 0; i < 3; i++) {
            JComboBox<String> tempComboBox = new JComboBox<>();
            String number = Integer.toString(i);

            tempComboBox.setName(number);
            tempComboBox.addItem("Goldfish");
            tempComboBox.addItem("Kangaroo");
            tempComboBox.addItem("Elephant");
            tempComboBox.setEditable(false);
            tempComboBox.setEnabled(false);

            comboBoxesOfComputers.add(tempComboBox);
        }

        h.insets = new Insets(10, 0, 0, 0);
        h.gridy = 6;
        h.gridx = 1;
        card2.add(comboBoxesOfComputers.get(0), h);

        h.insets = new Insets(10, 10, 0, 0);
        h.gridx = 2;
        card2.add(comboBoxesOfComputers.get(1), h);

        h.insets = new Insets(10, 10, 0, 0);
        h.gridx = 3;
        card2.add(comboBoxesOfComputers.get(2), h);

        GridBagConstraints f = new GridBagConstraints();
        Dimension dimensionUsernames = new Dimension(80, 30);

        for (int i = 0; i < 4; i++) {
            JTextField tempTextField=new JTextField();
            tempTextField.setPreferredSize(dimensionUsernames);
            if(i==0)
                tempTextField.setEnabled(true);
            else
                tempTextField.setEnabled(false);

            usernames.add(tempTextField);
        }

        f.insets = new Insets(10, 0, 0, 0);
        f.gridy = 7;
        card2.add(usernames.get(0), f);

        f.insets = new Insets(10, 0, 0, 0);
        f.gridx = 1;
        card2.add(usernames.get(1), f);

        f.insets = new Insets(10, 0, 0, 0);
        f.gridx = 2;
        card2.add(usernames.get(2), f);

        f.insets = new Insets(10, 0, 0, 0);
        f.gridx = 3;
        card2.add(usernames.get(3), f);

        GridBagConstraints g = new GridBagConstraints();
        JButton buttonGo = new JButton("Go");
        buttonGo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonGo.addActionListener(handler);

        g.insets = new Insets(10, 0, 0, 0);
        g.gridy=8;
        g.gridx=1;
        card2.add(buttonGo,g);

        cards.add(card1, "CARD1");
        cards.add(card2, "CARD2");
        cards.add(card5,"CARD5");

        setTitle("Memory Card Game");
        setSize(700, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * This method creates the CARD3 panel,which is the one where the game takes place.It has the cards-buttons as well as
     * labels with the players' names which are made by the createPlayersButtons method.
     */
    public void startCard3() {
        JPanel card3 = new JPanel();
        card3.setLayout(new GridBagLayout());
        card3.setBackground(Color.BLUE);

        table = new Table(gameType,handler);
        createPlayersButtons(card3);

        Insets insets = new Insets(0, 20, 20, 0);
        Dimension cardsDimension = new Dimension(64, 64);
        JButton tempCard;

        for (int i = 1; i < table.getLines() + 1; i++) {
            GridBagConstraints tempConstraints = new GridBagConstraints();
            tempCard = table.getMemoryTable().get(i * 10 + 1);

            tempCard.setPreferredSize(cardsDimension);
            tempCard.setBackground(Color.blue);
            tempCard.setBorderPainted(false);
            tempCard.setForeground(Color.blue);
            tempCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            tempConstraints.gridy = i;
            tempConstraints.insets = new Insets(0, 0, 20, 0);
            card3.add(tempCard, tempConstraints);

            for (int j = 1; j < table.getRows(); j++) {
                tempCard = table.getMemoryTable().get(i * 10 + j + 1);
                tempCard.setPreferredSize(cardsDimension);
                tempCard.setBackground(Color.blue);
                tempCard.setBorderPainted(false);
                tempCard.setForeground(Color.blue);
                tempCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                tempConstraints.gridx = j;
                tempConstraints.insets = insets;

                card3.add(tempCard, tempConstraints);
            }
        }
        cards.add(card3, "CARD3");
    }


    /**
     * This method makes the options for some players' type and players' username not available when the number of players as chosen
     * by the JSpinner require less players.
     */
    public void disablePlayers() {
        int i;
        int numberOfItems = comboBoxesOfPlayers.get(0).getItemCount();

        if (numberOfPlayers == 1 && numberOfItems == 2)
            comboBoxesOfPlayers.get(0).removeItem("Computer");

        for (i = 0; i < numberOfPlayers; i++) {
            comboBoxesOfPlayers.get(i).setEnabled(true);
            usernames.get(i).setEnabled(true);
            if (i != 0 && comboBoxesOfPlayers.get(i).getSelectedItem().toString().equals("Computer"))
                comboBoxesOfComputers.get(i - 1).setEnabled(true);
        }
        while (i < 4) {
            comboBoxesOfPlayers.get(i).setEnabled(false);
            comboBoxesOfComputers.get(i - 1).setEnabled(false);
            usernames.get(i).setEnabled(false);
            i++;
        }
    }


    /**
     * This method creates the labels of the players' usernames in panel CARD3 and displays them on top of the memory cards.
     * The name of the player whose turn is is green,while the other names are grey.
     * @param card3
     */
    public void createPlayersButtons(JPanel card3) {
        GridBagConstraints playersTurn = new GridBagConstraints();
        Dimension usernamesDimension = new Dimension(100, 32);

        listOfPlayersNames = new ArrayList<>();
        listOfPlayersCards = new ArrayList<>();

        if(numberOfPlayers==1) {
             return;
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            JButton tempPlayer = new JButton();
            listOfPlayersNames.add(tempPlayer);

            JLabel tempCards = new JLabel();
            listOfPlayersCards.add(tempCards);
        }

        int rows = table.getRows();

        if (numberOfPlayers == 2) {
            listOfPlayersNames.get(0).setText(usernames.get(0).getText());
            listOfPlayersNames.get(0).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(0).setBackground(Color.GREEN);
            playersTurn.gridy = 0;
            playersTurn.gridx = rows / 2 - 2;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(0), playersTurn);

            listOfPlayersNames.get(1).setText(usernames.get(1).getText());
            listOfPlayersNames.get(1).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(1).setBackground(Color.LIGHT_GRAY);
            playersTurn.gridx = rows / 2;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(1), playersTurn);
        } 
        else if (numberOfPlayers == 3) {
            listOfPlayersNames.get(0).setText(usernames.get(0).getText());
            listOfPlayersNames.get(0).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(0).setBackground(Color.GREEN);
            playersTurn.gridy = 0;
            playersTurn.gridx = rows / 2 - 3;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(0), playersTurn);

            listOfPlayersNames.get(1).setText(usernames.get(1).getText());
            listOfPlayersNames.get(1).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(1).setBackground(Color.LIGHT_GRAY);
            playersTurn.gridx = rows / 2 - 1;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(1), playersTurn);

            listOfPlayersNames.get(2).setText(usernames.get(2).getText());
            listOfPlayersNames.get(2).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(2).setBackground(Color.LIGHT_GRAY);
            playersTurn.gridx = rows / 2 + 1;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(2), playersTurn);
        }
        else if (numberOfPlayers == 4 && !(gameType == 2)) {
            listOfPlayersNames.get(0).setText(usernames.get(0).getText());
            listOfPlayersNames.get(0).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(0).setBackground(Color.GREEN);
            playersTurn.gridy = 0;
            playersTurn.gridx = 0;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 50);
            card3.add(listOfPlayersNames.get(0), playersTurn);

            listOfPlayersNames.get(1).setText(usernames.get(1).getText());
            listOfPlayersNames.get(1).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(1).setBackground(Color.LIGHT_GRAY);
            playersTurn.gridx = 1;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 60, 50, 0);
            card3.add(listOfPlayersNames.get(1), playersTurn);

            listOfPlayersNames.get(2).setText(usernames.get(2).getText());
            listOfPlayersNames.get(2).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(2).setBackground(Color.LIGHT_GRAY);
            playersTurn.gridx = 3;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 20);
            card3.add(listOfPlayersNames.get(2), playersTurn);

            listOfPlayersNames.get(3).setText(usernames.get(3).getText());
            listOfPlayersNames.get(3).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(3).setBackground(Color.LIGHT_GRAY);
            playersTurn.gridx = 4;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 70, 50, 0);
            card3.add(listOfPlayersNames.get(3), playersTurn);
        }
        else if (numberOfPlayers == 4 && gameType == 2) {
            listOfPlayersNames.get(0).setText(usernames.get(0).getText());
            listOfPlayersNames.get(0).setPreferredSize(usernamesDimension);
            listOfPlayersNames.get(0).setBackground(Color.GREEN);
            playersTurn.gridy = 0;
            playersTurn.gridx = 0;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(0), playersTurn);

            listOfPlayersNames.get(1).setText(usernames.get(1).getText());
            listOfPlayersNames.get(1).setPreferredSize(usernamesDimension);
            playersTurn.gridx = 2;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(1), playersTurn);

            listOfPlayersNames.get(2).setText(usernames.get(2).getText());
            listOfPlayersNames.get(2).setPreferredSize(usernamesDimension);
            playersTurn.gridx = 4;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(2), playersTurn);

            listOfPlayersNames.get(3).setText(usernames.get(3).getText());
            listOfPlayersNames.get(3).setPreferredSize(usernamesDimension);
            playersTurn.gridx = 6;
            playersTurn.gridwidth = 2;
            playersTurn.insets = new Insets(0, 0, 50, 0);
            card3.add(listOfPlayersNames.get(3), playersTurn);
        }
    }


    /**
     * This method changes color to the buttons of players in panel CARD2,so you can see what is your current game type
     * option.
     */
    public void changeGameTypeColor() {
        for(int i=0;i<4;i++) {
            if(i==gameType-1) {
                buttonsOfGameType.get(i).setBackground(Color.GREEN);
            }
            else
                buttonsOfGameType.get(i).setBackground(Color.white);
        }
    }


    /**
     * This method checks whether the usernames submitted by the users are valid,meaning the are not blank or too long.
     * @return
     */
    public boolean checkUsernames() {
        for (int i = 0; i < numberOfPlayers; i++) {
            if (usernames.get(i).getText() == null || usernames.get(i).getText().length()==0) {
                PopupWindow.infoBox("Dont't leave a username empty", "Empty username");
                return false;
            }
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            if (usernames.get(i).getText().length() > 10) {
                PopupWindow.infoBox("Your username must have at most 10 characters", "Too long username");
                return false;
            }
        }
        return true;
    }


    /**
     * This method takes characteristics of the players the user has committed and creates their objects.There are 2 types
     * of players,humans and robots.
     */
    public void createPlayers() {
        
        players=new ArrayList<>();
        HumanPlayer tempHumanPlayer;
        ComputerPlayer tempComputerPlayer;
        tempHumanPlayer = new HumanPlayer(usernames.get(0).getText());
        players.add(tempHumanPlayer);

        for(int i=1;i<numberOfPlayers;i++) {
            if(comboBoxesOfPlayers.get(i).getSelectedIndex()==0) {
                tempHumanPlayer = new HumanPlayer(usernames.get(i).getText());
                players.add(tempHumanPlayer);
            }
            else {
                tempComputerPlayer = new ComputerPlayer(usernames.get(i).getText(),table,comboBoxesOfComputers.get(i-1).getSelectedIndex());
                players.add(tempComputerPlayer);
            }
        }
    }


    /**
     * This method calls the constructor of the Duel class,and creates the Duel game interface for the CARD3.The reason
     * this game type has different method,and even a class of its own is because it is different from the others at its
     * game style.
     */
    public void createDuel() {
        Duel duel=new Duel(handler,usernames);
        table=duel.getTable();
        listOfPlayersNames=duel.getListOfPlayersNames();
        cards.add(duel, "CARD4");
    }


    /**
     * This method is called when the game is over,and it takes the username of the winner,as well as his steps to complete
     * the game if it's a single player game,and it adds them in the HighScores file.
     *
     * @param username
     * @param steps
     * @throws IOException
     */
    public void getWinner(String username,int steps) throws IOException {
        HighScores tempHighScores;
        ArrayList<ArrayList<UserInformation>> highscoresSinglePlayer;
        ArrayList<ArrayList<UserInformation>> highscoresMultiPlayer;
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("HighScores"))) {
            tempHighScores = (HighScores) in.readObject();

            if(numberOfPlayers==1) {
                tempHighScores.putScoreInSinglePlayer(username, steps, gameType);
            }
            else {
                tempHighScores.putScoreMultiPlayer(username,gameType);
            }

            highscoresSinglePlayer=tempHighScores.getHighScoresSinglePlayer();
            highscoresMultiPlayer=tempHighScores.getHighScoresMultiPlayer();

            for(int i=0;i<10;i++) {
                System.out.println(highscoresSinglePlayer.get(0).get(i).getName());
            }

            card5=new HighScoresGUI(highscoresSinglePlayer,highscoresMultiPlayer,handler);

            try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("HighScores"))) {
                out.writeObject(tempHighScores);
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method creates the object HighScoresGUI with the help of the HighScores object,and the file HighScores which is
     * used to store the object,via Serialization.
     * @throws IOException
     */
    public  void createHighScores() throws IOException {
        HighScores tempHighScores;
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("HighScores"))) {
            tempHighScores = (HighScores) in.readObject();
            card5=new HighScoresGUI(tempHighScores.getHighScoresSinglePlayer(),tempHighScores.getHighScoresMultiPlayer(),handler);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public JPanel getCards() { return cards; }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getGameType() {
        return gameType;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<JButton> getListOfPlayerNames() {
        return listOfPlayersNames ;
    }

    public ArrayList<JLabel> getListOfPlayersCards() {
        return listOfPlayersCards;
    }

    public ArrayList<JTextField> getUsernames() {
        return usernames;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Table getTable() { 
        return table;
    }
}
/**
 * This class creates the graphics of the High Scores panel,the CARD5.It takes the 2 ArrayLists of the HighScore object
 * with the Single player highscores and the Multi player highscores,and prints them.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;


public class HighScoresGUI extends JPanel {

    private Font font;
    private Map attributes;
    private EventHandler handler;

    public HighScoresGUI(ArrayList<ArrayList<UserInformation>> highscoresSinglePlayer,
                         ArrayList<ArrayList<UserInformation>> highscoresMultiPlayer,EventHandler handler) {

        this.handler=handler;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.blue);

        JPanel panelHighScores=new JPanel();
        panelHighScores.setLayout(new BoxLayout(panelHighScores,BoxLayout.X_AXIS));
        panelHighScores.setBackground(Color.blue);

        JButton GoBack=new JButton("Go Back");
        GoBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        GoBack.addActionListener(handler);
        panelHighScores.add(Box.createRigidArea(new Dimension(20, 10)));
        panelHighScores.add(GoBack);

        JLabel HighScores=new JLabel("HIGH SCORES");
        HighScores.setFont(new Font("Calibri", Font.PLAIN, 30));
        font = HighScores.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        HighScores.setFont(font.deriveFont(attributes));
        HighScores.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHighScores.add(Box.createRigidArea(new Dimension(0, 10)));
        panelHighScores.add(HighScores);

        panelHighScores.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panelHighScores);

        JPanel Context=new JPanel();
        Context.setLayout(new BoxLayout(Context,BoxLayout.X_AXIS));
        Context.setBackground(Color.blue);

        JPanel BasicS=new JPanel();
        BasicS.setLayout(new BoxLayout(BasicS,BoxLayout.X_AXIS));
        BasicS.setBackground(Color.blue);

        JPanel rankBasicS=new JPanel();
        rankBasicS.setLayout(new BoxLayout(rankBasicS,BoxLayout.Y_AXIS));
        rankBasicS.setBackground(Color.blue);

        JLabel rankLabelBasicS=new JLabel("Rank");
        rankLabelBasicS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = rankLabelBasicS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        rankLabelBasicS.setFont(font.deriveFont(attributes));
        rankLabelBasicS.setAlignmentX(Component.CENTER_ALIGNMENT);
        rankBasicS.add(rankLabelBasicS);

        for(int i=1;i<11;i++) {
            JLabel tempRank=new JLabel(i+".");
            tempRank.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempRank.setAlignmentX(Component.CENTER_ALIGNMENT);
            rankBasicS.add(Box.createRigidArea(new Dimension(0, 8)));
            rankBasicS.add(tempRank);
        }
        rankBasicS.setAlignmentX(Component.CENTER_ALIGNMENT);
        BasicS.add(rankBasicS);
        BasicS.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel usernamesBasicS=new JPanel();
        usernamesBasicS.setLayout(new BoxLayout(usernamesBasicS,BoxLayout.Y_AXIS));
        usernamesBasicS.setBackground(Color.blue);

        JLabel usernamesLabelBasicS=new JLabel("Usernames");
        usernamesLabelBasicS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = usernamesLabelBasicS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        usernamesLabelBasicS.setFont(font.deriveFont(attributes));
        usernamesLabelBasicS.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamesBasicS.add(usernamesLabelBasicS);

        for(int i=1;i<11;i++) {
            JLabel tempUsername;

            tempUsername=new JLabel(highscoresSinglePlayer.get(0).get(i-1).getName());
            tempUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
            usernamesBasicS.add(Box.createRigidArea(new Dimension(0, 8)));
            usernamesBasicS.add(tempUsername);
        }
        usernamesBasicS.setAlignmentX(Component.CENTER_ALIGNMENT);
        BasicS.add(usernamesBasicS);
        BasicS.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel stepsBasicS=new JPanel();
        stepsBasicS.setLayout(new BoxLayout(stepsBasicS,BoxLayout.Y_AXIS));
        stepsBasicS.setBackground(Color.blue);

        JLabel stepsLabelBasicS=new JLabel("Steps");
        stepsLabelBasicS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = stepsLabelBasicS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        stepsLabelBasicS.setFont(font.deriveFont(attributes));
        stepsLabelBasicS.setAlignmentX(Component.CENTER_ALIGNMENT);
        stepsBasicS.add(stepsLabelBasicS);

        for(int i=1;i<11;i++) {
            JLabel tempSteps;
            if(highscoresSinglePlayer.get(0).get(i-1).getStepsOrWins()!=0) {
                tempSteps=new JLabel(highscoresSinglePlayer.get(0).get(i-1).getStepsOrWins()+"");
            }
            else {
                tempSteps=new JLabel("-");
            }
            tempSteps.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempSteps.setAlignmentX(Component.CENTER_ALIGNMENT);
            stepsBasicS.add(Box.createRigidArea(new Dimension(0, 8)));
            stepsBasicS.add(tempSteps);
        }
        stepsBasicS.setAlignmentX(Component.CENTER_ALIGNMENT);
        BasicS.add(stepsBasicS);

        Context.add(BasicS);
        Context.add(Box.createRigidArea(new Dimension(15, 0)));

        JPanel DoubleS=new JPanel();
        DoubleS.setLayout(new BoxLayout(DoubleS,BoxLayout.X_AXIS));
        DoubleS.setBackground(Color.blue);

        JPanel rankDoubleS=new JPanel();
        rankDoubleS.setLayout(new BoxLayout(rankDoubleS,BoxLayout.Y_AXIS));
        rankDoubleS.setBackground(Color.blue);

        JLabel rankLabelDoubleS=new JLabel("Rank");
        rankLabelDoubleS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = rankLabelDoubleS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        rankLabelDoubleS.setFont(font.deriveFont(attributes));
        rankLabelDoubleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        rankDoubleS.add(rankLabelDoubleS);

        for(int i=1;i<11;i++) {
            JLabel tempRank=new JLabel(i+".");
            tempRank.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempRank.setAlignmentX(Component.CENTER_ALIGNMENT);
            rankDoubleS.add(Box.createRigidArea(new Dimension(0, 8)));
            rankDoubleS.add(tempRank);
        }
        rankDoubleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        DoubleS.add(rankDoubleS);
        DoubleS.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel usernamesDoubleS=new JPanel();
        usernamesDoubleS.setLayout(new BoxLayout(usernamesDoubleS,BoxLayout.Y_AXIS));
        usernamesDoubleS.setBackground(Color.blue);

        JLabel usernamesLabelDoubleS=new JLabel("Usernames");
        usernamesLabelDoubleS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = usernamesLabelDoubleS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        usernamesLabelDoubleS.setFont(font.deriveFont(attributes));
        usernamesLabelDoubleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamesDoubleS.add(usernamesLabelDoubleS);

        for(int i=1;i<11;i++) {
            JLabel tempUsername;
            tempUsername=new JLabel(highscoresSinglePlayer.get(1).get(i-1).getName());
            tempUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
            usernamesDoubleS.add(Box.createRigidArea(new Dimension(0, 8)));
            usernamesDoubleS.add(tempUsername);
        }
        usernamesDoubleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        DoubleS.add(usernamesDoubleS);
        DoubleS.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel stepsDoubleS=new JPanel();
        stepsDoubleS.setLayout(new BoxLayout(stepsDoubleS,BoxLayout.Y_AXIS));
        stepsDoubleS.setBackground(Color.blue);

        JLabel stepsLabelDoubleS=new JLabel("Steps");
        stepsLabelDoubleS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = stepsLabelDoubleS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        stepsLabelDoubleS.setFont(font.deriveFont(attributes));
        stepsLabelDoubleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        stepsDoubleS.add(stepsLabelDoubleS);

        for(int i=1;i<11;i++) {
            JLabel tempSteps;
            if(highscoresSinglePlayer.get(1).get(i-1).getStepsOrWins()!=0) {
                tempSteps=new JLabel(highscoresSinglePlayer.get(1).get(i-1).getStepsOrWins()+"");
            }
            else {
                tempSteps=new JLabel("-");
            }
            tempSteps.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempSteps.setAlignmentX(Component.CENTER_ALIGNMENT);
            stepsDoubleS.add(Box.createRigidArea(new Dimension(0, 8)));
            stepsDoubleS.add(tempSteps);
        }
        stepsDoubleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        DoubleS.add(stepsDoubleS);

        Context.add(DoubleS);
        Context.add(Box.createRigidArea(new Dimension(15, 0)));

        JPanel TripleS=new JPanel();
        TripleS.setLayout(new BoxLayout(TripleS,BoxLayout.X_AXIS));
        TripleS.setBackground(Color.blue);

        JPanel rankTripleS=new JPanel();
        rankTripleS.setLayout(new BoxLayout(rankTripleS,BoxLayout.Y_AXIS));
        rankTripleS.setBackground(Color.blue);

        JLabel rankLabelTripleS=new JLabel("Rank");
        rankLabelTripleS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = rankLabelTripleS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        rankLabelTripleS.setFont(font.deriveFont(attributes));
        rankLabelTripleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        rankTripleS.add(rankLabelTripleS);

        for(int i=1;i<11;i++) {
            JLabel tempRank=new JLabel(i+".");
            tempRank.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempRank.setAlignmentX(Component.CENTER_ALIGNMENT);
            rankTripleS.add(Box.createRigidArea(new Dimension(0, 8)));
            rankTripleS.add(tempRank);
        }
        rankTripleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        TripleS.add(rankTripleS);
        TripleS.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel usernamesTripleS=new JPanel();
        usernamesTripleS.setLayout(new BoxLayout(usernamesTripleS,BoxLayout.Y_AXIS));
        usernamesTripleS.setBackground(Color.blue);

        JLabel usernamesLabelTripleS=new JLabel("Usernames");
        usernamesLabelTripleS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = usernamesLabelTripleS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        usernamesLabelTripleS.setFont(font.deriveFont(attributes));
        usernamesLabelTripleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamesTripleS.add(usernamesLabelTripleS);

        for(int i=1;i<11;i++) {
            JLabel tempUsername;
            tempUsername=new JLabel(highscoresSinglePlayer.get(2).get(i-1).getName());
            tempUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
            usernamesTripleS.add(Box.createRigidArea(new Dimension(0, 8)));
            usernamesTripleS.add(tempUsername);
        }
        usernamesTripleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        TripleS.add(usernamesTripleS);
        TripleS.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel stepsTripleS=new JPanel();
        stepsTripleS.setLayout(new BoxLayout(stepsTripleS,BoxLayout.Y_AXIS));
        stepsTripleS.setBackground(Color.blue);

        JLabel stepsLabelTripleS=new JLabel("Steps");
        stepsLabelTripleS.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = stepsLabelTripleS.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        stepsLabelTripleS.setFont(font.deriveFont(attributes));
        stepsLabelTripleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        stepsTripleS.add(stepsLabelTripleS);

        for(int i=1;i<11;i++) {
            JLabel tempSteps;
            if(highscoresSinglePlayer.get(2).get(i-1).getStepsOrWins()!=0) {
                tempSteps=new JLabel(highscoresSinglePlayer.get(2).get(i-1).getStepsOrWins()+"");
            }
            else {
                tempSteps=new JLabel("-");
            }
            tempSteps.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempSteps.setAlignmentX(Component.CENTER_ALIGNMENT);
            stepsTripleS.add(Box.createRigidArea(new Dimension(0, 8)));
            stepsTripleS.add(tempSteps);
        }
        stepsTripleS.setAlignmentX(Component.CENTER_ALIGNMENT);
        TripleS.add(stepsTripleS);

        Context.add(TripleS);
        Context.add(Box.createRigidArea(new Dimension(40, 0)));

        JPanel BasicM=new JPanel();
        BasicM.setLayout(new BoxLayout(BasicM,BoxLayout.X_AXIS));
        BasicM.setBackground(Color.blue);

        JPanel rankBasicM=new JPanel();
        rankBasicM.setLayout(new BoxLayout(rankBasicM,BoxLayout.Y_AXIS));
        rankBasicM.setBackground(Color.blue);

        JLabel rankLabelBasicM=new JLabel("Rank");
        rankLabelBasicM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = rankLabelBasicM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        rankLabelBasicM.setFont(font.deriveFont(attributes));
        rankLabelBasicM.setAlignmentX(Component.CENTER_ALIGNMENT);
        rankBasicM.add(rankLabelBasicM);

        for(int i=1;i<11;i++) {
            JLabel tempRank=new JLabel(i+".");
            tempRank.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempRank.setAlignmentX(Component.CENTER_ALIGNMENT);
            rankBasicM.add(Box.createRigidArea(new Dimension(0, 8)));
            rankBasicM.add(tempRank);
        }
        rankBasicM.setAlignmentX(Component.CENTER_ALIGNMENT);
        BasicM.add(rankBasicM);
        BasicM.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel usernamesBasicM=new JPanel();
        usernamesBasicM.setLayout(new BoxLayout(usernamesBasicM,BoxLayout.Y_AXIS));
        usernamesBasicM.setBackground(Color.blue);

        JLabel usernamesLabelBasicM=new JLabel("Usernames");
        usernamesLabelBasicM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = usernamesLabelBasicM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        usernamesLabelBasicM.setFont(font.deriveFont(attributes));
        usernamesLabelBasicM.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamesBasicM.add(usernamesLabelBasicM);

        for(int i=1;i<11;i++) {
            JLabel tempUsername;
            tempUsername=new JLabel(highscoresMultiPlayer.get(0).get(i-1).getName());
            tempUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
            usernamesBasicM.add(Box.createRigidArea(new Dimension(0, 8)));
            usernamesBasicM.add(tempUsername);
        }
        usernamesBasicM.setAlignmentX(Component.CENTER_ALIGNMENT);
        BasicM.add(usernamesBasicM);
        BasicM.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel winsBasicM=new JPanel();
        winsBasicM.setLayout(new BoxLayout(winsBasicM,BoxLayout.Y_AXIS));
        winsBasicM.setBackground(Color.blue);

        JLabel winsLabelBasicM=new JLabel("Wins");
        winsLabelBasicM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = winsLabelBasicM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        winsLabelBasicM.setFont(font.deriveFont(attributes));
        winsLabelBasicM.setAlignmentX(Component.CENTER_ALIGNMENT);
        winsBasicM.add(winsLabelBasicM);

        for(int i=1;i<11;i++) {
            JLabel tempWins;
            if(highscoresMultiPlayer.get(0).get(i-1).getStepsOrWins()!=0) {
                tempWins=new JLabel(highscoresMultiPlayer.get(0).get(i-1).getStepsOrWins()+"");
            }
            else {
                tempWins=new JLabel("-");
            }
            tempWins.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempWins.setAlignmentX(Component.CENTER_ALIGNMENT);
            winsBasicM.add(Box.createRigidArea(new Dimension(0, 8)));
            winsBasicM.add(tempWins);
        }
        winsBasicM.setAlignmentX(Component.CENTER_ALIGNMENT);
        BasicM.add(winsBasicM);

        Context.add(BasicM);
        Context.add(Box.createRigidArea(new Dimension(15, 0)));

        JPanel DoubleM=new JPanel();
        DoubleM.setLayout(new BoxLayout(DoubleM,BoxLayout.X_AXIS));
        DoubleM.setBackground(Color.blue);

        JPanel rankDoubleM=new JPanel();
        rankDoubleM.setLayout(new BoxLayout(rankDoubleM,BoxLayout.Y_AXIS));
        rankDoubleM.setBackground(Color.blue);

        JLabel rankLabelDoubleM=new JLabel("Rank");
        rankLabelDoubleM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = rankLabelDoubleM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        rankLabelDoubleM.setFont(font.deriveFont(attributes));
        rankLabelDoubleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        rankDoubleM.add(rankLabelDoubleM);

        for(int i=1;i<11;i++) {
            JLabel tempRank=new JLabel(i+".");
            tempRank.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempRank.setAlignmentX(Component.CENTER_ALIGNMENT);
            rankDoubleM.add(Box.createRigidArea(new Dimension(0, 8)));
            rankDoubleM.add(tempRank);
        }
        rankDoubleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        DoubleM.add(rankDoubleM);
        DoubleM.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel usernamesDoubleM=new JPanel();
        usernamesDoubleM.setLayout(new BoxLayout(usernamesDoubleM,BoxLayout.Y_AXIS));
        usernamesDoubleM.setBackground(Color.blue);

        JLabel usernamesLabelDoubleM=new JLabel("Usernames");
        usernamesLabelDoubleM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = usernamesLabelDoubleM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        usernamesLabelDoubleM.setFont(font.deriveFont(attributes));
        usernamesLabelDoubleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamesDoubleM.add(usernamesLabelDoubleM);

        for(int i=1;i<11;i++) {
            JLabel tempUsername;

            tempUsername=new JLabel(highscoresMultiPlayer.get(1).get(i-1).getName());
            tempUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
            usernamesDoubleM.add(Box.createRigidArea(new Dimension(0, 8)));
            usernamesDoubleM.add(tempUsername);
        }
        usernamesDoubleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        DoubleM.add(usernamesDoubleM);
        DoubleM.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel winsDoubleM=new JPanel();
        winsDoubleM.setLayout(new BoxLayout(winsDoubleM,BoxLayout.Y_AXIS));
        winsDoubleM.setBackground(Color.blue);

        JLabel winsLabelDoubleM=new JLabel("Wins");
        winsLabelDoubleM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = winsLabelDoubleM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        winsLabelDoubleM.setFont(font.deriveFont(attributes));
        winsLabelDoubleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        winsDoubleM.add(winsLabelDoubleM);

        for(int i=1;i<11;i++) {
            JLabel tempWins;
            if(highscoresMultiPlayer.get(1).get(i-1).getStepsOrWins()!=0) {
                tempWins=new JLabel(highscoresMultiPlayer.get(1).get(i-1).getStepsOrWins()+"");
            }
            else {
                tempWins=new JLabel("-");
            }
            tempWins.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempWins.setAlignmentX(Component.CENTER_ALIGNMENT);
            winsDoubleM.add(Box.createRigidArea(new Dimension(0, 8)));
            winsDoubleM.add(tempWins);
        }
        winsDoubleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        DoubleM.add(winsDoubleM);

        Context.add(DoubleM);
        Context.add(Box.createRigidArea(new Dimension(15, 0)));

        JPanel TripleM=new JPanel();
        TripleM.setLayout(new BoxLayout(TripleM,BoxLayout.X_AXIS));
        TripleM.setBackground(Color.blue);

        JPanel rankTripleM=new JPanel();
        rankTripleM.setLayout(new BoxLayout(rankTripleM,BoxLayout.Y_AXIS));
        rankTripleM.setBackground(Color.blue);

        JLabel rankLabelTripleM=new JLabel("Rank");
        rankLabelTripleM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = rankLabelTripleM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        rankLabelTripleM.setFont(font.deriveFont(attributes));
        rankLabelTripleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        rankTripleM.add(rankLabelTripleM);

        for(int i=1;i<11;i++) {
            JLabel tempRank=new JLabel(i+".");
            tempRank.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempRank.setAlignmentX(Component.CENTER_ALIGNMENT);
            rankTripleM.add(Box.createRigidArea(new Dimension(0, 8)));
            rankTripleM.add(tempRank);
        }
        rankTripleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        TripleM.add(rankTripleM);
        TripleM.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel usernamesTripleM=new JPanel();
        usernamesTripleM.setLayout(new BoxLayout(usernamesTripleM,BoxLayout.Y_AXIS));
        usernamesTripleM.setBackground(Color.blue);

        JLabel usernamesLabelTripleM=new JLabel("Usernames");
        usernamesLabelTripleM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = usernamesLabelTripleM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        usernamesLabelTripleM.setFont(font.deriveFont(attributes));
        usernamesLabelTripleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamesTripleM.add(usernamesLabelTripleM);

        for(int i=1;i<11;i++) {
            JLabel tempUsername;

            tempUsername=new JLabel(highscoresMultiPlayer.get(2).get(i-1).getName());
            tempUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
            usernamesTripleM.add(Box.createRigidArea(new Dimension(0, 8)));
            usernamesTripleM.add(tempUsername);
        }
        usernamesTripleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        TripleM.add(usernamesTripleM);
        TripleM.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel winsTripleM=new JPanel();
        winsTripleM.setLayout(new BoxLayout(winsTripleM,BoxLayout.Y_AXIS));
        winsTripleM.setBackground(Color.blue);

        JLabel winsLabelTripleM=new JLabel("Wins");
        winsLabelTripleM.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = winsLabelTripleM.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        winsLabelTripleM.setFont(font.deriveFont(attributes));
        winsLabelTripleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        winsTripleM.add(winsLabelTripleM);

        for(int i=1;i<11;i++) {
            JLabel tempWins;
            if(highscoresMultiPlayer.get(2).get(i-1).getStepsOrWins()!=0) {
                tempWins=new JLabel(highscoresMultiPlayer.get(2).get(i-1).getStepsOrWins()+"");
            }
            else {
                tempWins=new JLabel("-");
            }
            tempWins.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempWins.setAlignmentX(Component.CENTER_ALIGNMENT);
            winsTripleM.add(Box.createRigidArea(new Dimension(0, 8)));
            winsTripleM.add(tempWins);
        }
        winsTripleM.setAlignmentX(Component.CENTER_ALIGNMENT);
        TripleM.add(winsTripleM);

        Context.add(TripleM);
        Context.add(Box.createRigidArea(new Dimension(15, 0)));

        this.add(Context);

        JPanel Duel=new JPanel();
        Duel.setLayout(new BoxLayout(Duel,BoxLayout.X_AXIS));
        Duel.setBackground(Color.blue);

        JPanel rankDuel=new JPanel();
        rankDuel.setLayout(new BoxLayout(rankDuel,BoxLayout.Y_AXIS));
        rankDuel.setBackground(Color.blue);

        JLabel rankLabelDuel=new JLabel("Rank");
        rankLabelDuel.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = rankLabelDuel.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        rankLabelDuel.setFont(font.deriveFont(attributes));
        rankLabelDuel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rankDuel.add(rankLabelDuel);

        for(int i=1;i<11;i++) {
            JLabel tempRank=new JLabel(i+".");
            tempRank.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempRank.setAlignmentX(Component.CENTER_ALIGNMENT);
            rankDuel.add(Box.createRigidArea(new Dimension(0, 8)));
            rankDuel.add(tempRank);
        }
        rankDuel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Duel.add(rankDuel);
        Duel.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel usernamesDuel=new JPanel();
        usernamesDuel.setLayout(new BoxLayout(usernamesDuel,BoxLayout.Y_AXIS));
        usernamesDuel.setBackground(Color.blue);

        JLabel usernamesLabelDuel=new JLabel("Usernames");
        usernamesLabelDuel.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = usernamesLabelDuel.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        usernamesLabelDuel.setFont(font.deriveFont(attributes));
        usernamesLabelDuel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernamesDuel.add(usernamesLabelDuel);

        for(int i=1;i<11;i++) {
            JLabel tempUsername;
            tempUsername=new JLabel(highscoresMultiPlayer.get(3).get(i-1).getName());
            tempUsername.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
            usernamesDuel.add(Box.createRigidArea(new Dimension(0, 8)));
            usernamesDuel.add(tempUsername);
        }
        usernamesDuel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Duel.add(usernamesDuel);
        Duel.add(Box.createRigidArea(new Dimension(10, 0)));

        JPanel winsDuel=new JPanel();
        winsDuel.setLayout(new BoxLayout(winsDuel,BoxLayout.Y_AXIS));
        winsDuel.setBackground(Color.blue);

        JLabel winsLabelDuel=new JLabel("Wins");
        winsLabelDuel.setFont(new Font("Calibri", Font.PLAIN, 15));
        font = winsLabelDuel.getFont();
        attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        winsLabelDuel.setFont(font.deriveFont(attributes));
        winsLabelDuel.setAlignmentX(Component.CENTER_ALIGNMENT);
        winsDuel.add(winsLabelDuel);

        for(int i=1;i<11;i++) {
            JLabel tempWins;
            if(highscoresMultiPlayer.get(3).get(i-1).getStepsOrWins()!=0) {
                tempWins=new JLabel(highscoresMultiPlayer.get(3).get(i-1).getStepsOrWins()+"");
            }
            else {
                tempWins=new JLabel("-");
            }
            tempWins.setFont(new Font("Calibri", Font.PLAIN, 15));
            tempWins.setAlignmentX(Component.CENTER_ALIGNMENT);
            winsDuel.add(Box.createRigidArea(new Dimension(0, 8)));
            winsDuel.add(tempWins);
        }
        winsDuel.setAlignmentX(Component.CENTER_ALIGNMENT);
        Duel.add(winsDuel);

        Context.add(Duel);
        Context.add(Box.createRigidArea(new Dimension(15, 0)));

        this.add(Context);
    }
}

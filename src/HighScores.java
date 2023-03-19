/**
 * This class is responsible for storing the information of the high scores into an object and then with Serialization
 * to be put into a file.
 */

import java.io.Serializable;
import java.util.ArrayList;


public class HighScores implements Serializable {
    
    private ArrayList<ArrayList<UserInformation>> highscoresSinglePlayer;
    private ArrayList<ArrayList<UserInformation>> highscoresMultiPlayer;


    public HighScores() {
        
        highscoresSinglePlayer=new ArrayList<>();
        highscoresMultiPlayer=new ArrayList<>();

        for(int i=0;i<3;i++) {
            
            ArrayList<UserInformation> tempArrayListSingle=new ArrayList<>();
            for(int j=0;j<10;j++) {
                UserInformation tempUserInformation=new UserInformation("---------",0);
                tempArrayListSingle.add(tempUserInformation);
            }
            highscoresSinglePlayer.add(i,tempArrayListSingle);

            ArrayList<UserInformation> tempArrayListMulti=new ArrayList<>();
            for(int j=0;j<10;j++) {
                UserInformation tempUserInformation=new UserInformation("---------",0);
                tempArrayListMulti.add(tempUserInformation);
            }
            highscoresMultiPlayer.add(i,tempArrayListMulti);
        }
        ArrayList<UserInformation> tempArrayListMulti=new ArrayList<>();
        for(int j=0;j<10;j++) {
            UserInformation tempUserInformation=new UserInformation("---------",0);
            tempArrayListMulti.add(tempUserInformation);
        }
        highscoresMultiPlayer.add(3,tempArrayListMulti);
    }


    /**
     * This method registers the winners' name and steps into the Single Player highscores.
     * @param username
     * @param steps
     * @param gameType
     */
    public void putScoreInSinglePlayer(String username,int steps,int gameType) {
        
        ArrayList<UserInformation> scoreSinglePlayer = highscoresSinglePlayer.get(gameType-1);
        UserInformation userInfo=new UserInformation(username,steps);

        for(int i=0;i<10;i++) {
            
            if(scoreSinglePlayer.get(i).getName().equals("---------")) {
                scoreSinglePlayer.add(i,userInfo);
                break;
            }

            if(scoreSinglePlayer.get(i).getStepsOrWins()>=steps) {
                scoreSinglePlayer.add(i,userInfo);
                break;
            }
        }
        if(scoreSinglePlayer.size()>10) {
            scoreSinglePlayer.remove(10);
        }
    }


    /**
     * This method registers the winners' name into the Multi Player highscores.
     * @param username
     * @param gameType
     */
    public void putScoreMultiPlayer(String username,int gameType) {
        ArrayList<UserInformation> scoreMultiPlayer = highscoresMultiPlayer.get(gameType-1);

        for(int i=0;i<10;i++) {
            
            System.out.println(scoreMultiPlayer.get(i).getName());
            if(scoreMultiPlayer.get(i).getName().equals(username)) {
                scoreMultiPlayer.get(i).increaseStepsOrWins();
                if(i==0) {
                    break;
                }
                UserInformation tempUserInfo=new UserInformation(scoreMultiPlayer.get(i).getName(),scoreMultiPlayer.get(i).getStepsOrWins());
                scoreMultiPlayer.remove(i);

                for(int j=i-1;j>=0;j--) {
                    if(j==0 && tempUserInfo.getStepsOrWins()>=scoreMultiPlayer.get(j).getStepsOrWins())
                        scoreMultiPlayer.add(0,tempUserInfo);
                    else if(tempUserInfo.getStepsOrWins()>=scoreMultiPlayer.get(j).getStepsOrWins())
                        continue;
                    else {
                        scoreMultiPlayer.add(j+1,tempUserInfo);
                        break;
                    }
                }
                break;
            }
            else if(scoreMultiPlayer.get(i).getName().equals("---------")) {
                highscoresMultiPlayer.get(gameType-1).add(i,new UserInformation(username,1));
                break;
            }
        }
        if(scoreMultiPlayer.size()>10) {
            scoreMultiPlayer.remove(10);
        }
    }


    public ArrayList<ArrayList<UserInformation>> getHighScoresMultiPlayer() {
        return highscoresMultiPlayer;
    }


    public ArrayList<ArrayList<UserInformation>> getHighScoresSinglePlayer() {
        return highscoresSinglePlayer;
    }
}

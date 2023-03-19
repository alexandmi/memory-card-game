/**
 * This object helps to store the high scores information in the HighScores object.
 */

import java.io.Serializable;


public class UserInformation implements Serializable {
    
    private String name;
    private int stepsOrWins;

    public UserInformation(String name,int stepsOrWins) {
        this.name=name;
        this.stepsOrWins=stepsOrWins;
    }

    public void increaseStepsOrWins() { 
        this.stepsOrWins++; 
    }

    public String getName() { 
        return name; 
    }

    public int getStepsOrWins() { 
        return stepsOrWins; 
    }
}
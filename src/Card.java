/**
 * This class represents the memory cards that are being opened and closed on the table.
 */

import javax.swing.*;


public class Card extends JButton {
    
    private ImageIcon countryIcon;
    private ImageIcon worldIcon;
    private ImageIcon blueIcon;
    private int countryName;
    private int coordinates;
    private boolean exist;

    public Card(ImageIcon countryIcon,ImageIcon worldIcon,ImageIcon blueIcon,int countryName) {
        this.countryIcon=countryIcon;
        this.worldIcon=worldIcon;
        this.countryName=countryName;
        this.blueIcon=blueIcon;
        this.setIcon(worldIcon);
        exist=true;
    }


    public void setCoordinates(int coordinates) { 
        this.coordinates=coordinates; 
    }

    public int getCoordinates() { 
        return coordinates; 
    }

    public void openCard() { 
        this.setIcon(countryIcon); 
    }

    public void closeCard() { 
        this.setIcon(worldIcon); 
    }

    /**
     * When a card is removed is not actually removed,it is painted blue and disabled,in order keep the layout of the
     * GUI table intact.
     */
    public void removeCard() {
        this.setIcon(blueIcon);
        exist=false;
    }

    public boolean getExist() {
        return exist;
    }

    public int getCountryName() { 
        return countryName; 
    }
}

/**
 * This class has the object of the popup window,which is used at the game.
 */

import javax.swing.JOptionPane;


public class PopupWindow {

    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}

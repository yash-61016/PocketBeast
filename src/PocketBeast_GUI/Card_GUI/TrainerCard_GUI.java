/*
 * This is a Concrete GUI class that define 
 * extra elements of the Card's GUI
 */
package PocketBeast_GUI.Card_GUI;

import java.awt.Color;
import javax.swing.JLabel;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public class TrainerCard_GUI extends Card_GUI {

    JLabel descriptionLabel;

    /**
     * Creates the GUI for the card
     *
     * @param card The card of which GUI will be created
     */
    public TrainerCard_GUI(Card card) {
        super(card);
        int x = cardSize.width / 2 - 10;
        int y = cardSize.height / 5 - bigFont.getSize();
        x = x / 3;
        x += x * 2;
        y = y + 120;
        manaCostLabel.setBounds(x, y, 50, 50);
        cardPanel.setBackground(Color.decode("#63C5DA"));
    }

}

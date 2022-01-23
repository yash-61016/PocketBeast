/*
 * This is a Concrete GUI class that define 
 * extra elements of the Card's GUI
 */
package PocketBeast_GUI.Card_GUI;

import java.awt.Color;
import javax.swing.JLabel;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;

/**
 *
 * @author Yash Patel
 */
public class BeastCard_GUI extends Card_GUI {

    JLabel attackLabel;
    JLabel healthLabel;

    /**
     * Creates the GUI for the card
     *
     * @param card The card of which GUI will be created
     */
    public BeastCard_GUI(BeastCard card) {
        super(card);
        attackLabel = new JLabel();
        attackLabel.setText(String.valueOf(card.getAttack()));
        attackLabel.setFont(bigFont);
        int x = cardSize.width / 2 - 10;
        int y = cardSize.height / 5 - bigFont.getSize();
        x = x / 3;
        x += x * 2;
        y = y + 120;
        attackLabel.setBounds(x, y, 50, 50);
        cardPanel.add(attackLabel);
        healthLabel = new JLabel();
        healthLabel.setText(String.valueOf(card.getHealth()));
        healthLabel.setFont(bigFont);
        x = x * 2 - 20;
        healthLabel.setBounds(x, y, 50, 50);
        cardPanel.add(healthLabel);
        cardPanel.setBackground(Color.decode("#EFFD5F"));
    }

    /**
     *
     * @param health Represents the health of the of the Card's GUI
     */
    public void setHealth(int health) {
        attackLabel.setText(String.valueOf(health));
    }

}

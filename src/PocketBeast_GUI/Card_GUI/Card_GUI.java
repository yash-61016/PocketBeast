/*
 * This is an abstract class which defines basic 
 * GUI structure of the Card's GUI
 */
package PocketBeast_GUI.Card_GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public abstract class Card_GUI extends JPanel {

    JPanel cardPanel;
    JLabel idLabel;
    JLabel nameLabel;
    JLabel manaCostLabel;
    Font bigFont;
    Rectangle cardSize;
    Color foreground;

    /**
     * Creates the GUI for the card
     *
     * @param card The card of which GUI will be created
     */
    public Card_GUI(Card card) {
        this.foreground = Color.BLACK;
        this.cardSize = new Rectangle(50, 50, 170, 200);
        this.bigFont = new Font("TimesRoman", Font.BOLD, 22);
        this.cardPanel = new JPanel();

        cardPanel.setBounds(cardSize);
        cardPanel.setForeground(foreground);
        cardPanel.setLayout(null);

        idLabel = new JLabel();
        idLabel.setText(card.getId());
        idLabel.setBackground(Color.yellow);
        idLabel.setFont(bigFont);
        int x = cardSize.width / 2 - 10;
        int y = cardSize.height / 7 - bigFont.getSize();
        idLabel.setBounds(x, y, 50, 50);

        manaCostLabel = new JLabel();
        manaCostLabel.setText(String.valueOf(card.getManaCost()));
        manaCostLabel.setBackground(Color.yellow);
        manaCostLabel.setFont(bigFont);
        x = x / 3;
        y = y + 130;
        manaCostLabel.setBounds(x, y, 50, 50);

        nameLabel = new JLabel();
        nameLabel.setText("<html>" + card.getName() + "</html>");
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setFont(bigFont);
        x = 10;
        y = idLabel.getY() + 50;
        nameLabel.setBounds(x, y, cardPanel.getWidth(), 100);

        cardPanel.add(idLabel);
        cardPanel.add(nameLabel);
        cardPanel.add(manaCostLabel);

    }

    /**
     * Gets the GUI created in the constructor
     *
     * @return A JPanel representing the Card's GUI
     */
    public JPanel getCardGui() {
        return this.cardPanel;
    }

    /**
     * Sets coordinates of the cardPanel, which later will be added to the main
     * Panel
     *
     * @param x Represents x coordinates
     * @param y Represents y coordinates
     */
    public void setCardCordinates(int x, int y) {
        this.cardPanel.setBounds(x, y, 170, 200);
    }

}

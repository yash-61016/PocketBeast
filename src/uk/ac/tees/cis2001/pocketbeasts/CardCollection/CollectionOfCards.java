/*
 * This is an abstract class which defines arraylist of cards
 * and some basic methods
 */
package uk.ac.tees.cis2001.pocketbeasts.CardCollection;

import java.io.Serializable;
import java.util.ArrayList;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public abstract class CollectionOfCards implements Serializable {

    protected ArrayList<Card> CardList;

    /**
     * Gets the arrayList of cards
     *
     * @return A ArrayList representing ArrayList of cards
     */
    public ArrayList<Card> getCards() {
        return this.CardList;
    }

    /**
     * Adds specified card in the arrayList of cards
     *
     * @param card The card to be added in the arrayList of cards
     */
    public void add(Card card) {
        this.CardList.add(card);
    }

    /**
     * Removes specified card from the arrayList of cards
     *
     * @param card The card to be removed from the arrayList of cards
     */
    public void remove(Card card) {
        this.CardList.remove(card);
    }

    /**
     * Removes specifies cards from the arrayList of cards
     *
     * @param cards A ArrayList of cards representing the cards to be removed
     * from the arrayList
     */
    public void removeAll(ArrayList<Card> cards) {
        this.CardList.removeAll(cards);
    }

}

/*
 * This file is part of PocketBeasts.
 *
 * PocketBeasts is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PocketBeasts is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
 */
package uk.ac.tees.cis2001.pocketbeasts.CardCollection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public class Deck extends CollectionOfCards {

    /**
     * Creates a new Deck of cards with specified cards
     *
     * @param cards A ArrayList of cards which needs to be added in the Deck
     */
    public Deck(ArrayList<Card> cards) {
        this.CardList = cards;
    }

    /**
     * Counts how many cards are available in the deck
     *
     * @return A integer representing size of the deck
     */
    public int count() {
        return this.CardList.size();
    }

    /**
     * Gets a card from the Deck, and removes it from the deck
     *
     * @return A Card representing the card from index 0 of the deck
     */
    public Card draw() {
        Card card = this.CardList.get(0);
        this.CardList.remove(0);
        return card;
    }

    /**
     * Mixes all the cards in the Deck
     */
    public void shuffle() {
        Collections.shuffle(this.CardList);
    }
}

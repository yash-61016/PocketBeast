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
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public class InPlay extends CollectionOfCards implements Serializable {

    /**
     * Creates a new InPlay with empty arrayList of cards
     */
    public InPlay() {
        this.CardList = new ArrayList<>();
    }

    /**
     * Gets the card on the specified index
     *
     * @param index The index of which card needed
     * @return A Card representing the card on the specified index from the
     * InPlay
     */
    public Card getCard(int index) {
        return CardList.get(index);
    }

    /**
     * Counts how many cards are available in the hand
     *
     * @return A integer representing size of the hand
     */
    public int count() {
        return this.CardList.size();
    }
}

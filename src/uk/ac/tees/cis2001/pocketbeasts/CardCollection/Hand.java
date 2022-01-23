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
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import java.util.ArrayList;
import java.util.Collections;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public class Hand extends CollectionOfCards {

    /**
     * Creates a new Hand with an empty arrayList of cards
     */
    public Hand() {
        this.CardList = new ArrayList<>();
    }

    @Override
    public void add(Card card) {
        this.CardList.add(card);
        this.sort();
    }

    /**
     * Counts how many cards are available in the hand
     *
     * @return A integer representing size of the hand
     */
    public int count() {
        return this.CardList.size();
    }

    /**
     * Rearranges all the cards in the hand in ascending order of their
     * mana-cost
     */
    public void sort() {
        // this sorting algorithm uses Bubble sort algorithm
        // to sort all the cards in ascending order of their mana-cost
        int n = this.CardList.size(); // size of hand
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Card card1 = CardList.get(j);
                Card card2 = CardList.get(j + 1);
                if (card1.getManaCost() > card2.getManaCost()) {
                    Card temp = CardList.get(j);
                    CardList.set(j, CardList.get(j + 1));
                    CardList.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Gets current details of the hand
     *
     * @return A String representing details of the hand
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.count(); i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");

        for (Card card : this.CardList) {
            sb.append(String.format("|%7d| ", card.getManaCost()));
        }
        sb.append("\n");

        for (Card card : this.CardList) {
            sb.append(String.format("|  %-5s| ", card.getId()));
        }
        sb.append("\n");

        for (int i = 0; i < this.count(); i++) {
            sb.append("|       | ");
        }
        sb.append("\n");
        for (Card card : this.CardList) {
            if (card.getClass().getSuperclass() == BeastCard.class) {
                sb.append(String.format("|%-2d %4d| ", ((BeastCard) card).getAttack(), ((BeastCard) card).getHealth()));
            } else {
                sb.append("       ");
            }
        }

        sb.append("\n");

        for (int i = 0; i < this.count(); i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");

        return sb.toString();
    }

}

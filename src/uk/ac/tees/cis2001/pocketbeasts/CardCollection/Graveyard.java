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

/**
 *
 * @author Yash Patel
 */
public class Graveyard extends CollectionOfCards {

    /**
     * Creates a new Graveyard with an empty ArrayList
     */
    public Graveyard() {
        this.CardList = new ArrayList<>();
    }

    /**
     * Counts how many cards in the graveyard
     *
     * @return A integer representing number of cards in the graveyard
     */
    public int count() {
        return this.CardList.size();
    }
}

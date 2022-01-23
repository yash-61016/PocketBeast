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
package uk.ac.tees.cis2001.pocketbeasts;

/**
 *
 * @author Yash Patel
 */
public interface Card {

    /**
     * Gets the ID of the card
     *
     * @return A String representing ID of the card
     */
    public String getId();

    /**
     * Gets the name of the card
     *
     * @return A String representing name of the card
     */
    public String getName();

    /**
     * Gets the mana cost of the card
     *
     * @return A integer representing mana cost of the card
     */
    public int getManaCost();

    /**
     * Gets the description of the card
     *
     * @return A String representing details of the card
     */
    @Override
    public String toString();
}

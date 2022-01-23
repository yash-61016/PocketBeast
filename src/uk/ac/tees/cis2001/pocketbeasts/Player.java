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

import java.io.Serializable;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.InPlay;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.Graveyard;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.Hand;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.Deck;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;

/**
 *
 * @author Yash Patel
 */
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private final int MAX_MANA = 9;
    private final String name;
    private int manaAvailable;
    private int manaTicker;
    private int health;
    private final Deck deck;
    private final Hand hand;
    private InPlay inPlay;
    private Graveyard graveyard;

    /**
     * Creates player with specified name, deck and sets other properties of the
     * new player
     *
     * @param name The player's name
     * @param deck The player's initial deck
     */
    public Player(String name, Deck deck) {
        this.name = name;
        this.manaAvailable = 0;
        this.manaTicker = 0;
        this.health = 1;
        this.deck = deck;
        this.hand = new Hand();
        this.inPlay = new InPlay();
        this.graveyard = new Graveyard();
    }

    /**
     * Gets the name of the player
     *
     * @return A String representing name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the mana available of the player
     *
     * @return A integer representing available mana of the player
     */
    public int getManaAvailable() {
        return this.manaAvailable;
    }

    /**
     * Gets the health of the player
     *
     * @return A integer representing available health of the player
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Gets the deck of the player
     *
     * @return A Deck representing deck of the player
     */
    public Deck getDeck() {
        return this.deck;
    }

    /**
     * Gets the hand of the player
     *
     * @return A Hand representing hand of the player
     */
    public Hand getHand() {
        return this.hand;
    }

    /**
     * Gets the in-play cards of the player
     *
     * @return A InPlay representing in play cards of the player
     */
    public InPlay getInPlay() {
        return this.inPlay;
    }

    /**
     * Gets the dead/used cards of the player
     *
     * @return A Graveyard representing graveyard of the player
     */
    public Graveyard getGraveyard() {
        return this.graveyard;
    }

    /**
     * Adds 4 random cards in player's hand from the deck to start with in a new
     * game
     */
    public void newGame() {
        this.deck.shuffle();
        for (int i = 0; i < 4; i++) {
            this.hand.add(this.deck.draw());
        }
    }

    /**
     * Increases mana by one each turn and replenishes in full
     */
    public void addMana() {
        if (this.manaTicker < this.MAX_MANA) {
            this.manaTicker++;
        }
        this.manaAvailable = manaTicker;
    }

    /**
     * Deducts mana from the player's available mana
     *
     * @param amount The amount of mana to be deducted
     */
    public void useMana(int amount) {
        this.manaAvailable -= amount;
    }

    /**
     * Removes a card from deck and adds that to the hand
     */
    public void drawCard() {
        this.hand.add(this.deck.draw());
    }

    /**
     * Deducts some amount from the health of the player
     *
     * @param amount The amount of health to be deducted
     * @return A Boolean, ture if health is below 0, else false
     */
    public Boolean damage(int amount) {
        this.health -= amount;
        return this.health <= 0;
    }

    /**
     * Removes all the cards from old in play of player and adds all cards from
     * the new in play specified
     *
     * @param inPlay The inPlay from which cards will be added
     */
    public void setInPlay(InPlay inPlay) {
        this.inPlay.removeAll(this.inPlay.getCards());
        for (Card card : inPlay.getCards()) {
            this.inPlay.add(card);
        }
    }

    /**
     * Sets graveyard of the player
     *
     * @param graveyard The graveyard to be set
     */
    public void setGraveyard(Graveyard graveyard) {
        this.graveyard = graveyard;
    }

    /**
     * Gets description/details of the player's current state
     *
     * @return A String representing details of the player's state
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-9s HEALTH/%-5d MANA/%d\n", this.name, this.health, this.manaAvailable));

        for (int i = 0; i < this.inPlay.count() + 2; i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");

        for (int i = 0; i < 2; i++) {
            sb.append("|       | ");
        }
        for (int i = 0; i < this.inPlay.count(); i++) {
            sb.append(String.format("|%7d| ", this.inPlay.getCard(i).getManaCost()));
        }
        sb.append("\n");

        sb.append("| DECK  | ");
        sb.append("| GRAVE | ");
        for (int i = 0; i < this.inPlay.count(); i++) {
            sb.append(String.format("|  %-5s| ", this.inPlay.getCard(i).getId()));
        }
        sb.append("\n");

        sb.append(String.format("| %-6d| ", this.deck.count()));
        sb.append(String.format("| %-6d| ", this.graveyard.count()));
        for (int i = 0; i < this.inPlay.count(); i++) {
            sb.append("|       | ");
        }
        sb.append("\n");

        for (int i = 0; i < 2; i++) {
            sb.append("|       | ");
        }
        for (int i = 0; i < this.inPlay.count(); i++) {
            if (this.inPlay.getCard(i).getClass().getSuperclass() == BeastCard.class) {
                BeastCard card = (BeastCard) this.inPlay.getCard(i);
                sb.append(String.format("|%-2d %4d| ", card.getAttack(), card.getHealth()));
            } else {
                sb.append("       ");
            }
        }

        sb.append("\n");

        for (int i = 0; i < this.inPlay.count() + 2; i++) {
            sb.append("+-------+ ");
        }
        sb.append("\n");
        sb.append(String.format("%d card(s) in hand.\n", this.hand.count()));
        sb.append("\n");

        sb.append(this.hand.toString());

        return sb.toString();
    }
}

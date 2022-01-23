/*
 * This is an abstract class which defines arraylist of cards
 * and some basic methods
 */
package uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards;

import java.io.Serializable;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public abstract class BeastCard implements Card, Comparable<Card>, Serializable {

    private final String id;
    private final String name;
    private final int manaCost;
    private int attack;
    private int health;

    /**
     * Created a new BeastCard with specified details
     *
     * @param id The Id of the card
     * @param name The Name of the card
     * @param manaCost The ManaCost of the card
     * @param attack The Attack power of the card
     * @param health The Health of the card
     */
    public BeastCard(String id, String name, int manaCost, int attack, int health) {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
        this.attack = attack;
        this.health = health;
    }

    /**
     * Creates a new BeastCard with details from specified BeastCard
     *
     * @param beastCard The BeastCard to get details of
     */
    public BeastCard(BeastCard beastCard) {
        this.id = beastCard.getId();
        this.name = beastCard.getName();
        this.manaCost = beastCard.getManaCost();
        this.attack = beastCard.getAttack();
        this.health = beastCard.getHealth();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getManaCost() {
        return this.manaCost;
    }

    /**
     * Gets the attack power of card
     *
     * @return A integer representing attack power of the card
     */
    public int getAttack() {
        return attack;

    }

    /**
     * Sets the attack power of the card
     *
     * @param attack Integer containing new attack power
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Gets the health of card
     *
     * @return A integer representing health of the card
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets new health of the card
     *
     * @param health Integer containing new health amount
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Deducts specified amount from the health of the card
     *
     * @param amount Integer containing the amount to be deducted from the
     * health of card
     */
    public void damage(int amount) {
        health -= amount;
    }

    /**
     * Update card's health and attack values with specified card
     *
     * @param card Card containing health and attack value to be updated
     */
    public void updateCard(BeastCard card) {
        setHealth(card.getHealth());
        setAttack(card.getAttack());
    }

    @Override
    public String toString() {
        return this.name + " (" + this.id + ") Mana Cost/" + this.manaCost
                + " Attack/" + this.attack + " Health/" + this.health;
    }

    /**
     * Compares two cards mana-costs
     *
     * @param card Card to be compared
     * @return This method returns Zero if this card's and given card's
     * mana-cost is equal, if this card's mana-cost is less than given card's
     * mana-cost then returns a value less than zero, if this card's mana-cost
     * is greater than given card's mana-cost then returns a value greater than
     * zero
     */
    @Override
    public int compareTo(Card card) {
        return Integer.compare(this.getManaCost(), card.getManaCost());
    }
}

/*
 * This is an abstract class which defines arraylist of cards
 * and some basic methods
 */
package uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import java.io.Serializable;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public abstract class TrainerCard implements Card, Serializable {

    private final String id;
    private final String name;
    private final int manaCost;

    /**
     * Created a new TrainerCard with specified details
     *
     * @param id The Id of the card
     * @param name The Name of the card
     * @param manaCost The ManaCost of the card
     */
    public TrainerCard(String id, String name, int manaCost) {
        this.id = id;
        this.name = name;
        this.manaCost = manaCost;
    }

    /**
     * Creates a new TrainerCard with details from specified TrainerCard
     *
     * @param trainerCard The TrainerCard to get details of
     */
    public TrainerCard(TrainerCard trainerCard) {
        this.id = trainerCard.getId();
        this.name = trainerCard.getName();
        this.manaCost = trainerCard.getManaCost();
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

    @Override
    public String toString() {
        return this.name + " (" + this.id + ") Mana Cost/" + this.manaCost;
    }

    /**
     * This is a abstract method by which special power of the TrainerCard can
     * be used on the specified card
     *
     * @param card A Card on which the special power will be used
     */
    public abstract void useSpecialPower(BeastCard card);
}

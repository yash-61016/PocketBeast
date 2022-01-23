/*
 * This is a  Abstract Factory class interface which
 * declares a set of methods for 
 * creating each of the abstract Cards.
 */
package AbstractFactory;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.TrainerCard;

/**
 *
 * @author Yash Patel
 */
public interface CardFactory {

    /**
     * Creates all cards of a similar type
     *
     * @return A Array of BeastCard representing a array of cards of a specific
     * type
     */
    public BeastCard[] createBeastCard();

    /**
     * Creates all cards of a similar type
     *
     * @return A Array of TrainerCard representing a array of cards of a
     * specific type
     */
    public TrainerCard[] createTrainerCard();
}

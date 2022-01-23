/*
 * This is a Concrete Decorator class that define extra behaviors that can be 
 * added to components dynamically. 
 * This class is to add Health behaviour to the card
 */
package DecoratorPattern;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;

/**
 *
 * @author Yash Patel
 */
public class HealthDecorator extends CardDecorator {

    /**
     * Creates a decorator around the BeastCard
     *
     * @param card The BeastCard to be decorated which is passed to super class
     */
    public HealthDecorator(BeastCard card) {
        super(card);
    }

    /**
     * Gets the Health of the card
     *
     * @return A integer representing the Card's new Health 
     */
    public int getHealth() {
        return card.getHealth() + increaseHealth();
    }

    /**
     * Increases the power of the Health of the card
     *
     * @return A integer representing the Health to be increased
     */
    private int increaseHealth() {
        return 2;
    }
}

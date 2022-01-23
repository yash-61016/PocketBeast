/*
 * This is a Concrete Decorator class that define extra behaviors that can be 
 * added to components dynamically. 
 * This class is to add Attack behaviour to the card
 */
package DecoratorPattern;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;

/**
 *
 * @author Yash Patel
 */
public class AttackDecorator extends CardDecorator {

    /**
     * Creates a decorator around the BeastCard
     *
     * @param card The BeastCard to be decorated which is passed to super class
     */
    public AttackDecorator(BeastCard card) {
        super(card);
    }

    /**
     * Gets the attack of the card
     *
     * @return A integer representing the Card's attack power
     */
    public int getAttack() {
        return card.getAttack() + increaseAttack();
    }

    /**
     * Increases the power of the attack of the card
     *
     * @return A integer representing the power to be increased
     */
    private int increaseAttack() {
        return 2;
    }
}

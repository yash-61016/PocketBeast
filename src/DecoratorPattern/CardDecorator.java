/*
 * This is  Base Decorator class has a field for referencing a wrapped object.
 * The field’s type should be declared as the component interface so it 
 * can contain both concrete components and decorators. 
 * The base decorator delegates all operations to the wrapped object. 
 */
package DecoratorPattern;

import uk.ac.tees.cis2001.pocketbeasts.Card;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;

/**
 *
 * @author Yash Patel
 */
public abstract class CardDecorator implements Card{
    protected BeastCard card;
    
    /**
     * Creates a decorator around the BeastCard
     * @param card The BeastCard to be decorated
     */
    public CardDecorator(BeastCard card){
        this.card = card;
    }
    
    /**
     * Gets the id of the card
     * @return A string representing the Card's ID
     */
    @Override
    public String getId(){
           return this.card.getId();
       }
    
    /**
     * Gets the name of the card
     * @return  A string representing the Card's Name
     */
    @Override
    public String getName(){
        return this.card.getName();
    }
    
    /**
     * Gets the mana cost of the card
     * @return A integer representing the Card's mana cost
     */
    @Override
    public int getManaCost(){
        return this.card.getManaCost();
    }
    
    /**
     * Gets the description of the card
     * @return A String representing the Card's details
     */
    @Override
    public String toString(){
        return this.card.toString();
    }
    
    
}

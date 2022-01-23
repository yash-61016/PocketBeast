/*
 * The Memento is a value object that acts as a 
 * snapshot of the Card’s state. 
 */
package uk.ac.tees.cis2001.pocketbeasts.MementoPattern;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;

/**
 *
 * @author Yash Patel
 */
public class CardMemento {
    
    private BeastCard beastCard;
    private int health;
    
    CardMemento(BeastCard beastCard){
        this.beastCard = beastCard;
        this.health = this.beastCard.getHealth();
    }
    public BeastCard restoreBeastCard(){
        this.beastCard.setHealth(health);
        return this.beastCard;
    }
}

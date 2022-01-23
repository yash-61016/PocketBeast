/*
 * The Originator class can produce snapshots of its own state,
 * as well as restore its state from snapshots when needed.
 */
package uk.ac.tees.cis2001.pocketbeasts.MementoPattern;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;

/**
 *
 * @author Yash Patel
 */
public class CardOrignator {
    private BeastCard beastCard;
    
    public void setBeastCard(BeastCard beastCard){
        this.beastCard = beastCard;
    }
    public BeastCard getBeastCard(){
        return this.beastCard;
    }
    public CardMemento saveCardStateToMemento(){
        return new CardMemento(beastCard);
    }
    public void getCardStateFromMemento(CardMemento cardMemento){
        beastCard = cardMemento.restoreBeastCard();
    }
}

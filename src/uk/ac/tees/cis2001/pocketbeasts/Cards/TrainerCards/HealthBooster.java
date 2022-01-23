/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import uk.ac.tees.cis2001.pocketbeasts.Card;
import DecoratorPattern.HealthDecorator;

/**
 *
 * @author Yash Patel
 */
public class HealthBooster extends TrainerCard{

    public HealthBooster(String id, String name, int manaCost) {
        super(id, name, manaCost);
    }

    @Override
    public void useSpecialPower(BeastCard card) {
         card.setHealth(new HealthDecorator(card).getHealth());
    }
    
}

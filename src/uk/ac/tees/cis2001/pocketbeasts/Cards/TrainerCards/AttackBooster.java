/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards;

import DecoratorPattern.AttackDecorator;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;


/**
 *
 * @author Yash Patel
 */
public class AttackBooster extends TrainerCard{

    public AttackBooster(String id, String name, int manaCost) {
        super(id, name, manaCost);
    }

    @Override
    public void useSpecialPower(BeastCard card) {
         card.setAttack(new AttackDecorator(card).getAttack());
    }
    
}

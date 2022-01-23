/*
 * This is a Concrete Factory class which implement creation methods of the abstract factory.
 * Each concrete factory corresponds to a specific variant of Card and
 * creates only those Card variants.
 * This class creates all spell type trainerCards
 */
package AbstractFactory;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.AttackBooster;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.TrainerCard;

/**
 *
 * @author Yash Patel
 */
public class SpellTrainer implements CardFactory{

    @Override
    public BeastCard[] createBeastCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TrainerCard[] createTrainerCard() {
        AttackBooster attackBooster = new AttackBooster("AB", "Attack Booster", 2);
        TrainerCard[] trainerCards = {attackBooster};
       return trainerCards;
    }
    
}

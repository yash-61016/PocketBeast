/*
 * This is a Concrete Factory class which implement creation methods of the abstract factory.
 * Each concrete factory corresponds to a specific variant of Card and
 * creates only those Card variants.
 * This class creates all healing type trainerCards
 */
package AbstractFactory;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import AbstractFactory.CardFactory;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.HealthBooster;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.TrainerCard;

/**
 *
 * @author Yash Patel
 */
public class HealingTrainer implements CardFactory{

    @Override
    public BeastCard[] createBeastCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TrainerCard[] createTrainerCard() {
        HealthBooster healthBooster = new HealthBooster("HB", "Health Booster", 2);
        TrainerCard[] trainerCards = {healthBooster};
        return trainerCards;
    }
    
}

/*
 * This is a Concrete Factory class which implement creation methods of the abstract factory.
 * Each concrete factory corresponds to a specific variant of Card and
 * creates only those Card variants.
 * This class creates all Ground type beasts
 */
package AbstractFactory;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.*;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.TrainerCard;

/**
 *
 * @author Yash Patel
 */
public class GroundBeast implements CardFactory{

    @Override
    public BeastCard[] createBeastCard() {
        HighlandTiger ht = new HighlandTiger();
        EnormousElephant ep = new EnormousElephant();
        HulkyGorilla gp = new HulkyGorilla();
        BeastCard[] beastCards = {gp, ht, ep};
        return beastCards;
    }

    @Override
    public TrainerCard[] createTrainerCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

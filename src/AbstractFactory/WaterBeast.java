/*
 * This is a Concrete Factory class which implement creation methods of the abstract factory.
 * Each concrete factory corresponds to a specific variant of Card and
 * creates only those Card variants.
 * This class creates all Water type beasts
 */
package AbstractFactory;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.*;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.TrainerCard;

/**
 *
 * @author Yash Patel
 */
public class WaterBeast implements CardFactory{

    @Override
    public BeastCard[] createBeastCard() {
        FlipperShark sh = new FlipperShark();
        BrawnyCrocodile cd = new BrawnyCrocodile();
        BeastCard[] beastCards = {sh, cd};
        return beastCards;
    }

    @Override
    public TrainerCard[] createTrainerCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

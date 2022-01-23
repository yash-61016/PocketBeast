/*
 * This is a Concrete Factory class which implement creation methods of the abstract factory.
 * Each concrete factory corresponds to a specific variant of Card and
 * creates only those Card variants.
 *  This class creates all the Air type Beasts
 */
package AbstractFactory;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.KestrelFalcon;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.PerspicaciousEagle;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.PredaciousVulture;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.TrainerCard;

/**
 *
 * @author Yash Patel
 */
public class AirBeast implements CardFactory{


    @Override
    public BeastCard[] createBeastCard() {
        KestrelFalcon fc = new KestrelFalcon();
        PredaciousVulture vc = new PredaciousVulture();
        PerspicaciousEagle eg = new PerspicaciousEagle();
        BeastCard[] beastCards = {fc, vc, eg};
        return beastCards;
    }

    @Override
    public TrainerCard[] createTrainerCard() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

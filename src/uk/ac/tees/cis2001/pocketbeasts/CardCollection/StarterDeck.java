/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2001.pocketbeasts.CardCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import AbstractFactory.GroundBeast;
import AbstractFactory.WaterBeast;
import AbstractFactory.AirBeast;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import AbstractFactory.HealingTrainer;
import AbstractFactory.SpellTrainer;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.TrainerCard;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public class StarterDeck {

    StarterDeck() {
    }

    /**
     * Gets a starter Deck
     *
     * @return A ArrayList of Card
     */
    public static ArrayList<Card> getStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<>();

        // adding diffrent types of cards
        BeastCard[] groundBeasts = new GroundBeast().createBeastCard();
        BeastCard[] airBeasts = new AirBeast().createBeastCard();
        BeastCard[] waterBeasts = new WaterBeast().createBeastCard();
        TrainerCard[] spellCards = new SpellTrainer().createTrainerCard();
        TrainerCard[] healingCards = new HealingTrainer().createTrainerCard();

        //adding diffrent types of cards to the starterDeck
        starterDeck.addAll(Arrays.asList(groundBeasts));
        starterDeck.addAll(Arrays.asList(airBeasts));
        starterDeck.addAll(Arrays.asList(waterBeasts));
        starterDeck.addAll(Arrays.asList(spellCards));
        starterDeck.addAll(Arrays.asList(healingCards));

        //mixing all the cards
        Collections.shuffle(starterDeck);
        return starterDeck;
    }
}

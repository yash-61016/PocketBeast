/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2001.pocketbeasts.CardCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import AbstractFactory.AirBeast;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import AbstractFactory.GroundBeast;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.HighlandTiger;
import AbstractFactory.WaterBeast;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public class DeckTest {
    ArrayList<Card> starterDeck;
    
    public DeckTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Testing Deck class...");
    }
    
    @Before
    public void setUp() {
        starterDeck = new ArrayList<>();
        BeastCard[] groundBeasts = new GroundBeast().createBeastCard();
        BeastCard[] airBeasts = new AirBeast().createBeastCard();
        BeastCard[] waterBeasts = new WaterBeast().createBeastCard();
        
        for (int i = 0; i < 2; i++) {
            starterDeck.addAll(Arrays.asList(groundBeasts));
            starterDeck.addAll(Arrays.asList(airBeasts));
            starterDeck.addAll(Arrays.asList(waterBeasts));
        }
    }
    
    @After
    public void tearDown() {
        starterDeck = null;
    }

    /**
     * Test of count method, of class Deck.
     */
//    @Ignore
    @Test
    public void testCount() {
        System.out.println(" - Testing the Count method");
        Deck instance = new Deck(starterDeck);
        int expResult = 16;
        int result = instance.count();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of count method after drawing out one card.
     */
//    @Ignore
    @Test
    public void testCountAfterDraw() {
        System.out.println(" - Testing the Count method after drawing one card");
        Deck instance = new Deck(starterDeck);
        instance.draw();
        int expResult = 15;
        int result = instance.count();
        assertEquals(expResult, result);
    }

    /**
     * Test of draw method, of class Deck.
     */
//    @Ignore
    @Test
    public void testDraw() {
        System.out.println(" - Testing Draw method");
        Deck instance = new Deck(starterDeck);
        Card expResult = new GroundBeast().createBeastCard()[0];
        Card result = instance.draw();
        assertEquals(expResult.getClass(), result.getClass());
    }
   
    /**
     * Test of add method, of abstract class CollectionOfCards.
     */
//    @Ignore
    @Test
    public void testAdd() {
        System.out.println(" - Testing Add method");
        starterDeck = new ArrayList<>();
        Deck instance = new Deck(starterDeck);
        GroundBeast groundbeast = new GroundBeast();
        instance.add(groundbeast.createBeastCard()[0]);
        Card expResult = groundbeast.createBeastCard()[0];
        Card result = instance.draw();
        assertEquals(expResult.getClass(), result.getClass());
    }
    
 /**
     * Test of remove method, of abstract class CollectionOfCards.
     */
//    @Ignore
    @Test
    public void testRemove() {
        System.out.println(" - Testing Remove method");
        starterDeck = new ArrayList<>();
        Deck instance = new Deck(starterDeck);
        BeastCard[] groundbeasts = new GroundBeast().createBeastCard();
        
        instance.add(groundbeasts[0]);
        instance.add(groundbeasts[1]);
        
        instance.remove(groundbeasts[0]);
        
        int expResult = 1;
        int result = instance.count();
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of remove all method, of abstract class CollectionOfCards.
     */
//    @Ignore
    @Test
    public void testRemoveAll() {
        System.out.println(" - Testing RemoveAll method");
        Deck instance = new Deck(starterDeck);

        instance.removeAll(starterDeck);
        
        int expResult = 0;
        int result = instance.count();
        
        assertEquals(expResult, result);
    }
        
     @AfterClass
    public static void tearDownClass() {
         System.out.println("Finished testing Deck class.");
         System.out.println();
    }
    
}

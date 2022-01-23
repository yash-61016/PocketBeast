/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2001.pocketbeasts.CardCollection;

import java.util.ArrayList;
import java.util.Arrays;
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
import AbstractFactory.WaterBeast;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public class InPlayTest {
    ArrayList<Card> starterDeck;
    public InPlayTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Testing InPlay class...");
    }
    @Before
    public void setUp() {
        starterDeck = new ArrayList<>();
        BeastCard[] groundBeasts = new GroundBeast().createBeastCard();
        starterDeck.addAll(Arrays.asList(groundBeasts));
    }
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Fininshed testing Hand class.");
        System.out.println();
    }
    

    /**
     * Test of getCard method, of class InPlay.
     */
//    @Ignore
    @Test
    public void testGetCard() {
        System.out.println(" - Testing getCard method");
        InPlay instance = new InPlay();
        instance.CardList = starterDeck;
        Card expResult = starterDeck.get(2);
        Card result = instance.getCard(2);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of count method, of class InPlay.
     */
//    @Ignore
    @Test
    public void testCount() {
        System.out.println(" - Testing Cound method");
        InPlay instance = new InPlay();
        instance.CardList = starterDeck;
        int expResult = 3;
        int result = instance.count();
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of add method, of abstract class CollectionOfCards.
     */
//    @Ignore
    @Test
    public void testAdd() {
        System.out.println(" - Testing Add method");
        starterDeck = new ArrayList<>();
        InPlay instance = new InPlay();
        GroundBeast groundbeast = new GroundBeast();
        instance.add(groundbeast.createBeastCard()[0]);
        Card expResult = groundbeast.createBeastCard()[0];
        Card result = instance.getCard(0);
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
        InPlay instance = new InPlay();
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
        InPlay instance = new InPlay();

        instance.removeAll(starterDeck);
        
        int expResult = 0;
        int result = instance.count();
        
        assertEquals(expResult, result);
    }
        
    
}

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
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import AbstractFactory.GroundBeast;
import uk.ac.tees.cis2001.pocketbeasts.Card;

/**
 *
 * @author Yash Patel
 */
public class GraveyardTest {
    ArrayList<Card> starterDeck;
    public GraveyardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Testing Graveyard Class...");
    }
    /**
     * Test of count method, of class Graveyard.
     */
//    @Ignore
    @Test
    public void testCount() {
        System.out.println(" - Testing Count method");
        Graveyard instance = new Graveyard();
        int expResult = 0;
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
        Graveyard instance = new Graveyard();
        GroundBeast groundbeast = new GroundBeast();
        instance.add(groundbeast.createBeastCard()[0]);
        int expResult = instance.count();
        int result = 1;
        assertEquals(expResult, result);
    }
    
 /**
     * Test of remove method, of abstract class CollectionOfCards.
     */
    //@Ignore
    @Test
    public void testRemove() {
        System.out.println(" - Testing Remove method");
        starterDeck = new ArrayList<>();
        Graveyard instance = new Graveyard();
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
        starterDeck = new ArrayList<>();
        BeastCard[] groundBeasts = new GroundBeast().createBeastCard();
        starterDeck.addAll(Arrays.asList(groundBeasts));
        
        Graveyard instance = new Graveyard();
        for(Card card : starterDeck){
            instance.add(card);
        }
        
        instance.removeAll(starterDeck);
        
        int expResult = 0;
        int result = instance.count();
        
        assertEquals(expResult, result);
    }
     
     @AfterClass
    public static void tearDownClass() {
        System.out.println("Finished testing Graveyard class.");
         System.out.println();
    }
    
    
}

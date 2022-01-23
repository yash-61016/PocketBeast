/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2001.pocketbeasts.Beasts;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.FlipperShark;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yash Patel
 */
public class FlipperSharkTest {

    FlipperShark instance;
    
    public FlipperSharkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Testing FlipperShark class...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finished testing FlipperShark class.");
    }
    
    @Before
    public void setUp() {
        instance = new FlipperShark();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

   
    /**
     * Test of getId method, of abstract class BeastCard.
     */
//    @Ignore
    @Test
    public void testGetId() {
        System.out.println(" - Testing the GetId method");
        String expResult = "FS";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of abstract class BeastCard.
     */
//    @Ignore
    @Test
    public void testGetName() {
        System.out.println(" - Testing the GetName method");
        String expResult = "Flipper Shark";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getManacost method, of abstract class BeastCard.
     */
//    @Ignore
    @Test
    public void testGetManacost() {
        System.out.println(" - Testing the GetManacost method");
        int expResult = 4;
        int result = instance.getManaCost();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAttack method, of abstract class BeastCard.
     */
//    @Ignore
    @Test
    public void testGetAttack() {
        System.out.println(" - Testing the GetAttack method");
        int expResult = 5;
        int result = instance.getAttack();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHealth method, of abstract class BeastCard.
     */
//    @Ignore
    @Test
    public void testGetHealth() {
        System.out.println(" - Testing the GetHealth method");
        int expResult = 5;
        int result = instance.getHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHealth method, of abstract class BeastCard.
     */
//    @Ignore
    @Test
    public void testSetHealth() {
        System.out.println(" - Testing the SetHealth method");
        int newHealth = 4;
        instance.setHealth(newHealth);
        int expResult = newHealth;
        int result = instance.getHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of damage method, of abstract class BeastCard.
     */
//    @Ignore
    @Test
    public void testDamage() {
        System.out.println(" - Testing the Damage method");
        int health = instance.getHealth();
        int attack = 1;
        instance.damage(attack);
        int expResult = health - attack;
        int result = instance.getHealth();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of abstract class BeastCard.
     */
//    @Ignore
    @Test
    public void testToString() {
        System.out.println(" - Testing the ToString method");
        String expResult = "Flipper Shark (FS) Mana Cost/4 Attack/5 Health/5";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    
}

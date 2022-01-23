/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2001.pocketbeasts.Beasts;

import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.PredaciousVulture;
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
public class PredaciousVultureTest {

    private PredaciousVulture instance;
    
    public PredaciousVultureTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Testing PredaciousVulture class...");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finished testing PredaciousVulture class.");
    }
    
    @Before
    public void setUp() {
        instance = new PredaciousVulture();
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
        String expResult = "PV";
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
        String expResult = "Predacious Vulture";
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
        int expResult = 2;
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
        int expResult = 3;
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
        int expResult = 2;
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
        String expResult = "Predacious Vulture (PV) Mana Cost/2 Attack/3 Health/2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    
}

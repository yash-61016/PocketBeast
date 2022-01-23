/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.tees.cis2001.pocketbeasts;

import AbstractFactory.AirBeast;
import AbstractFactory.GroundBeast;
import AbstractFactory.WaterBeast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.Deck;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.StarterDeck;

/**
 *
 * @author Yash Patel
 */
public class PocketBeastTestHarness {


    PocketBeastTestHarness() {
        System.out.println(getRules());
        System.out.println("Press ENTER to continue...");
        Scanner sc = new Scanner(System.in);
        Player[] players = new Player[]{
            new Player("James", new Deck(getStarterDeck())),
            new Player("Steve", new Deck(getStarterDeck()))
        };

        for (Player player : players) {
            player.newGame();
            System.out.println(player);
        }

        String winningMessage = "";

        Boolean run = true;
        while (run) {
            for (Player player : players) {
                // Add mana and draw card
                player.addMana();
                if (player.getDeck().count() > 0) {
                    player.drawCard();
                }
                // Print initial play state
                System.out.println(player);

                // HACK assumes only one other player
                Player otherPlayer = null;
                for (Player iPlayer : players) {
                    if (iPlayer != player) {
                        otherPlayer = iPlayer;
                    }
                }
                if (otherPlayer == null) {
                    winningMessage = "Something has gone terribly wrong...";
                    run = false;
                    break;
                }

                // Cycle through cards in play to attack
                for (Card card : player.getInPlay().getCards()) {
                    System.out.println(card.toString());
                    String attack = getPrompt(
                            player.getName() + " attack with " + card.getName() + "? (Yes/No): ",
                            new String[]{"Yes", "No"});
                    if (attack.equals("Yes")) {
                        // Choose who to attack, player directly or a player's beast

                        if (card.getClass().getSuperclass() == BeastCard.class) {

                            int attackChoice = 2;
                            System.out.println("Who would you like to attack? ");
                            System.out.println("1. " + otherPlayer.getName());
                            for (Card otherCard : otherPlayer.getInPlay().getCards()) {
                                System.out.println(attackChoice + ". " + otherCard);
                                attackChoice++;
                            }
                            ArrayList<String> prompts = new ArrayList<>();
                            for (int i = 1; i < attackChoice; i++) {
                                prompts.add(String.valueOf(i));
                            }
                            String target = getPrompt("Choose a number: ", prompts.toArray(new String[0]));

                            if (target.equals("1")) { // Player
                                if (otherPlayer.damage(((BeastCard) card).getAttack())) {
                                    // if true returned players health <= 0
                                    winningMessage = player.getName() + " wins!";
                                    run = false;
                                    break;
                                }
                                System.out.println(otherPlayer.getName() + " is now at " + otherPlayer.getHealth());
                            } else { // Beast, index is `target-2`
                                Card targetCard = otherPlayer.getInPlay().getCard(Integer.parseInt(target) - 2);
                                ((BeastCard) targetCard).damage(((BeastCard) card).getAttack());
                                ((BeastCard) card).damage(((BeastCard) targetCard).getAttack());
                                System.out.println(" ");
                            }
                        }

                    }
                }

                if (!run) {
                    break;
                }

                // Cycle through cards in play remove "dead" cards (health <= 0)
                ArrayList<Card> toRemove = new ArrayList<>();
                for (Card card : player.getInPlay().getCards()) {
                    if (card.getClass().getSuperclass() == BeastCard.class) {
                        if (((BeastCard) card).getHealth() <= 0) {
                            toRemove.add(card);
                            player.getGraveyard().add(card);
                        }
                    }

                }
                player.getInPlay().removeAll(toRemove);

                toRemove = new ArrayList<>();
                for (Card card : otherPlayer.getInPlay().getCards()) {
                    if (card.getClass().getSuperclass() == BeastCard.class) {
                        if (((BeastCard) card).getHealth() <= 0) {
                            toRemove.add(card);
                            otherPlayer.getGraveyard().add(card);
                        }
                    }

                }
                otherPlayer.getInPlay().removeAll(toRemove);

                // Play cards from hand
                toRemove = new ArrayList<>();
                for (Card card : player.getHand().getCards()) {
                    if (card.getManaCost() <= player.getManaAvailable()) {
                        System.out.println(card.toString());

                        String play = getPrompt(
                                player.getName() + " play " + card.getName() + "? (Yes/No) ",
                                new String[]{"Yes", "No"});
                        if (play.equals("Yes")) {
                            player.getInPlay().add(card);
                            player.useMana(card.getManaCost());
                            toRemove.add(card);
                        }
                    }
                }
                player.getHand().removeAll(toRemove);

                // Print final play state
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(player);
            }
        }

        System.out.println(winningMessage);
    }

    /**
     * Gets a valid response from the given array of responses
     *
     * @param prompt the prompt to be printed in the console
     * @param validResponse array of String with all the valid responses
     * @return A String representing valid response
     */
    public static String getPrompt(String prompt, String[] validResponse) {
        System.out.print(prompt);
        Random r = new Random();
        int randomNumber = r.nextInt(validResponse.length);
        System.out.println(validResponse[randomNumber]);
        return validResponse[randomNumber];
    }

    private String getRules() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("\n");
        sb.append("-+-+-+-+-+-+-+-+-+-+-+-+");
        sb.append("\n");
        sb.append("Welcome to PocketBeasts!");
        sb.append("\n");
        sb.append("-+-+-+-+-+-+-+-+-+-+-+-+");
        sb.append("\n");
        sb.append("");
        sb.append("\n");
        sb.append("This basic console application tests our underlying software design patterns.");
        sb.append("\n");
        sb.append("");
        sb.append("\n");
        sb.append("Here's a key for each card:");
        sb.append("\n");
        sb.append("");
        sb.append("\n");
        sb.append("                             +-------+ ");
        sb.append("\n");
        sb.append("M  = Mana Cost               |      M| ");
        sb.append("\n");
        sb.append("ID = Card identifier:        |  ID   | ");
        sb.append("\n");
        sb.append("A  = Attack:                 |       | ");
        sb.append("\n");
        sb.append("H  = Health:                 |A     H| ");
        sb.append("\n");
        sb.append("                             +-------+ ");
        sb.append("\n");
        sb.append("");
        sb.append("\n");
        sb.append("New players each start with 15 Health and 1 Mana to spend on playing cards.");
        sb.append("\n");
        sb.append("At the start of the game each player draws 4 cards from their deck to hand.");
        sb.append("\n");
        sb.append("");
        sb.append("\n");
        sb.append("Players each take turns. Each turn consists four phases:");
        sb.append("\n");
        sb.append("1. Add mana (mana increases by one each turn and replenishes in full).");
        sb.append("\n");
        sb.append("2. Draw a card.");
        sb.append("\n");
        sb.append("3. Cycle through your cards in play (if any), choosing whether to attack.");
        sb.append("\n");
        sb.append("   a. Attacking the other player directly with your card inflicts damage to their health.");
        sb.append("\n");
        sb.append("      equal to the attack power of the card.");
        sb.append("\n");
        sb.append("   b. Attacking another players beast will damage both cards (equal to their attack values).");
        sb.append("\n");
        sb.append("   c. Any beast with <= 0 health is removed from the play field and placed into the graveyard.");
        sb.append("\n");
        sb.append("4. Play cards from hand.");
        sb.append("\n");
        sb.append("");
        sb.append("\n");
        return sb.toString();
    }
    
        private ArrayList<Card> getStarterDeck() {
        ArrayList<Card> starterDeck = new ArrayList<>();

        // adding diffrent types of cards
        BeastCard[] groundBeasts = new GroundBeast().createBeastCard();
        BeastCard[] airBeasts = new AirBeast().createBeastCard();
        BeastCard[] waterBeasts = new WaterBeast().createBeastCard();

        //adding diffrent types of cards to the starterDeck
        starterDeck.addAll(Arrays.asList(groundBeasts));
        starterDeck.addAll(Arrays.asList(airBeasts));
        starterDeck.addAll(Arrays.asList(waterBeasts));

        //mixing all the cards
        Collections.shuffle(starterDeck);
        return starterDeck;
    }
}

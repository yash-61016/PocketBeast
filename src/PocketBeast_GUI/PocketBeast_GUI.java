/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PocketBeast_GUI;

import PocketBeast_GUI.Card_GUI.TrainerCard_GUI;
import PocketBeast_GUI.Card_GUI.BeastCard_GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import uk.ac.tees.cis2001.pocketbeasts.Cards.BeastCards.BeastCard;
import uk.ac.tees.cis2001.pocketbeasts.Cards.TrainerCards.TrainerCard;
import uk.ac.tees.cis2001.pocketbeasts.Card;
import uk.ac.tees.cis2001.pocketbeasts.Player;

/**
 *
 * @author Yash Patel
 */
public class PocketBeast_GUI implements ActionListener, Runnable {

    JFrame MainFrame = new JFrame("Pocket Beast");

    Player you;
    Player tempPlayer;
    Player opponent;

    JPanel OpponentInPlayPanel = new JPanel();
    JLabel OpponentInPlayLabel = new JLabel();

    JPanel InPlayPanel = new JPanel();
    JLabel InPlayLabel = new JLabel();

    JPanel HandPanel = new JPanel();
    JScrollPane HandSPanel = new JScrollPane();
    JLabel HandLabel = new JLabel();

    JLabel ExtraCardsTitlePanel = new JLabel();
    JLabel DeckPanel = new JLabel();
    JLabel GraveyardPanel = new JLabel();
    JLabel ManaPanel = new JLabel();
    JLabel HealthPanel = new JLabel();

    JLabel MoveLabel = new JLabel();
    JTextField MoveTextField = new JTextField();
    JButton MoveButton = new JButton();

    JLabel PlayerNameLabel = new JLabel();

    JLabel OpponentNameLabel = new JLabel("Opponent : waiting..");

    JPanel ChatPanel = new JPanel();

    Rectangle CardCollectionPanelSize = new Rectangle(1895, 300);
    Rectangle MainFrameSize = new Rectangle(50, 10, 2500, 1500);

    JMenuBar menuBar;
    JMenu file, help, spacer;
    JMenuItem exit, rules, cardDetails;

    Color foreground = Color.decode("#4BC3B5");
    Color background = Color.decode("#1C2C54");

    JTextField ChatTextField;
    JButton SendButton;
    JLabel ChatAreaPanel = new JLabel();
    

    ServerSocket skt;
    Socket s;
    ObjectInputStream inStream;
    ObjectOutputStream outStream;

    Box vertical = Box.createVerticalBox();

    private static int maxOpponentInPlayOccupied = 0;
    private static int maxInPlayOccupied = 0;
    private static int maxHandOccupied = 0;

    public boolean yourTurn;
    private boolean won = false;
    public boolean enemyWon = false;
    boolean attackBool = true;
    boolean wantToAttack = false;
    boolean choosenBool = true;
    boolean playedBool = true;
    boolean trainerCardBool = false;
    
    public char winningMessage = 'W'; 
    private String font = "Ink Free";

    private String[] validResponses;
    ArrayList<String> prompts = new ArrayList<>();
    ArrayList<Card> toRemove = new ArrayList<>();
    int state;
    Card proxyCard;
    AttackThread attackThread = new AttackThread();

    ExecutorService service = Executors.newCachedThreadPool();
    private MoveEnum moveEnum;

    public PocketBeast_GUI(Player you, Boolean firstTurn) {

        this.you = you;
        this.tempPlayer = you;
        you.newGame();
        yourTurn = firstTurn;

        //adding Menu
        exit = new JMenuItem("End Game");
        rules = new JMenuItem("Game Rules");
        cardDetails = new JMenuItem("Card Detials");

        exit.addActionListener(this);
        rules.addActionListener(this);
        cardDetails.addActionListener(this);

        exit.setFont(new Font(font, Font.BOLD, 30));
        rules.setFont(new Font(font, Font.BOLD, 30));
        cardDetails.setFont(new Font(font, Font.BOLD, 30));

        menuBar = new JMenuBar();
        file = new JMenu("File");
        file.setFont(new Font(font, Font.BOLD, 30));

        help = new JMenu("Help");
        help.setFont(new Font(font, Font.BOLD, 30));

        spacer = new JMenu(" ");
        spacer.setEnabled(false);

        file.add(exit);
        help.add(rules);
        help.add(cardDetails);

        menuBar.add(file);
        menuBar.add(spacer);
        menuBar.add(help);

        //Other Player's InPlay Name code
        String OtherPlayerInPlayTitle = "<html>O<br>P I<br>P N<br>O P<br>N L<br>E A<br>N Y<br>T </htlm>";
        OpponentInPlayLabel.setText(OtherPlayerInPlayTitle);
        OpponentInPlayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        OpponentInPlayLabel.setVerticalAlignment(SwingConstants.CENTER);
        OpponentInPlayLabel.setFont(new Font(font, Font.BOLD, 28));
        OpponentInPlayLabel.setForeground(background);
        OpponentInPlayLabel.setBackground(foreground);
        OpponentInPlayLabel.setBounds(0, 0, 60, 300);
        OpponentInPlayLabel.setOpaque(true);

        //Other Player's InPlay code
        OpponentInPlayPanel.setBackground(Color.BLACK);
        OpponentInPlayPanel.setLayout(null);
        OpponentInPlayPanel.setBounds(65, 0, 1830, 300);
        OpponentInPlayPanel.setVisible(true);

        //Deck code
        ExtraCardsTitlePanel = new JLabel();
        ExtraCardsTitlePanel.setText("<html>Mana    :<br><br>Health    :<br><br>Deck    :<br><br>Graveyard  :</html>");
        ExtraCardsTitlePanel.setHorizontalAlignment(SwingConstants.CENTER);
        ExtraCardsTitlePanel.setVerticalAlignment(SwingConstants.CENTER);
        ExtraCardsTitlePanel.setFont(new Font(font, Font.BOLD, 45));
        ExtraCardsTitlePanel.setForeground(foreground);
        ExtraCardsTitlePanel.setBackground(background);
        ExtraCardsTitlePanel.setBounds(0, 300, 295, 480);
        ExtraCardsTitlePanel.setOpaque(true);

        //Mana code
        ManaPanel = new JLabel();
        int mana = you.getManaAvailable();
        ManaPanel.setText(Integer.toString(mana));
        ManaPanel.setHorizontalAlignment(SwingConstants.CENTER);
        ManaPanel.setVerticalAlignment(SwingConstants.CENTER);
        ManaPanel.setFont(new Font(font, Font.BOLD, 45));
        ManaPanel.setForeground(foreground);
        ManaPanel.setBackground(background);
        ManaPanel.setBounds(295, 300, 295, 160);
        ManaPanel.setOpaque(true);

        //Health code
        HealthPanel = new JLabel();
        int health = you.getHealth();
        HealthPanel.setText(Integer.toString(health));
        HealthPanel.setHorizontalAlignment(SwingConstants.CENTER);
        HealthPanel.setVerticalAlignment(SwingConstants.CENTER);
        HealthPanel.setFont(new Font(font, Font.BOLD, 45));
        HealthPanel.setForeground(foreground);
        HealthPanel.setBackground(background);
        HealthPanel.setBounds(295, 420, 295, 140);
        HealthPanel.setOpaque(true);

        //Deck code
        DeckPanel = new JLabel();
        int deck = you.getDeck().count();
        DeckPanel.setText(Integer.toString(deck));
        DeckPanel.setHorizontalAlignment(SwingConstants.CENTER);
        DeckPanel.setVerticalAlignment(SwingConstants.CENTER);
        DeckPanel.setFont(new Font(font, Font.BOLD, 45));
        DeckPanel.setForeground(foreground);
        DeckPanel.setBackground(background);
        DeckPanel.setBounds(295, 540, 295, 120);
        DeckPanel.setOpaque(true);

        //Graveyard code
        GraveyardPanel = new JLabel();
        int graveyard = you.getGraveyard().count();
        GraveyardPanel.setText(Integer.toString(graveyard));
        GraveyardPanel.setHorizontalAlignment(SwingConstants.CENTER);
        GraveyardPanel.setVerticalAlignment(SwingConstants.CENTER);
        GraveyardPanel.setFont(new Font(font, Font.BOLD, 45));
        GraveyardPanel.setForeground(foreground);
        GraveyardPanel.setBackground(background);
        GraveyardPanel.setBounds(295, 660, 295, 90);
        GraveyardPanel.setOpaque(true);

        //Player name code
        String name = you.getName();
        PlayerNameLabel.setText(name);
        PlayerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PlayerNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        PlayerNameLabel.setFont(new Font(font, Font.BOLD, 48));
        PlayerNameLabel.setForeground(background);
        PlayerNameLabel.setBackground(foreground);
        PlayerNameLabel.setBounds(590, 310, 1300, 60);
        PlayerNameLabel.setOpaque(true);

        //Move code
        MoveLabel.setText("");
        MoveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        MoveLabel.setVerticalAlignment(SwingConstants.CENTER);
        MoveLabel.setFont(new Font(font, Font.BOLD, 38));
        MoveLabel.setForeground(Color.WHITE);
        MoveLabel.setBackground(background);
        MoveLabel.setBounds(590, 375, 1300, 200);
        MoveLabel.setOpaque(true);

        MoveTextField = new JTextField();
        MoveTextField.setBounds(590, 650, 1300, 50);
        MoveTextField.setFont(new Font(font, Font.PLAIN, 38));

        MoveButton.setText("OK");
        MoveButton.setBounds(590, 705, 1300, 60);
        MoveButton.setForeground(Color.BLACK);
        MoveButton.setFont(new Font(font, Font.PLAIN, 32));
        MoveButton.addActionListener(this);

        //Player's InPlay Name code
        String InPlayTitle = "<html>I<br>N<br>P<br>L<br>A<br>Y</htlm>";
        InPlayLabel.setText(InPlayTitle);
        InPlayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        InPlayLabel.setVerticalAlignment(SwingConstants.CENTER);
        InPlayLabel.setFont(new Font(font, Font.BOLD, 28));
        InPlayLabel.setForeground(background);
        InPlayLabel.setBackground(foreground);
        InPlayLabel.setBounds(0, 780, 60, 300);
        InPlayLabel.setOpaque(true);

        //Player's InPlay code
        InPlayPanel.setBackground(Color.BLACK);
        InPlayPanel.setLayout(null);
        InPlayPanel.setBounds(65, 780, 1835, 300);
        InPlayPanel.setVisible(true);

        //Player's Hand Name code
        String HandTitle = "<html>H<br>A<br>N<br>D</htlm>";
        HandLabel.setText(HandTitle);
        HandLabel.setHorizontalAlignment(SwingConstants.CENTER);
        HandLabel.setVerticalAlignment(SwingConstants.CENTER);
        HandLabel.setFont(new Font(font, Font.BOLD, 28));
        HandLabel.setForeground(background);
        HandLabel.setBackground(foreground);
        HandLabel.setBounds(0, 1090, 60, 300);
        HandLabel.setOpaque(true);

        //Player's Hand code
        HandPanel.setBackground(Color.BLACK);
        HandPanel.setLayout(null);
        HandPanel.setBounds(0, 0, 3050, 300);
        HandPanel.setVisible(true);
        HandSPanel = new JScrollPane(HandPanel);
        HandSPanel.setWheelScrollingEnabled(true);
        HandSPanel.setBounds(65, 1090, 1835, 300);
        HandSPanel.setEnabled(true);
//        adding starter deck to hand
        for (Card card : you.getHand().getCards()) {
            this.addCardToPanel(card, HandPanel, maxHandOccupied);
            maxHandOccupied++;
        }

        //Chat panel code
        ChatPanel.setBackground(foreground);
        ChatPanel.setLayout(null);
        int chatPanelX = CardCollectionPanelSize.width;
        int chatPanelHeight = MainFrameSize.height;
        int chatPanelWidth = (MainFrameSize.width - CardCollectionPanelSize.width);
        ChatPanel.setBounds(chatPanelX, 80, chatPanelWidth, chatPanelHeight);
        ChatPanel.setVisible(true);

        //Opponent label code
        OpponentNameLabel.setFont(new Font(font, Font.BOLD, 40));
        OpponentNameLabel.setForeground(Color.white);
        OpponentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        OpponentNameLabel.setBounds(1900, 0, 600, 80);

        ChatAreaPanel.setBackground(Color.decode("#eeeeee"));
        ChatAreaPanel.setBounds(8, 10, (chatPanelWidth - 20), (chatPanelHeight - 285));
        ChatAreaPanel.setOpaque(true);
        

        ChatTextField = new JTextField();
        ChatTextField.setBounds(8, (chatPanelHeight - 280), (chatPanelWidth - 160), 95);
        ChatTextField.setFont(new Font(font, Font.PLAIN, 38));

        SendButton = new JButton("Send");
        SendButton.setBounds(450, 1220, 145, 95);
        SendButton.setFont(new Font(font, Font.PLAIN, 40));
        SendButton.addActionListener(this);
        
        
        
        ChatPanel.add(SendButton);
        ChatPanel.add(ChatTextField);
        ChatPanel.add(ChatAreaPanel);
        
        MainFrame.add(menuBar);
        MainFrame.setJMenuBar(menuBar);
        MainFrame.add(MoveLabel);
        MainFrame.add(MoveTextField);
        MainFrame.add(MoveButton);
        MainFrame.add(PlayerNameLabel);
        MainFrame.add(OpponentNameLabel);
        MainFrame.add(OpponentInPlayLabel);
        MainFrame.add(OpponentInPlayPanel);
        MainFrame.add(ChatPanel);
        MainFrame.add(ExtraCardsTitlePanel);
        MainFrame.add(DeckPanel);
        MainFrame.add(GraveyardPanel);
        MainFrame.add(ManaPanel);
        MainFrame.add(HealthPanel);
        MainFrame.add(HandSPanel);
        MainFrame.add(InPlayPanel);
        MainFrame.add(HandLabel);
        MainFrame.add(InPlayLabel);

        MainFrame.getContentPane().setBackground(background);
        MainFrame.setLayout(null);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setBounds(MainFrameSize);
        MainFrame.setVisible(true);
        MainFrame.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == SendButton) {
            try {
                String out = ChatTextField.getText();

                JPanel p2 = formatLabel(out);

                ChatAreaPanel.setLayout(new BorderLayout());

                JPanel right = new JPanel(new BorderLayout());
                right.add(p2, BorderLayout.LINE_END);
                vertical.add(right);
                vertical.add(Box.createVerticalStrut(15));

                ChatAreaPanel.add(vertical, BorderLayout.PAGE_START);

                outStream.writeObject(out);
                outStream.flush();
                ChatTextField.setText("");

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == MoveButton) {
            if (null != moveEnum) {
                switch (moveEnum) {
                    case PLAYINGBEAST:
                        String play = MoveTextField.getText();
                        if (validateResponse(play)) {
                            if (play.equals("Yes") || play.equals("yes") || play.equals("y")) {
                                if (proxyCard.getClass().getSuperclass() == TrainerCard.class) {
                                    trainerCardBool = true;
                                    MoveLabel.setText("");
                                    MoveTextField.setText("");
                                    updateInterface();
                                } else {
                                    you.getInPlay().add(proxyCard);
                                    toRemove.add(proxyCard);
                                    MoveLabel.setText("");
                                    MoveTextField.setText("");
                                    updateInterface();
                                    you.useMana(proxyCard.getManaCost());
                                    System.out.println("Mana" + you.getManaAvailable());
                                }
                                playedBool = false;
                                proxyCard = null;
                            } else {
                                MoveLabel.setText("");
                                MoveTextField.setText("");
                                updateInterface();
                                playedBool = false;
                            }
                        } else {
                            MoveTextField.setText("");
                        }
                        break;

                    case PLAYINGTRAINER:
                        String beastCardIndex = MoveTextField.getText();
                        if (validateResponse(beastCardIndex)) {
                            ((TrainerCard) proxyCard).useSpecialPower((BeastCard) you.getInPlay().getCard(Integer.parseInt(beastCardIndex) - 1));
                            System.out.println("new health");
                            System.out.println(((BeastCard) you.getInPlay().getCard(Integer.parseInt(beastCardIndex) - 1)).getHealth());
                            System.out.println(((BeastCard) you.getInPlay().getCard(Integer.parseInt(beastCardIndex) - 1)).getAttack());
                            choosenBool = false;
                            you.useMana(proxyCard.getManaCost());
                            System.out.println("Mana" + you.getManaAvailable());
                            toRemove.add(proxyCard);
                            MoveTextField.setText("");
                            updateInterface();
                            proxyCard = null;
                        } else {
                            MoveTextField.setText("");
                        }
                        break;
                    case ATTACKING:
                        if (MoveTextField.getText() != null) {
                            String response = MoveTextField.getText();
                            if (validateResponse(response)) {
                                if (response.equals("Yes") || response.equals("yes") || response.equals("y")) {
                                    wantToAttack = true;
                                    attackBool = false;
                                } else {
                                    wantToAttack = false;
                                    attackBool = false;
                                }
                                MoveLabel.setText("");
                                MoveTextField.setText("");
                                updateInterface();
                            } else {
                                MoveTextField.setText("");
                            }

                        }
                        break;
                    case CHOOSINGYOATTACK:
                        String target = MoveTextField.getText();
                        if (validateResponse(target)) {
                            if (target.equals("1")) { // Player
                                if (opponent.damage(((BeastCard) proxyCard).getAttack())) {
                                    try {
                                        outStream.writeObject(winningMessage);
                                        outStream.flush();
                                        outStream.reset();
                                    } catch (IOException ex) {
                                        Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    URL url = PocketBeast_GUI.class.getResource("/winner.gif");
                                    Icon icon = new ImageIcon(url);
                                    JOptionPane.showMessageDialog(
                                            MainFrame,
                                            "",
                                            " ", JOptionPane.INFORMATION_MESSAGE,
                                            icon);
                                    System.exit(0);
                                }
                                MoveLabel.setText(opponent.getName() + " is now at " + opponent.getHealth());
                                updateInterface();
                                try {
                                    service.awaitTermination(1, TimeUnit.SECONDS);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else { // Beast, index is `target-2`
                                Card targetCard = opponent.getInPlay().getCard(Integer.parseInt(target) - 2);
                                ((BeastCard) targetCard).damage(((BeastCard) proxyCard).getAttack());
                                ((BeastCard) proxyCard).damage(((BeastCard) targetCard).getAttack());
                            }
                            choosenBool = false;
                            MoveTextField.setText("");
                            updateInterface();
                            proxyCard = null;
                        } else {
                            MoveTextField.setText("");
                        }

                        break;
                    default:
                        break;
                }
            }
        } else if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == rules) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append("New players each start with 15 Health and 1 Mana to spend on playing cards.");
            sb.append("<br><br>");
            sb.append("At the start of the game each player draws 4 cards from their deck to hand.");
            sb.append("<br><br>");
            sb.append("Players each take turns. Each turn consists four phases:");
            sb.append("<br><br>");
            sb.append("1. Add mana (mana increases by one each turn and replenishes in full).");
           sb.append("<br><br>");
            sb.append("2. Draw a card.");
            sb.append("<br>");
            sb.append("There are 2 types of cards :");
            sb.append("<br>");
            sb.append("i) Beast Card : Cards with powers to attack another person's card.");
            sb.append("<br>");
            sb.append("ii) Trainer Card : Cards which can increase powers of Beast card. You can't play these cards on its own, ");
            sb.append("<br>");
            sb.append("Note : You can't play Trainer card on its own, you can only play");
            sb.append("<br>");
            sb.append("them on top of Beast card and only can be played once.");
            sb.append("<br>");
            sb.append("for further details check CARD DETAILS tab in HELP menu.");
            sb.append("<br><br>");
            sb.append("3. Cycle through your cards in play (if any), choosing whether to attack.");
            sb.append("<br>");
            sb.append("i) Attacking the other player directly with your card inflicts damage to their health.");
            sb.append("<br>");
            sb.append(" equal to the attack power of the card.");
            sb.append("<br>");
            sb.append("ii) Attacking another players beast will damage both cards (equal to their attack values).");
            sb.append("<br>");
            sb.append("iii) Any card with <= 0 health is removed from the play field and placed into the graveyard.");
            sb.append("<br><br>");
            sb.append("4. Play cards from hand.");
            sb.append("<br><br>");
            sb.append("<br>");
            JLabel rules = new JLabel("<html>" + sb.toString() + "</html>");
            rules.setFont(new Font("Arial", Font.BOLD, 28));
            JOptionPane.showMessageDialog(MainFrame, rules);
        } else if(ae.getSource() == cardDetails){

            ImageIcon icon = new ImageIcon(this.getClass().getResource("/cardDetails.jpg")); 
            JOptionPane.showMessageDialog(
                        null,
                        "",
                        " ", JOptionPane.INFORMATION_MESSAGE,
                        icon);
        }
    }

    public JPanel formatLabel(String out) {
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">" + out + "</p></html>");
        l1.setFont(new Font(font, Font.PLAIN, 28));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15, 15, 15, 50));

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        l2.setFont(new Font(font, Font.PLAIN, 18));
        p3.add(l1);
        p3.add(l2);
        return p3;
    }

    private void addCardToPanel(Card card, JPanel panel, int maxOccupied) {
        if (card.getClass().getSuperclass() == BeastCard.class) {
            BeastCard_GUI beastCardGUI = new BeastCard_GUI((BeastCard) card);
            int x = (maxOccupied * 180) + 10;
            beastCardGUI.setCardCordinates(x, 50);
            panel.add(beastCardGUI.getCardGui());
        } else if (card.getClass().getSuperclass() == TrainerCard.class) {
            TrainerCard_GUI trainerCardGUI = new TrainerCard_GUI((TrainerCard) card);
            int x = (maxOccupied * 180) + 10;
            trainerCardGUI.setCardCordinates(x, 50);;
            panel.add(trainerCardGUI.getCardGui());
        }

    }

    private void drawCard() {
        if (you.getDeck().count() > 0) {
            you.drawCard();
            HandPanel.removeAll();
            maxHandOccupied = 0;
            for (Card card : you.getHand().getCards()) {
                this.addCardToPanel(card, HandPanel, maxHandOccupied);
                maxHandOccupied++;
            }
            DeckPanel.setText(Integer.toString(you.getDeck().count()));
            updateInterface();
        }
    }

    private Boolean validateResponse(String response) {
        if (Arrays.stream(validResponses).anyMatch(response::equals)) {
            return true;
        }
        return false;
    }

    public void updateInterface() {
        ManaPanel.setText(Integer.toString(you.getManaAvailable()));
        DeckPanel.validate();
        ManaPanel.validate();
        HealthPanel.validate();
        GraveyardPanel.validate();
        HandPanel.validate();
        InPlayPanel.validate();
        OpponentInPlayPanel.validate();
        MoveLabel.validate();
        MainFrame.validate();
        MainFrame.repaint();
    }

    public void setPrompt(String prompt, String[] validResponse) {
        this.validResponses = validResponse;
        MoveLabel.setText("<html>" + prompt + "</html>");
        updateInterface();
    }

    @Override
    public void run() {
        if (enemyWon) {
           URL loseGifUrl = PocketBeast_GUI.class.getResource("/lose.gif");
            Icon lostIcon = new ImageIcon(loseGifUrl);
            
            JOptionPane.showMessageDialog(
                    MainFrame,
                    "",
                    " ", JOptionPane.INFORMATION_MESSAGE,
                    lostIcon);
            System.exit(0);
        }

             if (yourTurn == true) {
            MoveTextField.setEditable(yourTurn);
            MoveButton.setEnabled(yourTurn);
            OpponentInPlayPanel.removeAll();
            maxOpponentInPlayOccupied = 0;
            for (Card card : opponent.getInPlay().getCards()) {
                addCardToPanel(card, OpponentInPlayPanel, maxOpponentInPlayOccupied);
                maxOpponentInPlayOccupied++;
            }
            OpponentInPlayPanel.validate();
            you.addMana();
            ManaPanel.setText(Integer.toString(you.getManaAvailable()));
            updateInterface();
            this.drawCard();

            AttackThread attackThread = new AttackThread();
            Future<?> attackFuture = service.submit(attackThread);
            try {
                attackFuture.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            ArrayList playerDeadCardsToRemove = new ArrayList<>();
            for (Card card : you.getInPlay().getCards()) {
                if (card.getClass().getSuperclass() == BeastCard.class) {
                    if (((BeastCard) card).getHealth() <= 0) {
                        playerDeadCardsToRemove.add(card);
                        you.getGraveyard().add(card);
                    }
                }
            }
            you.getInPlay().removeAll(playerDeadCardsToRemove);

            ArrayList opponentDeadCardsToRemove = new ArrayList<>();
            for (Card card : opponent.getInPlay().getCards()) {
                if (card.getClass().getSuperclass() == BeastCard.class) {
                    if (((BeastCard) card).getHealth() <= 0) {
                        opponentDeadCardsToRemove.add(card);
                        opponent.getGraveyard().add(card);
                    }
                }

            }
            opponent.getInPlay().removeAll(opponentDeadCardsToRemove);
            InPlayPanel.removeAll();
            maxInPlayOccupied = 0;
            for (Card card : you.getInPlay().getCards()) {
                addCardToPanel(card, InPlayPanel, maxInPlayOccupied);
                maxInPlayOccupied++;
            }
            OpponentInPlayPanel.removeAll();
            maxOpponentInPlayOccupied = 0;
            for (Card card : opponent.getInPlay().getCards()) {
                addCardToPanel(card, OpponentInPlayPanel, maxOpponentInPlayOccupied);
                maxOpponentInPlayOccupied++;
            }
            updateInterface();

            PlayCardThread playCardThread = new PlayCardThread();
            Future<?> playFuture = service.submit(playCardThread);
            try {
                playFuture.get();
            } catch (InterruptedException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            InPlayPanel.removeAll();
            maxInPlayOccupied = 0;
            for (Card card : you.getInPlay().getCards()) {
                addCardToPanel(card, InPlayPanel, maxInPlayOccupied);
                maxInPlayOccupied++;
            }
            updateInterface();
            yourTurn = false;
            

            try {
                outStream.writeObject(you);
                System.out.println(you);
                outStream.flush();
                outStream.reset();
            } catch (IOException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outStream.writeObject(opponent.getInPlay());
                outStream.flush();
                outStream.reset();
            } catch (IOException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outStream.writeObject(opponent.getGraveyard());
                outStream.flush();
                outStream.reset();
            } catch (IOException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outStream.writeObject(opponent.getHealth());
                outStream.flush();
                outStream.reset();
            } catch (IOException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outStream.writeObject(true);
                outStream.flush();
                outStream.reset();
            } catch (IOException ex) {
                Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (yourTurn == false) {
            MoveLabel.setText("<html> Waiting for " + opponent.getName() + " to play... </html>");
            MoveTextField.setEditable(yourTurn);
            MoveButton.setEnabled(yourTurn);
            updateInterface();
        
        }
    }

    class AttackThread implements Runnable {

        @Override
        public void run() {
            for (Iterator it = you.getInPlay().getCards().iterator(); it.hasNext();) {
                Card card = (Card) it.next();
                attackBool = true;
                choosenBool = true;
                if (card.getManaCost() <= you.getManaAvailable()) {
                    setPrompt(you.getName() + " attack with " + card.getName() + "? (Yes/No): ",
                            new String[]{"Yes", "yes", "y", "No", "no", "n"});

                    moveEnum = MoveEnum.ATTACKING;
                    while (attackBool) {
                        try {
                            service.awaitTermination(1, TimeUnit.SECONDS);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (wantToAttack) {
                        MoveLabel.setText("");
                        MoveTextField.setText("");
                        MoveButton.setEnabled(true);
                        updateInterface();

                        ChooseToAttackThread chooseToAttackThread = new ChooseToAttackThread(card);
                        Future<?> chooseToAttackFuture = service.submit(chooseToAttackThread);
                        try {
                            chooseToAttackFuture.get();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ExecutionException ex) {
                            Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        }
    }

    class ChooseToAttackThread implements Runnable {

        ChooseToAttackThread(Card card) {
            proxyCard = card;
        }

        @Override
        public void run() {

            if (proxyCard.getClass().getSuperclass() == BeastCard.class) {
                int attackChoice = 2;
                StringBuilder sb = new StringBuilder();
                sb.append("Who would you like to attack? ");
                sb.append("<br>");
                sb.append("1. ").append(opponent.getName());
                for (Card otherCard : opponent.getInPlay().getCards()) {
                    if(((BeastCard)otherCard).getHealth()>0){
                        sb.append("<br>");
                    sb.append(attackChoice).append(". ").append(otherCard);
                    attackChoice++;
                    }
                }
                for (int i = 1; i < attackChoice; i++) {
                    prompts.add(String.valueOf(i));
                }
                setPrompt(sb.toString(), prompts.toArray(new String[0]));
                updateInterface();
                moveEnum = MoveEnum.CHOOSINGYOATTACK;
                while (choosenBool) {
                    try {
                        service.awaitTermination(1, TimeUnit.SECONDS);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }

    class PlayCardThread implements Runnable {

        Boolean clicked = false;

        @Override
        public void run() {

            for (Card card : you.getHand().getCards()) {
                choosenBool = true;
                playedBool = true;
                if (card.getManaCost() <= you.getManaAvailable()) {
                    if (card.getClass().getSuperclass() == TrainerCard.class) {
                        if (you.getInPlay().count() > 0) {
                            this.askToPlayCard(card);
                        }
                    } else {
                        this.askToPlayCard(card);
                    }

                }
                updateInterface();
            }
            for (Iterator it = toRemove.iterator(); it.hasNext();) {
                Card toAdd = (Card) it.next();
                if(toAdd.getClass().getSuperclass() == BeastCard.class){
                    addCardToPanel(toAdd, InPlayPanel, maxInPlayOccupied);
                    maxInPlayOccupied++;
                }
            }
            you.getHand().removeAll(toRemove);
            toRemove.removeAll(toRemove);
            HandPanel.removeAll();
            maxHandOccupied = 0;
            for (Card tempCard : you.getHand().getCards()) {
                addCardToPanel(tempCard, HandPanel, maxHandOccupied);
                maxHandOccupied++;
            }
            updateInterface();
        }

        private void askToPlayCard(Card card) {
            proxyCard = card;
            moveEnum = MoveEnum.PLAYINGBEAST;
            setPrompt("Want to play " + proxyCard.getName() + "? (Yes/No) ",
                    new String[]{"Yes", "yes", "y", "No", "no", "n"});
            while (playedBool) {
                try {
                    service.awaitTermination(1, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (trainerCardBool == true) {
                this.askToPlayTrainerCard(card);
            }
        }

        private void askToPlayTrainerCard(Card card) {
            proxyCard = card;
            int attackChoice = 0;
            StringBuilder sb = new StringBuilder();
            sb.append("On which card you want to use " + card.getName());
            sb.append("<br>");
            for (Card otherCard : you.getInPlay().getCards()) {
                attackChoice++;
                sb.append("<br>");
                sb.append(attackChoice).append(". ").append(otherCard);
            }
            for (int i = 1; i < attackChoice; i++) {
                prompts.add(String.valueOf(i));
            }
            setPrompt(sb.toString(), prompts.toArray(new String[0]));
            updateInterface();
            moveEnum = MoveEnum.PLAYINGTRAINER;
            while (choosenBool) {
                try {
                    service.awaitTermination(1, TimeUnit.SECONDS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PocketBeast_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}

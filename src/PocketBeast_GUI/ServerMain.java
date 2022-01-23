/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PocketBeast_GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.Deck;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.Graveyard;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.InPlay;
import uk.ac.tees.cis2001.pocketbeasts.CardCollection.StarterDeck;
import uk.ac.tees.cis2001.pocketbeasts.Player;

/**
 *
 * @author Yash Patel
 */
public class ServerMain {

    public static final int PORT = 6001;

    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("src/icon.png");
        Image image = icon.getImage(); 
        Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  
        JLabel enterNameLabel = new JLabel("Please enter your good name");
        Font font = new Font("Ink Free", Font.BOLD, 30);
        enterNameLabel.setFont(font);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        String playerName = (String) JOptionPane.showInputDialog(null, enterNameLabel,
                "Welcome to PocketBeats", JOptionPane.QUESTION_MESSAGE, icon, null, null);
        
        Player player = new Player(playerName, new Deck(StarterDeck.getStarterDeck()));
        PocketBeast_GUI PocketBeast_Server = new PocketBeast_GUI(player, true);
        PocketBeast_Server.MainFrame.setVisible(true);
        final ExecutorService service = Executors.newCachedThreadPool();
        String msginput = "";
        Player opponent;
        Object object;
        Class c;
        try {
            PocketBeast_Server.skt = new ServerSocket(PORT);
            System.out.println("Server is up and running");
            while (true) {
                PocketBeast_Server.s = PocketBeast_Server.skt.accept();//Keeps the program running!
                PocketBeast_Server.outStream = new ObjectOutputStream(PocketBeast_Server.s.getOutputStream());
                PocketBeast_Server.inStream = new ObjectInputStream(PocketBeast_Server.s.getInputStream());
                PocketBeast_Server.outStream.writeObject(PocketBeast_Server.you);
                PocketBeast_Server.outStream.flush();
                PocketBeast_Server.outStream.reset();
                while (true) {
                    object = PocketBeast_Server.inStream.readObject();
                    c = object.getClass();
                    if (c == Player.class) {
                        opponent = (Player) object;
                        PocketBeast_Server.opponent = opponent;
                        String name = PocketBeast_Server.opponent.getName();
                        PocketBeast_Server.OpponentNameLabel.setText("Opponent: " + name);
                        PocketBeast_Server.HealthPanel.setText(Integer.toString(PocketBeast_Server.opponent.getHealth()));
                        PocketBeast_Server.MainFrame.validate();
                        object = null;
                        service.submit(PocketBeast_Server);
                    } else if (c == InPlay.class) {
                        PocketBeast_Server.you.setInPlay((InPlay) object);
                    } else if (c == Graveyard.class) {
                        PocketBeast_Server.you.setGraveyard((Graveyard) object);
                         PocketBeast_Server.GraveyardPanel.setText(Integer.toString(PocketBeast_Server.you.getGraveyard().count()));
                    } else if (c == Integer.class) {
                        PocketBeast_Server.HealthPanel.setText(Integer.toString((Integer) object));
                    } else if (c == String.class) {
                        PocketBeast_Server.ChatAreaPanel.setLayout(new BorderLayout());
                         msginput = (String) object;
                        JPanel p2 = PocketBeast_Server.formatLabel(msginput);
                        JPanel left = new JPanel(new BorderLayout());
                        left.add(p2, BorderLayout.LINE_START);
                        PocketBeast_Server.vertical.add(left);
                        PocketBeast_Server.vertical.add(Box.createVerticalStrut(15));
                        PocketBeast_Server.ChatAreaPanel.add(PocketBeast_Server.vertical, BorderLayout.PAGE_START);
                        PocketBeast_Server.MainFrame.validate();
                    } else if(c == Boolean.class){
                        PocketBeast_Server.yourTurn = (Boolean) object;
                        service.submit(PocketBeast_Server);
                    } else if (c == Character.class) {
                        PocketBeast_Server.enemyWon = true;
                        service.submit(PocketBeast_Server);
                    }
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}

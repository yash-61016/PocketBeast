
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
import java.net.Socket;
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
public class ClientMain {

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
        PocketBeast_GUI PocketBeast_Client = new PocketBeast_GUI(player, false);
        PocketBeast_Client.MainFrame.setVisible(true);
        final ExecutorService service = Executors.newCachedThreadPool();
        try {

            PocketBeast_Client.s = new Socket("127.0.0.1", ServerMain.PORT);
            PocketBeast_Client.outStream = new ObjectOutputStream(PocketBeast_Client.s.getOutputStream());
            PocketBeast_Client.inStream = new ObjectInputStream(PocketBeast_Client.s.getInputStream());
            PocketBeast_Client.outStream.writeObject(PocketBeast_Client.you);
            PocketBeast_Client.outStream.flush();
            PocketBeast_Client.outStream.reset();
            String msginput = "";
            Player opponent;
            Object object;
            Class c;
            while (true) {
                    object = PocketBeast_Client.inStream.readObject();
                    c = object.getClass();
               if (c == Player.class) {
                    opponent = (Player) object;
                    PocketBeast_Client.opponent = opponent;
                    String name = PocketBeast_Client.opponent.getName();
                    PocketBeast_Client.OpponentNameLabel.setText("Opponent: " + name);
                    PocketBeast_Client.MainFrame.validate();
                    object = null;
                } else if (c == InPlay.class) {
                    PocketBeast_Client.you.setInPlay((InPlay) object);
                } else if (c == Graveyard.class) {
                    PocketBeast_Client.you.setGraveyard((Graveyard) object);
                    PocketBeast_Client.GraveyardPanel.setText(Integer.toString(PocketBeast_Client.you.getGraveyard().count()));
                }
                else if (c == Integer.class) {
                    PocketBeast_Client.HealthPanel.setText(Integer.toString((Integer)object));
                }else if (c == String.class) {
                    PocketBeast_Client.ChatAreaPanel.setLayout(new BorderLayout());
                    msginput = (String) object;
                    JPanel p2 = PocketBeast_Client.formatLabel(msginput);
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(p2, BorderLayout.LINE_START);
                    PocketBeast_Client.vertical.add(left);
                    PocketBeast_Client.vertical.add(Box.createVerticalStrut(15));
                    PocketBeast_Client.ChatAreaPanel.add(PocketBeast_Client.vertical, BorderLayout.PAGE_START);
                    PocketBeast_Client.MainFrame.validate();
                } else if(c == Boolean.class){
                        PocketBeast_Client.yourTurn = (Boolean) object;
                        service.submit(PocketBeast_Client);
                 }else if(c == Character.class){
                    PocketBeast_Client.enemyWon = true;
                    service.submit(PocketBeast_Client);
                    }
            }

        } catch (Exception e) {
        }
    }
}

package fr.uvsq.M1.App.Rogue_Like;

import javax.swing.JFrame;
import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.uvsq.M1.App.Rogue_Like.screen.PlayScreen;
import fr.uvsq.M1.App.Rogue_Like.screen.Screen;
import fr.uvsq.M1.App.Rogue_Like.screen.StartScreen;

/**
 * Main :Rogue Like
 */
public class AppMain extends JFrame implements KeyListener {
    /**
     * to solve some compilation problem.
     */
    private static final long serialVersionUID = 1060623638149583738L;

    /**
     * terminal.
     */
    private AsciiPanel terminal;

    /**
     * game screen.
     */
    private Screen screen;

    /**
     * Constructeur of AppMain witch instance 
     * a new terminal and screen.
     */
    public AppMain(){
        super();
        terminal = new AsciiPanel(80,26);
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();
    }
    /**
     * Constructeur of AppMain witch instance 
     * get the seved screen 
     * @param instance of PlayScreen 
     */
    public AppMain(PlayScreen s){
        super();
        terminal = new AsciiPanel(80,26);
        add(terminal);
        pack();
        screen =s;
        addKeyListener(this);
        repaint();
    }
    /**
     * redisplay terminal after every interaction with the player.
     */
    public void repaint(){
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    /**
     * update with the keyboard
     * @param e button clicked by the player.
     */
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }

    /**
     *  Main method 
     * @param args null
     */
    public static void main(String[] args) {
        AppMain app = new AppMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}

package fr.uvsq.M1.App.Rogue_Like.screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import fr.uvsq.M1.App.Rogue_Like.world.Weapon;
import fr.uvsq.M1.App.Rogue_Like.world.Magic;

public class HelpScreen implements Screen {

	/**
     * Methode possible interactions between users.
     * @param terminal represente screen of the Game.
     */
    public void displayOutput(AsciiPanel terminal) {
    	terminal.writeCenter("--------------------- HELP: ---------------------", 1);
        terminal.write((char)1 + " click VK_LEFT:  to move on left ", 10, 10);
        terminal.write((char)1 + " click VK_RIGHT:  to move on right", 10, 11);
        terminal.write((char)1 + " click VK_UP : to move up ", 10, 12);
        terminal.write((char)1 + " click VK_DOWN : to move down ", 10, 13);
        terminal.write((char)1 + " click VK_R : to pick up objects ", 10, 14);
        terminal.write((char)1 + " click VK_Z : Attack ", 10, 15);
        terminal.write((char)1 + " click VK_P : Use Magic ", 10, 16);
        terminal.write((char)1 + " click VK_S : save Rogue Like ", 10, 17);
        terminal.write((char)1 + " click VK_Q : Quit Rogue Like ", 10, 18);
        terminal.writeCenter("" + (char)1 + (char)1 + (char)1 + (char)1 + 
        		(char)1 + (char)1 + (char)1 + (char)1 + (char)1, 19); 
       
    }

    /**
     * Methode of interaction users.
     * @param key that the user types on the keyboard.
     * @return new screen to display after interaction with the user.
     */
    public Screen respondToUserInput(KeyEvent key) {
    	if(key.getKeyCode() == KeyEvent.VK_ENTER) return new PlayScreen(1, Weapon.NO_WEAPON, Magic.NO_MAGIC, 0);
        else if(key.getKeyCode() == KeyEvent.VK_A) return new StartScreen();
        else 
        	return this;
    }
}


package fr.uvsq.M1.App.Rogue_Like.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import fr.uvsq.M1.App.Rogue_Like.world.Magic;
import fr.uvsq.M1.App.Rogue_Like.world.Weapon;

/**
 * Classe StartScreen qui s'affichera quand l'utilisateur aura demarrer le jeu.
 */
public class StartScreen implements Screen {

	/**
	 * Methode qui affiche les interactions possibles avec l'utilisateur.
	 * @param terminal represente l'ecran du jeu.
	 */
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("--------------------- Menu : ---------------------", 1);
		terminal.write((char)1 + " click A :     to Start new game", 10, 10);
		terminal.write((char)1 + " click B :     to get your sevgarded game", 10, 11);
		terminal.write((char)1 + " click D :     How to play", 10, 13);
		terminal.write((char)1 + " click Q :     QUITE", 10,15);
		terminal.writeCenter("" + (char)1 + (char)1 + (char)1 + (char)1 
				+ (char)1 + (char)1 + (char)1 + (char)1 + (char)1 + (char)1 + (char)1, 20);    
	}

	/**
	 * Methode qui permet a l'utilisateur d'interagir avec l'utilisateur.
	 * @param key touche que l'utilisateur tape sur le clavier.
	 * @return nouvel ecran a afficher apres l'interaction avec l'utilisateur.
	 */
	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
		case KeyEvent.VK_A: return new PlayScreen(1, Weapon.NO_WEAPON, Magic.NO_MAGIC, 0);
		case KeyEvent.VK_B: return new Dialoge();
		case KeyEvent.VK_D: return new HelpScreen();
		case KeyEvent.VK_Q: return new QuitRL();
		}
		return this;
	}
}

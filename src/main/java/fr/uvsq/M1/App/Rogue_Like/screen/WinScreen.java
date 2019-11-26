package fr.uvsq.M1.App.Rogue_Like.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;

/**
 * Classe WinScreen qui s'affichera quand l'utilisateur aura gagner le jeux.
 */
public class WinScreen implements Screen {

	/**
	 * Methode qui affiche les interactions possibles avec l'utilisateur.
	 * @param terminal represente l'ecran du jeu.
	 */
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("YOU WIN !!!!!", 10, AsciiPanel.brightBlue);
		terminal.writeCenter("-- press [Q] to QUITE --", 24);
		terminal.writeCenter("-- press [A] to go back to the menu principal --", 25);
	}

	/**
	 * Methode qui permet a l'utilisateur d'interagir avec l'utilisateur.
	 * @param key touche que l'utilisateur tape sur le clavier.
	 * @return nouvel ecran a afficher apres l'interaction avec l'utilisateur.
	 */
	public Screen respondToUserInput(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_A) return new StartScreen();
		else if(key.getKeyCode() == KeyEvent.VK_Q) return new QuitRL();
		else 
			return this;
	}
}

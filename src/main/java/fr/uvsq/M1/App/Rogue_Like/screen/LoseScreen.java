package fr.uvsq.M1.App.Rogue_Like.screen;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import fr.uvsq.M1.App.Rogue_Like.world.Weapon;
import fr.uvsq.M1.App.Rogue_Like.world.Magic;

/**
 * Classe LoseScreen qui s'affichera quand l'utilisateur aura perdu sa partie.
 */
public class LoseScreen implements Screen {

	/**
	 * Methode qui affiche les interactions possibles avec l'utilisateur.
	 * @param terminal represente l'ecran du jeu.
	 */
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("You lost.", 10, AsciiPanel.brightBlue);
		terminal.writeCenter("-- press [enter] to play again --", 24);
		terminal.writeCenter("-- press [Q] to QUITE the game --", 25);
	}

	/**
	 * Methode qui permet a l'utilisateur d'interagir avec l'utilisateur.
	 * @param key touche que l'utilisateur tape sur le clavier.
	 * @return nouvel ecran a afficher apres l'interaction avec l'utilisateur.
	 */
	public Screen respondToUserInput(KeyEvent key) {
		if(key.getKeyCode() == KeyEvent.VK_ENTER) return new PlayScreen(1, Weapon.NO_WEAPON, Magic.NO_MAGIC, 0);
		else if(key.getKeyCode() == KeyEvent.VK_Q) return new QuitRL();
		else 
			return this;
	}
}

package fr.uvsq.M1.App.Rogue_Like.world;

import java.awt.Color;
import java.io.Serializable;

import asciiPanel.AsciiPanel;

/**
 * Element possible dans un monde cree dans Rogue Like.
 */
public enum Tile implements Serializable{
	/**
	 * Elements representant le sol, les murs, les limites de jeu,arbre
	 * Weapon magic...
	 */
	FLOOR((char)250, AsciiPanel.white),
	WALL((char)177, AsciiPanel.cyan),
	BOUNDS('x', AsciiPanel.brightGreen),
	KEY((char)134, AsciiPanel.brightYellow),
	DOOR((char)219, AsciiPanel.brightYellow),
	TREE((char)5, AsciiPanel.brightGreen),
	SWORD((char)25, AsciiPanel.brightWhite),
	GUN((char)172, AsciiPanel.brightGreen),
	POISON((char)7, AsciiPanel.red),
	LIFE((char)3, AsciiPanel.brightRed),
	TRAVEL((char)30, AsciiPanel.brightRed),
	MEGA_POWER((char)6, AsciiPanel.brightBlue),
	MONEY ('$', AsciiPanel.yellow);

	/**
	 * Representation ASCII de cet element.
	 */
	private char caractere;
	
	/**
	 * Couleur de l'element.
	 */
	private Color color;

	/**
	 * Accesseur de la representation ASCII de l'element
	 * @return char representant le caractere ASCII de l'element a afficher.
	 */
	public char getCaractere() { return caractere; }

	/**
	 * Accesseur de la couleur de l'element.
	 * @return couleur de l'element.
	 */
	public Color getColor() { return color; }

	/**
	 * Constructeur de l'element
	 * @param caractere ASCII de l'element.
	 * @param color couleur de l'element.
	 */
	Tile(char caractere, Color color){
		this.caractere = caractere;
		this.color = color;
	}

	/**
	 * Methode qui teste si on peut marcher sur ce bloc.
	 * @return TRUE si on peut marcher sur ce bloc et FALSE sinon.
	 */
	public boolean isGround() {
		return this != DOOR && this != WALL && this != BOUNDS;
	}
}

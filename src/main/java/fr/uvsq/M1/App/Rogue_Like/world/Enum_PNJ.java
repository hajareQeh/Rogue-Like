package fr.uvsq.M1.App.Rogue_Like.world;

import asciiPanel.AsciiPanel;
import java.awt.Color;
import java.io.Serializable;

/**
 * Enumerations des differentes classes de PNJ possible.
 */
public enum Enum_PNJ implements Serializable{

	/**
	 * fairy give you some power 
	 * family give you some money
	 * zombie/sorcier take some of your life point
	 * 
	 */
	FAIRY((char)131, AsciiPanel.white),
	ZOMBIE('Z', AsciiPanel.brightRed),
	SORCIER('S', AsciiPanel.brightRed),
	FAMILY((char)2, AsciiPanel.green),
	NB_ENUM_PNJ('X', AsciiPanel.white);

	/**
	 * Caractere representant le PNJ.
	 */
	private char caractere;

	/**
	 * Couleur representant le PNJ.
	 */
	private Color color;

	/**
	 * Nombre de vies pour le PNJ.
	 */
	/**
	 * Constructeur de l'enumeration.
	 * @param caractere du PNJ.
	 * @param color du PNJ.
	 * @param vie du PNJ.
	 */
	Enum_PNJ(char caractere, Color color) {
		this.caractere = caractere;
		this.color = color;
	}

	/**
	 * Accesseur du caractere ASCII du PNJ.
	 * @return caractere representant le PNJ.
	 */
	public char getCaractere() {
		return this.caractere;
	}

	/**
	 * Accesseur de la couleur de PNJ.
	 * @return couleur du PNJ.
	 */
	public Color getColor() {
		return this.color;
	}
	/**
	 * called while attack
	 * @return degats caused by PNJ
	 */
	public int getDegats() {
		
		if(this == ZOMBIE) return 10;
		else if(this == SORCIER) return 5;
		else return 0;
	}

	
}
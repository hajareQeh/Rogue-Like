package fr.uvsq.M1.App.Rogue_Like.world;

import java.io.Serializable;

/**
 * Enumeration Weapon qui est une caracteristique a choisir
 * pour le joueur.
 */
public enum Weapon implements Serializable {
	/**
	 * Differents types d'armes.
	 */
	NO_WEAPON("no weapon"),
	SWORD("fire sword"),
	GUN("handgun"), 
	POISON("deadly poison"),

	/**
	 * Enumeration supplementaire pour connaitre le nombre d'armes au total.
	 */
	NB_WEAPON("null");

	/**
	 * Attribut Nom permettant de l'écrire dans le jeu.
	 */
	private String description;

	/**
	 * Accesseur de nom.
	 * @return nom de l'arme.
	 */
	private Weapon(String des) {
		this.description = des;
	}

	public String getDescription() {
		return description;
	}

	public int getDegats() {
		if(this == POISON) return 10;
		else if(this == SWORD) return 5;
		else if(this == GUN) return 5;
		else return 0;
	}

	
}

package fr.uvsq.M1.App.Rogue_Like.world;

import java.io.Serializable;

/**
 * Enumeration Sort qui est une caracteristique a choisir
 * pour le joueur.
 */
public enum Magic implements Serializable {
	NO_MAGIC("no magic"),
	TRAVEL("time travel"),
	MEGA_POWER("mega pawoer"),
	NB_MAGIC("null");
	/**
	 * Attribut Nom permettant de l'écrire dans le jeu
	 */
	private String nom;

	/**
	 * Accesseur de nom
	 * @return nom de l'arme
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Constructeur de Sort
	 * @param nom de l'arme
	 */
	private Magic(String nom) {
		this.nom = nom;
	}
}
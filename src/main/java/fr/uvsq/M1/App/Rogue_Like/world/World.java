package fr.uvsq.M1.App.Rogue_Like.world;

import java.awt.Color;
import java.io.Serializable;

/**
 * Classe World representant la map sur laquelle joue l'utilisateur.
 */
public class World implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Matrice representant la map du jeu.
	 */
	private Tile[][] tile;

	/**
	 * Longueur de la map.
	 */
	private int width;
	
	/**
	 * Largeur de la map.
	 */
	private int height;
	
	/**
	 * Constructeur de World.
	 * @param Tiles matrice representant le mode a creer.
	 */
	public World(Tile[][] Tiles){
		this.tile = Tiles;
		this.width = Tiles.length;
		this.height = Tiles[0].length;
	}

	/**
	 * Accesseur de la longueur de la map.
	 * @return longueur de la map.
	 */
	public int getWidth() { return width; }

	/**
	 * Accesseur de la largeur de la map.
	 * @return largeur de la map.
	 */
	public int getHeight() { return height; }

	/**
	 * Methode retournant la nature de la case en (x,y).
	 * @param x abscisse de la case.
	 * @param y ordonnee de la case.
	 * @return Tile representant la case.
	 */
	public Tile tile(int x, int y){
		if (x < 0 || x >= width || y < 0 || y >= height)
			return Tile.BOUNDS;
		else
			return tile[x][y];
	}

	/**
	 * Methode retournant le caractere ASCII representant la case (x,y).
	 * @param x abscisse de la case.
	 * @param y ordonnee de la case.
	 * @return char representant le caractere ASCII.
	 */
	public char glyph(int x, int y){
		return tile(x, y).getCaractere();
	}


	/**
	 * Methode retournant la couleur de la case (x,y).
	 * @param x abscisse de la case.
	 * @param y ordonnee de la case.
	 * @return couleur de la case.
	 */
	public Color color(int x, int y){
		return tile(x, y).getColor();
	}

	/**
	 * Trouve des coordonnees pour la creature etant libre.
	 * @param creature a rajouter sur la map.
	 */
	public void addAtEmptyLocation(Creature creature){
		int x;
		int y;

		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}
		while (!tile(x,y).isGround());

		creature.x = x;
		creature.y = y;
	}

	public Tile getPosition(int x,int y) {
		return tile[x][y];
	}
	
	public void affectationTile(int x,int y,Tile e) {
		tile[x][y]=e;
	}

}


package fr.uvsq.M1.App.Rogue_Like.world;

import java.awt.Color;
import java.io.Serializable;

/**
 * Class Creature represents the player and the PNJ.
 */
public class Creature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int life;
	/**
	 * World of Player or the PNJ.
	 */
	protected World world;

	/**
	 * Coordinates on the abscissa of the creature.
	 */
	public World getWorld() {
		return world;
	}

	public int x;

	/**
	 * Coordinates in ordonnee of the creature.
	 */
	public int y;

	/**
	 * Caracter represents the player or the PNJ.
	 */
	private char glyph;

	/**
	 * @return caractere represents the creature.
	 */
	public char getGlyph() { return glyph; }

	/**
	 * Color of creature.
	 */
	private Color color;

	/**
	 * @return color of the creature.
	 */
	public Color getColor() { 
		return color; 
	}

	/**
	 * Constructor of Creature.
	 * @param world where the creature where.
	 * @param glyph caractere of creature.
	 * @param color of creature.
	 * @param life life of the creature.
	 */
	public Creature(World world, char glyph, Color color, int life){
		this.world = world;
		this.glyph = glyph;
		this.life = life;
		this.color = color;
		world.addAtEmptyLocation(this);
	}

	/**
	 * @param x coordinates of the abscissa of the position of the box.
	 * @param y coordinates ordinates of the position of the cell.
	 * @param tile nature de la case.
	 */
	public void onEnter(int x, int y, Tile tile){
		if (tile.isGround()){
			this.x = x;
			this.y = y;
		} 
	}

	/**
	 * Deplacement de la creature.
	 * @param mx deplacement en abscisse de la creature.
	 * @param my deplacement en ordonnee de la creature.
	 */
	public void moveBy(int mx, int my){
		onEnter(x+mx, y+my, world.tile(x+mx, y+my));
	}

	public int getLife() {
		return life;
	}

	public void addLife(int life) {
		if(this.life + life <= 100)
			this.life += life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean testerDeplacement(int x, int y, Tile element){
		if (element.isGround()){
			this.x = x;
			this.y = y;
			return true;
		}
		return false;
	}

}
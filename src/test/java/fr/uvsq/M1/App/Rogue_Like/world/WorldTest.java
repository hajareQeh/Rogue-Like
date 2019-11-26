package fr.uvsq.M1.App.Rogue_Like.world;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests unitaires sur la classe World.
 */
public class WorldTest {

	private	World w;
	private Tile[][]Tiles;
	/**
	 * Permet d'initialiser les variables que l'on va tester. 
	 */
	@Before 
	public void initialize() {
		Tiles = new Tile[50][70];
		w = new World(Tiles);
	}
	
	/**
	 * Tests l'affectation de l'enumeration FLOOR à un element.
	 */
	@Test
	public void testaffectationTile() {
		w.affectationTile(1,1,Tile.FLOOR);
		assertEquals(w.getPosition(1,1),Tile.FLOOR);
	}

	/**
	 * Tests accesseur Width.
	 */
	@Test
	public void testWidth() {
		assertEquals(w.getWidth(),50);
	}
	
	/**
	 * Tests acceseur Height.
	 */
	@Test
	public void testHeigth() {
		assertEquals(w.getHeight(),70);
	}
}
package fr.uvsq.M1.App.Rogue_Like.world;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
/**
 * Tests unitaires sur la classe Creature.
 */
public class CreatureTest {

	private	World w;
	private Tile[][] tile;
	private Creature c;
	/**
	 * Permet d'initialiser les variables que l'on va tester. 
	 */
	@Before 
	public void initialize() {
		tile = new Tile[70][70];
		w= new World(tile);
		
		for(int i=0; i<70; i++) {
			for(int j=0;j<70;j++) {
				w.affectationTile(i,j,Tile.FLOOR);
			}
		}
		c = new Creature(w,'J',Color.BLACK,0);
		c.x=0;
		c.y=0;
		
	}
	/**
	 * Tests accesseur Couleur.
	 */
	@Test
	public void testGetColor() {		
		assertEquals(c.getColor(),Color.BLACK);
	}
	/**
	 * Tests accesseur Glyph.
	 */
	@Test
	public void testGetGlyph() {
		assertEquals(c.getGlyph(),'J');
	}
	/**
	 * Tests fonction de déplacement de créature.
	 */
	@Test
	public void testDéplacement() {
		c.moveBy(5, 6);
		assertEquals(c.x,5);
		assertEquals(c.y,6);
	}
	/**
	 * Tests de fonction qui cherche si un déplacement est possible
	 */
	@Test
	public void testSiDeplacement() {
	c.onEnter(1, 0, tile[0][1]);	
		assertEquals(c.getWorld().tile(0,1), Tile.FLOOR);
	}	
}


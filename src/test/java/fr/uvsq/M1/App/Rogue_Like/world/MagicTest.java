package fr.uvsq.M1.App.Rogue_Like.world;


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests unitaires sur la classe Sort.
 */
public class MagicTest {

    private Magic mg ;

    /**
     * Test accesseur nom NO_MAGIC
     */
    @Test
    public void testGetNomNoMagic() {
        mg=Magic.NO_MAGIC;
        assertEquals(mg.getNom(),"no magic");
    }

    /**
     * Test accesseur nom TRAVEL.
     */
    @Test
    public void testGetNomTravel() {
        mg=Magic.TRAVEL;
        assertEquals(mg.getNom(),"time travel");
    }

    /**
     * Test accesseur nom MEGA_POWER.
     */
    @Test
    public void testGetNomMEGA_POWER() {
        mg=Magic.MEGA_POWER;
        assertEquals(mg.getNom(),"mega pawoer");
    }

}

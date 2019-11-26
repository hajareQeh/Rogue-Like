package fr.uvsq.M1.App.Rogue_Like.world;


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests unitaires sur la classe Weapon.
 */
public class weaponTest {

    private Weapon wp;

    /**
     * Test accesseur nom no weapon.
     */
    @Test
    public void testGetNomNO_WEAPON() {
        wp=Weapon.NO_WEAPON;
        assertEquals(wp.getDescription(), "no weapon");
    }

    /**
     * Test accesseur nom fire sword.
     */
    @Test
    public void testGetNomSWORD() {
        wp=Weapon.SWORD;
        assertEquals(wp.getDescription(), "fire sword");
    }

    /**
     * Test accesseur nom handgun.
     */
    @Test
    public void testGetNomGUN() {
        wp=Weapon.GUN;
        assertEquals(wp.getDescription(), "handgun");
    }

    /**
     * Test accesseur nom deadly poison.
     */
    @Test
    public void testGetNomPOISON() {
        wp=Weapon.POISON;
        assertEquals(wp.getDescription(), "deadly poison");
    }

}
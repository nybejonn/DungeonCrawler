/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrpg.dungeonrpg.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonne
 */
public class EnemyTest {

    Enemy e;

    public EnemyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        e = new Enemy("e", 1, 2, 1, 2, 3, 3.5, 4, "");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void parryTest() {
        assertEquals("Not parried at beginning", false, e.getParried());
        e.parry();
        assertEquals("After parry(), parryied=true", true, e.getParried());
        e.unParry();
        assertEquals("Not parried after unParry()", false, e.getParried());
    }

    @Test
    public void scndParryTest() {
        int turnPointsAtStart = e.getTurnpoints();
        e.parry();
        assertEquals("One less turnpoints after parry", turnPointsAtStart - 1, e.getTurnpoints());
        e.parry();
        assertEquals("Two less turnpoints after second parry", turnPointsAtStart - 2, e.getTurnpoints());
        e.unParry();
        assertEquals("After unParry() turnpoints restored", turnPointsAtStart, e.getTurnpoints());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrpg.dungeonrpg.logic;

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
public class GameWorldTest {

    GameWorld wrld;

    public GameWorldTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        wrld = new GameWorld(5);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void moveLegalityTest1() {
        assertEquals("Can't move out of world", false, wrld.moveLegal(1, Math.floorDiv(wrld.getSize(), 2), wrld.getSize()));
        assertEquals("Can't move out of world", false, wrld.moveLegal(2, Math.floorDiv(wrld.getSize(), 2), 1));
        assertEquals("Can't move out of world", false, wrld.moveLegal(3, 1, Math.floorDiv(wrld.getSize(), 2)));
        assertEquals("Can't move out of world", false, wrld.moveLegal(4, wrld.getSize(), Math.floorDiv(wrld.getSize(), 2)));

    }

    @Test
    public void moveLegalityTest2() {
        assertEquals("Can move inside world", true, wrld.moveLegal(1, Math.floorDiv(wrld.getSize(), 2), Math.floorDiv(wrld.getSize(), 2)));
        assertEquals("Can move inside world", true, wrld.moveLegal(2, Math.floorDiv(wrld.getSize(), 2), Math.floorDiv(wrld.getSize(), 2)));
        assertEquals("Can move inside world", true, wrld.moveLegal(3, Math.floorDiv(wrld.getSize(), 2), Math.floorDiv(wrld.getSize(), 2)));
        assertEquals("Can move inside world", true, wrld.moveLegal(4, Math.floorDiv(wrld.getSize(), 2), Math.floorDiv(wrld.getSize(), 2)));
    }

    @Test
    public void moveLegalityTest3() {
        assertEquals("Can only move up,down,left or right", false, wrld.moveLegal(5, Math.floorDiv(wrld.getSize(), 2), Math.floorDiv(wrld.getSize(), 2)));
        assertEquals("Can only move up,down,left or right", false, wrld.moveLegal(-1, Math.floorDiv(wrld.getSize(), 2), Math.floorDiv(wrld.getSize(), 2)));
    }

    @Test
    public void checkMonstTest() {
        int X = wrld.getEnmies().get(0).getPosX();
        int Y = wrld.getEnmies().get(0).getPosY();
        String n = wrld.getEnmies().get(0).getName();
        assertEquals("Here should be a monster", n, wrld.checkForMonsters(X, Y).getName());
        assertEquals("Here should not be a monster", null, wrld.checkForMonsters(-100, -100));
    }

    @Test
    public void killTest() {
        int X = wrld.getEnmies().get(0).getPosX();
        int Y = wrld.getEnmies().get(0).getPosY();
        wrld.killEnmy(X, Y);
        assertEquals("Enemy should be out of world", wrld.getSize() + 100, wrld.getEnmies().get(0).getPosX());
    }
}

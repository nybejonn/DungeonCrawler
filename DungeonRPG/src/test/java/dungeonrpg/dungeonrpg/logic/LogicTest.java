/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Player;
import dungeonrpg.dungeonrpg.entities.Enemy;
import java.util.Scanner;
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
public class LogicTest {

    Logic l;
    Enemy e;
    Enemy e2;
    Enemy e3;

    public LogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        l = new Logic();
        e = new Enemy("", 1, 0, 2, 2, 0, 2.0, 2);
        e2 = new Enemy("", 0, 101, 2, 2, 100, 0, 2);
        e3 = new Enemy("",101,0,2,2,100,2.0,2);
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void moveLegalityTest1() {
        assertEquals("Can't move out of world", false, l.moveLegal(1, Math.floorDiv(l.getWrldSize(), 2), l.getWrldSize()));
        assertEquals("Can't move out of world", false, l.moveLegal(2, Math.floorDiv(l.getWrldSize(), 2), 1));
        assertEquals("Can't move out of world", false, l.moveLegal(3, 1, Math.floorDiv(l.getWrldSize(), 2)));
        assertEquals("Can't move out of world", false, l.moveLegal(4, l.getWrldSize(), Math.floorDiv(l.getWrldSize(), 2)));
        
    }
    @Test
    public void moveLegalityTest2(){
        assertEquals("Can move inside world", true, l.moveLegal(1, Math.floorDiv(l.getWrldSize(), 2), Math.floorDiv(l.getWrldSize(), 2)));
        assertEquals("Can move inside world", true, l.moveLegal(2, Math.floorDiv(l.getWrldSize(), 2), Math.floorDiv(l.getWrldSize(), 2)));
        assertEquals("Can move inside world", true, l.moveLegal(3, Math.floorDiv(l.getWrldSize(), 2), Math.floorDiv(l.getWrldSize(), 2)));
        assertEquals("Can move inside world", true, l.moveLegal(4, Math.floorDiv(l.getWrldSize(), 2), Math.floorDiv(l.getWrldSize(), 2)));
    }
    @Test
    public void moveLegalityTest3(){
        assertEquals("Can only move up,down,left or right",false,l.moveLegal(5,Math.floorDiv(l.getWrldSize(), 2), Math.floorDiv(l.getWrldSize(), 2)));
        assertEquals("Can only move up,down,left or right",false,l.moveLegal(-1,Math.floorDiv(l.getWrldSize(), 2), Math.floorDiv(l.getWrldSize(), 2)));
    }
    @Test
    public void plrTurnTest(){
        assertEquals("Quitting returns true", true,l.plrTurn("3", e));
        assertEquals("Enemy health = 0 returns true", true,l.plrTurn("1", e));
        assertEquals("Enemy health >0 returns false",false,l.plrTurn("1", e2));
    }
    @Test
    public void enmyTurnAlwaysEndsTest(){
        assertEquals("",false,l.enmyTurn(100, e2));
        assertEquals("",true,l.enmyTurn(100, e3));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Enemy;
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
    Enemy e4;

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
        //Enemy constructor (String name, int attackProb, int defProb, int posX, 
        //int posY, double health, double attack, int turnpoints)
        l = new Logic();
        e = new Enemy("", 1, 0, 2, 2, 0, 2.0, 2);
        e2 = new Enemy("", 0, 101, 2, 2, 100, 0, 2);
        e3 = new Enemy("", 101, 0, 2, 2, 100, 2.0, 2);
        e4 = new Enemy("", 0, 0, 2, 2, 100, 2.0, 2);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void plrAttTest() {
        assertEquals("Enemy health = 0 returns true", true, l.plrAtt(e));
        assertEquals("Enemy health >0 returns false", false, l.plrAtt(e2));
    }

    @Test
    public void plrTurnTest() {
        //4 = enemy dies, 5 = enemy takes dmg, 2 = attack fail, 7 = enemy is parried, 3 = defence fail
        assertEquals("Enemy dies --> return 4", 4, l.plrTurn(e, "1"));
        assertEquals("Enemy takes dmg but doesn't die ret 5", 5, l.plrTurn(e4, "1"));
        assertEquals("Enemy gets parried returns 7", 7, l.plrTurn(e4, "2"));
        assertEquals("Enemy resists parry returns 3", 3, l.plrTurn(e3, "2"));
    }
}

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
public class PlayerTest {

    Player plr;
    Loot l;

    public PlayerTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        plr = new Player();
        l = new Loot("l", 1, 1, 1, 1, 1, "!");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void moveTest() {
        int oldY = plr.getPosY();
        int oldX = plr.getPosX();
        plr.move(1);
        plr.move(4);
        assertEquals("Moving up adds 1 to y-coordinate", oldY + 1, plr.getPosY());
        assertEquals("Moving right adds 1 to x-coordinate", oldX + 1, plr.getPosX());
        plr.move(2);
        plr.move(3);
        assertEquals("Moving down takes 1 from y-coordinate", oldY, plr.getPosY());
        assertEquals("Moving left takes 1 from x-coordinate", oldX, plr.getPosX());
    }

    @Test
    public void takeDmgTest() {
        double hlthAtStart = plr.getHealth();
        plr.takeDmg(1);
        assertEquals("Take one damage decreases health by one", hlthAtStart - 1, plr.getHealth(), 0.01);
    }

    @Test
    public void takeDmg2Test() {
        plr.takeDmg(1000);
        assertEquals("Health never negative", 0, plr.getHealth(), 0.01);
    }

    @Test
    public void addLootTest() {
        Loot l1 = new Loot("a", 1, 1, 10, 0, 2, "!");
        plr.addLoot(l1);
        assertEquals("Loot in inventory", "a", plr.getLoot().get("a").getName());
        Loot l2 = new Loot("b", 1, 1, 10, 0, 2, "!");
        plr.addLoot(l2);
        assertEquals("Loot in inventory", "b", plr.getLoot().get("b").getName());
    }

    @Test
    public void equipTest() {
        Loot l1 = new Loot("a", 1, 1, 10, 0, 2, "!");
        Loot l2 = new Loot("b", 1, 1, 10, 0, 2, "!");
        plr.equip(null, null);
        assertEquals("Fist stays if nothing gets equipped", "Fist", plr.getItem2().getName());
        plr.equip(l1, l2);
        assertEquals("should be item1=a, item2=b", "a", plr.getItem1().getName());
        assertEquals("should be item1=a, item2=b", "b", plr.getItem2().getName());
    }

    @Test
    public void itemTest() {

        assertEquals("No change in attributes when no items equipped", plr.getItem1().getAttack() + plr.getAttack(), plr.getAttack(), 0.001);
        assertEquals("No change in attributes when no items equipped", plr.getItem1().getHealth() + plr.getHealth(), plr.getHealth(), 0.001);
        assertEquals("No change in attributes when no items equipped", plr.getItem1().getTurnpoints() + plr.getTurnpoints(), plr.getTurnpoints());

    }

    @Test
    public void itemTest2() {
        double h = plr.getHealth();
        double a = plr.getAttack();
        int t = plr.getTurnpoints();
        plr.equip(l, null);
        assertEquals("Items change attributes", h + l.getHealth(), plr.getHealth(), 0.001);
        assertEquals("Items change attributes", a + l.getAttack(), plr.getAttack(), 0.001);
        assertEquals("Items change attributes", t + l.getTurnpoints(), plr.getTurnpoints());
    }
}

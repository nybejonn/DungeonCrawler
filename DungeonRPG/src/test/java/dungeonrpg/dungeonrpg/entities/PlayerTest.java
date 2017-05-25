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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void moveTest(){
        int oldY = plr.getPosY();
        int oldX = plr.getPosX();
        plr.move(1);
        plr.move(4);
        assertEquals("Moving up adds 1 to y-coordinate", oldY + 1,plr.getPosY());
        assertEquals("Moving right adds 1 to x-coordinate", oldX + 1,plr.getPosX());
        plr.move(2);
        plr.move(3);
        assertEquals("Moving down takes 1 from y-coordinate", oldY,plr.getPosY());
        assertEquals("Moving left takes 1 from x-coordinate", oldX,plr.getPosX());
    }
    @Test
    public void takeDmgTest(){
        double hlthAtStart = plr.getHealth();
        plr.takeDmg(1);
        assertEquals("Take one damage decreases health by one",hlthAtStart-1,plr.getHealth(),0.01);
    }
    @Test
    public void takeDmg2Test(){
        plr.takeDmg(1000);
        assertEquals("Health never negative",0,plr.getHealth(),0.01);
    }
}

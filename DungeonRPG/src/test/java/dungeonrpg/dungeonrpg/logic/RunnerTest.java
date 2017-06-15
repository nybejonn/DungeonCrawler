package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Enemy;
import dungeonrpg.dungeonrpg.entities.Loot;
import dungeonrpg.dungeonrpg.ui.TestUI;
import dungeonrpg.dungeonrpg.ui.TextUi;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RunnerTest {

    Logic l;
    TestUI ui2;
    Enemy e;
    Enemy e2;
    Enemy e3;
    Loot loot;
    Runner r;

    public RunnerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        ui2 = new TestUI(1);
        l = new Logic();
        e = new Enemy("", 101, 101, 101, 101, 101, 100, 1000, "");
        e2 = new Enemy("", 0, 101, 2, 2, 100, 0, 2, "");
        e3 = new Enemy("", 0, 0, 0, 0, 0, 0, 0, "");
        loot = new Loot("test", 1, 1, 0, 0, 0, "");
        r = new Runner(ui2, l);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void enmyTurnTest() {
        assertEquals("", false, r.enmyTurn(e3));
        assertEquals("", true, r.enmyTurn(e));
    }

    @Test
    public void endTest() {
        assertEquals("", false, r.runGame());
    }

    @Test
    public void battleTest() {
        assertEquals("battle should be lost", true, r.battle(e));
    }

    @Test
    public void battleTest2() {
        assertEquals("battle should be won", false, r.battle(e3));
    }

    @Test
    public void lootTest() {
        r.loot(loot);
        assertEquals("Loot in inventory", "test", l.getPlr().getLoot().get("test").getName());
    }

    @Test
    public void moveTest() {
        assertEquals("Move works.", false, r.move(1));
        assertEquals("Move works.", false, r.move(4));
        assertEquals("Quit works", true, r.move(6));
    }

    @Test
    public void invCheckTest() {
        HashMap<String, Loot> m = new HashMap<>();
        m.put("test", loot);
        r.inventoryCheck(m);
        assertEquals("Choosing item to inventory works", "test", l.getPlr().getItem1().getName());
    }
}

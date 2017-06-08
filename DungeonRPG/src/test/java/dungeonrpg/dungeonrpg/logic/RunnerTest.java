package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Enemy;
import dungeonrpg.dungeonrpg.ui.TestUI;
import dungeonrpg.dungeonrpg.ui.TextUi;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RunnerTest {

    Logic l;
    TextUi ui;
    TestUI ui2;
    Enemy e;
    Enemy e2;
    Enemy e3;

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

        ui = new TextUi();
        ui2 = new TestUI(1);
        l = new Logic();
        e = new Enemy("", 101, 101, 101, 101, 101, 100, 100, "");
        e2 = new Enemy("", 0, 101, 2, 2, 100, 0, 2, "");
        e3 = new Enemy("", 0, 0, 0, 0, 0, 0, 0, "");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void enmyTurnTest() {
        Runner r = new Runner(ui, l);
        assertEquals("", false, r.enmyTurn(e3));
        assertEquals("", true, r.enmyTurn(e));
    }

    @Test
    public void endTest() {
        Runner r2 = new Runner(ui2, l);
        assertEquals("", false, r2.runGame());
    }
}

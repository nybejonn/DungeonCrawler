package dungeonrpg.dungeonrpg;

import dungeonrpg.dungeonrpg.logic.Logic;
import dungeonrpg.dungeonrpg.logic.Runner;
import dungeonrpg.dungeonrpg.ui.GUI;
import dungeonrpg.dungeonrpg.ui.TextUi;

public class DungeonCrawler {

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        Logic l = new Logic();
        TextUi tui = new TextUi();
        GUI gui = new GUI(l.getPlr().getPosX(), l.getPlr().getPosY(), l.getWrld());
        //Change between these to use graphical/text interface
        //Runner r = new Runner(tui,l)
        Runner r = new Runner(gui, l);
        r.runGame();
    }
}

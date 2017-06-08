package dungeonrpg.dungeonrpg;

import dungeonrpg.dungeonrpg.logic.Logic;
import dungeonrpg.dungeonrpg.logic.Runner;
import dungeonrpg.dungeonrpg.ui.TextUi;

public class DungeonCrawler {

    public static void main(String[] args) {
        Logic l = new Logic();
        TextUi ui = new TextUi();
        Runner r = new Runner(ui, l);
        r.runGame();
    }
}

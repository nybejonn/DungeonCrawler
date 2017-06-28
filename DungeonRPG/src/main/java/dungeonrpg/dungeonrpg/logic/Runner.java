package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Enemy;
import dungeonrpg.dungeonrpg.entities.Loot;
import dungeonrpg.dungeonrpg.ui.GameIF;
import java.util.HashMap;

/**
 * Class that runs the game logic methods.
 */
public class Runner {

    private Logic l;
    private GameIF ui;

    public Runner(GameIF ui, Logic l) {
        this.l = l;
        this.ui = ui;
    }

    public Logic getLogic() {
        return l;
    }

    /**
     * Method that runs the game methods in order.
     *
     * @return boolean for testing purposes
     */
    public boolean runGame() {
        boolean ko = false;
        if (ui.strt()) {
            while (!ko) {
                ko = battle(l.getWrld().checkForMonsters(l.getPlr().getPosX(), l.getPlr().getPosY()));
                if (ko) {
                    break;
                }
                loot(l.getWrld().checkForLoot(l.getPlr().getPosX(), l.getPlr().getPosY()));
                ko = move(ui.whereTo());
            }
        }
        return ko;
    }

    /**
     * Method that runs the battle portion of gameplay.
     *
     * @param enmy opponent
     * @return true if player dies, false otherwise
     */
    public boolean battle(Enemy enmy) {
        if (enmy != null) {
            ui.alert(6, enmy.getName(), 0);
            while (true) {
                int n = l.getPlr().getTurnpoints();

                l.getPlr().unParry();
                int k = 0;
                while (n > 0) {
                    ui.alert(16, null, n);
                    k = l.plrTurn(enmy, ui.battle());
                    ui.alert(k, enmy.getName(), 0);
                    if (k == 4) {
                        break;
                    }
                    n--;
                }
                if (k == 4) {
                    break;
                }
                if (enmyTurn(enmy)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds Loot objects to inventory if there are any in current position.
     *
     * @param loot item to be added
     */
    public void loot(Loot loot) {
        if (loot != null) {
            l.getPlr().addLoot(loot);
            loot.throwOffEdge(l.getWrld().getSize());
            ui.alert(14, loot.getName(), 0);
        }
    }

    /**
     * Movement portion of gameplay.
     *
     * @param d option that the user has chosen. 1,2,3,4 for movement,
     * 5=inventory, 6=quit.
     * @return true if d=6, false otherwise.
     */
    public boolean move(int d) {
        while (true) {
            if (d >= 1 && d <= 4) {
                if (l.movement(d)) {
                    break;
                }
                ui.alert(1, null, 0);
            } else if (d == 6) {
                return true;
            } else if (d == 5) {
                inventoryCheck(l.getPlr().getLoot());
            }
            d = ui.whereTo();
        }
        return false;
    }

    /**
     * Method for choosing Loot objects from inventory.
     *
     * @param loot HashMap of items in player's inventory.
     */
    public void inventoryCheck(HashMap<String, Loot> loot) {
        if (loot.isEmpty()) {
            ui.alert(15, null, 0);
        } else {
            String[] items = ui.inventory(loot.keySet());
            String i1 = items[0];
            String i2 = items[1];
            l.getPlr().equip(loot.get(i1), loot.get(i2));
        }
    }

    /**
     * Method for enemy's "AI".
     *
     * @param enmy
     * @return true if player dies and false otherwise.
     */
    public boolean enmyTurn(Enemy enmy) {
        int n = enmy.getTurnpoints();
        while (n > 0) {
            int k = l.enmyActn(enmy);
            if (k == 1 || k == 2) {
                ui.alert(11, enmy.getName(), 0);
                if (k == 1) {
                    if (l.getPlr().getHealth() == 0) {
                        ui.alert(8, enmy.getName(), 0);
                        return true;
                    } else {
                        ui.alert(9, enmy.getName(), 0);
                    }
                } else {
                    ui.alert(12, null, 0);
                }
            }
            if (k == 3) {
                ui.alert(13, enmy.getName(), 0);
                ui.alert(10, enmy.getName(), 0);
            }
            if (k == 4) {
                ui.alert(13, enmy.getName(), 0);
                ui.alert(12, enmy.getName(), 0);
            }
            n--;
        }
        enmy.unParry();
        return false;
    }
}

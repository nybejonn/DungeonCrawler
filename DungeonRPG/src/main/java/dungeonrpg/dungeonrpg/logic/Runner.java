package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Enemy;
import dungeonrpg.dungeonrpg.entities.Loot;
import dungeonrpg.dungeonrpg.ui.Interface;

/**
 * Class that runs the game logic methods. runGame method very long at the
 * moment, but will be split to submethods.
 */
public class Runner {

    private Logic l;
    private Interface ui;

    public Runner(Interface ui, Logic l) {
        this.l = l;
        this.ui = ui;
    }

    public Logic getLogic() {
        return l;
    }

    public boolean runGame() {
        boolean ko = false;
        if (ui.strt()) {

            while (!ko) {
                Enemy enmy = l.getWrld().checkForMonsters(l.getPlr().getPosX(), l.getPlr().getPosY());
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
                            ko = true;
                            break;
                        }
                    }
                }
                if (ko) {
                    break;
                }
                Loot loot = l.getWrld().checkForLoot(l.getPlr().getPosX(), l.getPlr().getPosY());
                if (loot != null) {
                    l.getPlr().addLoot(loot);
                    loot.throwOffEdge(l.getWrld().getSize());
                    ui.alert(14, loot.getName(), 0);
                }
                int d = ui.whereTo();
                while (true) {
                    if (d >= 1 && d <= 4) {
                        if (l.movement(d)) {
                            break;
                        }
                        ui.alert(1, null, 0);
                    } else if (d == 6) {
                        ko = true;
                        break;
                    } else if (d == 5) {
                        if (l.getPlr().getLoot().isEmpty()) {
                            ui.alert(15, null, 0);
                        } else {
                            String[] items = ui.inventory(l.getPlr().getLoot().keySet());
                            String i1 = items[0];
                            String i2 = items[1];
                            l.getPlr().equip(l.getPlr().getLoot().get(i1), l.getPlr().getLoot().get(i2));
                        }
                    }
                    d = ui.whereTo();
                }
            }
        }
        return ko;
    }

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

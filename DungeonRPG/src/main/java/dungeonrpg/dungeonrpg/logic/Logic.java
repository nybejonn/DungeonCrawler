package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Player;
import dungeonrpg.dungeonrpg.entities.Enemy;
import dungeonrpg.dungeonrpg.ui.Interface;

import java.util.ArrayList;
import java.util.Random;

public class Logic {

    private final Integer wrldSize;
    private Player plr;
    private Random rndm;
    private Interface ui;

    public Logic() {

        this.plr = new Player();
        this.wrldSize = 5;
        this.rndm = new Random();
        this.ui = new Interface();
    }

    public int getWrldSize() {
        return wrldSize;
    }

    public void runGame() {

        boolean GameOver = false;
        Enemy enmy = enmies().get(0);
        ui.alert(6, enmy.getName());
        while (!GameOver) {

            int plrPoints = plr.getTurnpoints();
            while (plrPoints > 0 && !GameOver) {
                GameOver = plrTurn(ui.battle(enmy.getName()), enmy);
                plrPoints--;
            }
            if (!GameOver) {

                plr.unParry();
                GameOver = enmyTurn(enmy.getTurnpoints(), enmy);
                enmy.unParry();
            }

        }
    }

    public boolean movement(int d) {
        if (moveLegal(d, plr.getPosX(), plr.getPosY())) {
            plr.move(d);
            return true;
        }
        return false;
    }

    public boolean plrTurn(String actn, Enemy enmy) {
        int x = rndm.nextInt(100) + 1;
        if (actn.equals("3")) {
            return true;
        }
        if (actn.equals("1")) {
            if (enmy.getDefProb() <= x) {
                if (enmy.takeDmg(plr.getAttack()) == 0) {
                    ui.alert(4, enmy.getName());
                    return true;
                } else {
                    ui.alert(5, enmy.getName());
                }
            } else {
                ui.alert(2, null);
            }
        }
        if (actn.equals("2") && enmy.getAttProb() <= x) {
            ui.alert(7, null);
            enmy.parry();
        } else if (actn.equals("2")) {
            ui.alert(3, null);
        }
        return false;
    }

    public boolean enmyTurn(int n, Enemy enmy) {
        boolean GameOver = false;
        while (n > 0) {

            if (rndm.nextInt(10) <= 6) {
                if (enmy.getAttProb() >= rndm.nextInt(100) + 1) {
                    if (plr.takeDmg(enmy.getAttack()) == 0) {
                        ui.alert(8, enmy.getName());
                        GameOver = true;
                        break;
                    }
                }
            } else {
                //vihulainen puolustaa
            }
            n--;
        }
        return GameOver;
    }

    public boolean moveLegal(int udlr, int posX, int posY) {
        int j = 0;
        if (udlr == 4 || udlr == 1) {
            j = 1;
        }
        if (udlr == 3 || udlr == 2) {
            j = -1;
        }
        if (udlr == 3 || udlr == 4) {
            if (posX + j <= this.wrldSize && posX + j >= 1) {
                return true;
            }
        }
        if (udlr == 1 || udlr == 2) {
            if (posY + j <= this.wrldSize && posY + j >= 1) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Enemy> enmies() {
        Enemy enmy = new Enemy("Enemy", 1, 1, 2, 2, 5, 2.0, 2);
        ArrayList<Enemy> enmies = new ArrayList();
        enmies.add(enmy);
        return enmies;
    }

    public Enemy checkForMonsters(int posX, int posY) {
        Enemy mnstr = null;
        for (Enemy enmy : enmies()) {
            if (enmy.getPosX() == posX && enmy.getPosY() == posY) {
                mnstr = enmy;
                break;
            }
        }
        return mnstr;
    }
}

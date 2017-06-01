package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Player;
import dungeonrpg.dungeonrpg.entities.Enemy;
import java.util.Random;

public class Logic {

    private GameWorld wrld;
    private Player plr;
    private Random rndm;

    public Logic() {
        this.wrld = new GameWorld(5);
        this.plr = new Player();
        this.rndm = new Random();
    }

    public GameWorld getWrld() {
        return this.wrld;
    }

    public Player getPlr() {
        return this.plr;
    }

    public boolean movement(int d) {

        if (wrld.moveLegal(d, plr.getPosX(), plr.getPosY())) {
            plr.move(d);
            return true;
        }
        return false;
    }

    public int plrTurn(Enemy enmy, String actn) {
        //4 = enemy dies, 5 = enemy takes dmg, 2 = attack fail, 7 = enemy is parried, 3 = defence fail
        if (actn.equals("1")) {
            if (plrAtt(enmy)) {
                if (enmy.getHealth() == 0) {
                    wrld.killEnmy(enmy.getPosX(), enmy.getPosY());
                    return 4;
                } else {
                    return 5;
                }
            }
            return 2;
        }
        if (plrDef(enmy)) {
            return 7;
        }
        return 3;
    }

    public boolean plrAtt(Enemy enmy) {
        if (enmy.getDefProb() <= rndm.nextInt(100) + 1) {
            enmy.takeDmg(plr.getAttack());
            return true;
        }
        return false;
    }

    public boolean plrDef(Enemy enmy) {
        if (enmy.getAttProb() <= rndm.nextInt(100) + 1) {
            enmy.parry();
            return true;
        }
        return false;
    }

    public int enmyActn(Enemy enmy) {
        //1=enemy attacks succesfully, 2 = enemy attack fails, 3 = enemy parries you, 4 = enemy def fail
        if (rndm.nextInt(10) <= 6) {
            if (enmy.getAttProb() >= rndm.nextInt(100) + 1) {
                plr.takeDmg(enmy.getAttack());
                return 1;
            } else {
                return 2;
            }
        }
        if (enmy.getDefProb() >= rndm.nextInt(100) + 1) {
            plr.parry();
            return 3;
        }
        return 4;
    }
}

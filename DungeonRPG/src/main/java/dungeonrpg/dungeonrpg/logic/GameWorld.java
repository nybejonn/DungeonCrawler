package dungeonrpg.dungeonrpg.logic;

import dungeonrpg.dungeonrpg.entities.Enemy;
import dungeonrpg.dungeonrpg.entities.Loot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Class that keeps track of in game entities.
 */
public class GameWorld {

    private Integer size;
    private ArrayList<Enemy> enmies;
    private HashMap<String, Loot> loot;
    private Random rndm;

    public GameWorld(int size) {
        this.rndm = new Random();
        this.enmies = new ArrayList();
        this.loot = new HashMap();
        this.size = size;
        birthMonsters();
        scatterLoot();
    }

    public int getSize() {
        return this.size;
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
            if (posX + j <= this.size && posX + j >= 1) {
                return true;
            }
        }
        if (udlr == 1 || udlr == 2) {
            if (posY + j <= this.size && posY + j >= 1) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Enemy> getEnmies() {

        return this.enmies;
    }

    public Enemy checkForMonsters(int posX, int posY) {
        Enemy mnstr = null;
        for (Enemy enmy : this.enmies) {
            if (enmy.getPosX() == posX && enmy.getPosY() == posY) {
                mnstr = enmy;
                break;
            }
        }
        return mnstr;
    }

    public Loot checkForLoot(int posX, int posY) {
        Loot luut = null;
        for (String k : this.loot.keySet()) {
            if (this.loot.get(k).getPosX() == posX && this.loot.get(k).getPosY() == posY) {
                luut = this.loot.get(k);
                break;
            }
        }
        return luut;
    }

    public void killEnmy(int posX, int posY) {
        this.enmies.stream().filter((enmy) -> (enmy.getPosX() == posX && enmy.getPosY() == posY)).forEach((enmy) -> {
            enmy.throwOffEdge(size);
        });
    }

    private void birthMonsters() {
        //Enemy constructor: (String name, int attackProb, int defProb, int posX, int posY, double health, double attack, int turnpoints, String sprite)

        Enemy bert = new Enemy("Zombie Bertrand Russell", 60, 30, 3, size, 10, 10, 3, "B");
        this.enmies.add(bert);
        boolean ok = true;
        for (int n = 0; n < Math.floorDiv(size * size, 4) + 1; n++) {
            int x = rndm.nextInt(size) + 1;
            int y = rndm.nextInt(size) + 1;
            for (Enemy e : this.enmies) {
                if (e.getPosX() == x && e.getPosY() == y) {
                    ok = false;
                }
            }
            if (ok && x % 2 == 0) {
                Enemy enmy = new Enemy("Tree Person", 40, 20, x, y, 3, 2.0, 2, "£");
                this.enmies.add(enmy);
            }
            if (ok && x % 3 == 0) {
                Enemy enmy = new Enemy("small piece of string", 50, 10, x, y, 4, 4, 1, "S");
                this.enmies.add(enmy);
            }
            if (ok && x % 5 == 0) {
                Enemy enmy = new Enemy("sentient piece of chocolate cake", 20, 60, x, y, 3, 4, 1, "¤");

                this.enmies.add(enmy);
            }
        }
    }

    private void scatterLoot() {

        Loot lngSwrd = new Loot("Longsword", rndm.nextInt(size) + 1, rndm.nextInt(size) + 1, 0, 2, 1, "!");
        Loot woodShield = new Loot("Wooden Shield", rndm.nextInt(size) + 1, rndm.nextInt(size) + 1, 3, 0, 0, "!");
        Loot bttleX = new Loot("Battle Axe", rndm.nextInt(size) + 1, rndm.nextInt(size) + 1, 1, 5, 2, "!");
        Loot ironShld = new Loot("Iron Shield", rndm.nextInt(size) + 1, rndm.nextInt(size) + 1, 10, 0, 2, "!");
        loot.put(bttleX.getName().toLowerCase(), bttleX);
        loot.put(ironShld.getName().toLowerCase(), ironShld);
        loot.put(lngSwrd.getName().toLowerCase(), lngSwrd);
        loot.put(woodShield.getName().toLowerCase(), woodShield);

    }
}

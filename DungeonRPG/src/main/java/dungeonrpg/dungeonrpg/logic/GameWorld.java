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

    private final Integer size;
    private final ArrayList<Enemy> enmies;
    private final HashMap<String, Loot> loot;
    private final Random rndm;
    /**
     * Constructor.
     * @param size size of the world.
     */
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

    public HashMap<String, Loot> getLoot() {
        return loot;
    }
    /**
     * Method checks if movement in user's indicated direction is allowed.
     * @param udlr the direction 1=up,2=down,3=left,4=right.
     * @param posX current x-coordinate.
     * @param posY current y-coordinate.
     * @return true if move is allowed and false otherwise.
     */
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
    /**
     * Method for checking current position for enemies.
     * @param posX current x-coordinate.
     * @param posY current y-coordinate.
     * @return class Enemy object if there is one
     */
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
    /**
     * Same as checkForMonsters but for Loot objects.
     * @param posX current x-coordinate.
     * @param posY current y-coordinate.
     * @return class Loot object if there is one
     */
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
    /**
     * Throws enemy out of world from position (posX,posY).
     * @param posX
     * @param posY 
     */
    public void killEnmy(int posX, int posY) {
        this.enmies.stream().filter((enmy) -> (enmy.getPosX() == posX && enmy.getPosY() == posY)).forEach((enmy) -> {
            enmy.throwOffEdge(size);
        });
    }
    /**
     * Creates enemies.
     */
    private void birthMonsters() {
        //Enemy constructor: (String name, int attackProb, int defProb, int posX,
        //int posY, double health, double attack, int turnpoints, String sprite)

        Enemy bert = new Enemy("Zombie Bertrand Russell", 60, 30, size, size, 10, 10, 3, "B");
        this.enmies.add(bert);

        Enemy t1 = new Enemy("Tree Person", 40, 20, 5, 5, 3, 2.0, 2, "£");
        this.enmies.add(t1);

        Enemy t2 = new Enemy("Tree Person", 40, 20, 1, 10, 3, 2.0, 2, "£");
        this.enmies.add(t2);

        Enemy g1 = new Enemy("Ultra-Dimensional Gabbage", 50, 10, 2, 6, 4, 4, 1, "S");
        this.enmies.add(g1);

        Enemy enmy3 = new Enemy("Man Bat", 20, 60, 9, 4, 3, 4, 1, "¤");
        this.enmies.add(enmy3);

    }
    /**
     * Creates Loot objects for the game.
     */
    private void scatterLoot() {

        Loot lngSwrd = new Loot("Longsword", 5, 5, 0, 2, 1, "!");
        Loot woodShield = new Loot("Wooden Shield", 1, 10, 3, 0, 0, "!");
        Loot bttleX = new Loot("Battle Axe", 1, 10, 1, 5, 2, "!");
        Loot ironShld = new Loot("Iron Shield", 2, 6, 10, 0, 2, "!");
        loot.put(bttleX.getName().toLowerCase(), bttleX);
        loot.put(ironShld.getName().toLowerCase(), ironShld);
        loot.put(lngSwrd.getName().toLowerCase(), lngSwrd);
        loot.put(woodShield.getName().toLowerCase(), woodShield);

    }
}

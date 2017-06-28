package dungeonrpg.dungeonrpg.entities;

import java.util.HashMap;

/**
 * Class that extends abstract class Entity. Pretty self-explanatory.
 */
public class Player extends Entity {

    private int exp;
    private boolean parried;
    private HashMap<String, Loot> loot;
    private Loot item1;
    private Loot item2;

    /**
     * constructor for abstract class: (int posX,int posY,int health,double
     * attack,int turnpoints,String sprite).
     */
    public Player() {
        super(1, 1, 5.0, 2, 2, "@");
        this.exp = 0;
        this.parried = false;
        this.loot = new HashMap();
        Loot fist = new Loot("Fist", 0, 0, 0, 0, 0, "f");
        this.item1 = fist;
        this.item2 = fist;
    }

    public Loot getItem1() {
        return item1;
    }

    public Loot getItem2() {
        return item2;
    }

    /**
     * Getters for health, attack and turn-points attributes are returned with
     * possible changes made by items the player has equipped.
     *
     * @return
     */
    @Override
    public double getHealth() {
        return this.health + item1.health + item2.health;
    }

    @Override
    public double getAttack() {
        return this.attack + item1.attack + item2.attack;
    }

    @Override
    public int getTurnpoints() {
        return this.turnpoints + item1.turnpoints + item2.turnpoints;
    }

    public boolean getParried() {
        return this.parried;
    }

    public int getExp() {
        return this.exp;
    }

    public HashMap<String, Loot> getLoot() {
        return this.loot;
    }

    /**
     * Method for moving player by changing coordinates accordingly.
     *
     * @param j direction of movement. 1=up, 2=down, 3=left,4=right.
     */
    public void move(int j) {
        if (j == 1) {
            this.posY++;
        }
        if (j == 2) {
            this.posY--;
        }
        if (j == 3) {
            this.posX--;
        }
        if (j == 4) {
            this.posX++;
        }
    }

    /**
     * Check class enemy.
     */
    public void parry() {
        this.parried = true;
        this.turnpoints--;
    }

    /**
     * Check class enemy.
     */
    public void unParry() {
        this.parried = false;
        this.turnpoints = this.turnpointsSafe;
    }

    /**
     *
     * @param loot found item to be added to inventory.
     */
    public void addLoot(Loot loot) {
        this.loot.put(loot.getName().toLowerCase(), loot);
    }

    /**
     * Player can have two Loot class objects equipped. This method is for that
     * purpose.
     *
     * @param itm1 Loot class object to be equipped.
     * @param itm2 Loot class object to be equipped.
     */
    public void equip(Loot itm1, Loot itm2) {
        if (itm1 != null) {
            item1 = itm1;
        }
        if (itm2 != null) {
            item2 = itm2;
        }
    }
}

package dungeonrpg.dungeonrpg.entities;

import java.util.ArrayList;
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

    public Player() {
        //constructor for abstract class: (int posX,int posY,int health,double attack,int turnpoints)
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

    public void parry() {
        this.parried = true;
        this.turnpoints--;
    }

    public void unParry() {
        this.parried = false;
        this.turnpoints = this.turnpointsSafe;
    }

    public void addLoot(Loot loot) {
        this.loot.put(loot.getName().toLowerCase(), loot);
    }

    public void equip(Loot itm1, Loot itm2) {
        if (itm1 != null) {
            item1 = itm1;
        }
        if (itm2 != null) {
            item2 = itm2;
        }
    }
}

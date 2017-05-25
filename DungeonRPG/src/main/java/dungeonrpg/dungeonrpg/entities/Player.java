package dungeonrpg.dungeonrpg.entities;

public class Player extends Entity {

    private double strength;
    private double defence;
    private int exp;
    private boolean parried;

    public Player() {
        //constructor for abstract class: (int posX,int posY,int health,double attack,int turnpoints)
        super(3, 1, 5.0, 2, 2);
        this.defence = 0;
        this.exp = 0;
        this.strength = 1;
    }

    public double getDefence() {
        return this.defence;
    }

    public double getStrength() {
        return this.strength;
    }

    public int getExp() {
        return this.exp;
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
}

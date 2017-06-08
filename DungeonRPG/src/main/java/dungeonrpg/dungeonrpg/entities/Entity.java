package dungeonrpg.dungeonrpg.entities;

/**
 * Abstract class for all entities in the game world.
 */
public abstract class Entity {

    protected String sprite;
    protected Integer posX;
    protected Integer posY;
    protected double health;
    protected double attack;
    protected Integer turnpoints;
    protected final Integer turnpointsSafe;

    public Entity(int posX, int posY, double health, double attack, int turnpoints, String sprite) {
        this.sprite = sprite;
        this.posX = posX;
        this.posY = posY;
        this.health = health;
        this.attack = attack;
        this.turnpoints = turnpoints;
        this.turnpointsSafe = turnpoints;
    }

    public String getSprite() {
        return sprite;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public double getHealth() {
        return this.health;
    }

    public double getAttack() {
        return this.attack;
    }

    public int getTurnpoints() {
        return this.turnpoints;
    }

    public double takeDmg(double dmg) {
        this.health = Math.max(this.health - dmg, 0);
        return this.health;
    }

    public void throwOffEdge(int edge) {
        this.posX = edge + 100;
    }
}

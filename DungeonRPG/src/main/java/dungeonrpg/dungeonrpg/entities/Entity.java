package dungeonrpg.dungeonrpg.entities;

public abstract class Entity {

    protected Integer posX;
    protected Integer posY;
    protected double health;
    protected double attack;
    protected Integer turnpoints;
    protected final Integer turnpointsSafe;
    protected boolean parried;

    public Entity(int posX, int posY, double health, double attack, int turnpoints) {
        this.posX = posX;
        this.posY = posY;
        this.health = health;
        this.attack = attack;
        this.turnpoints = turnpoints;
        this.parried = false;
        this.turnpointsSafe = turnpoints;
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

    public boolean getParried() {
        return this.parried;
    }

    public double takeDmg(double dmg){
        this.health = Math.max(this.health - dmg, 0);
        return this.health;
    }
    
    public void parry() {
        this.parried = true;
        this.turnpoints--;
    }
    public void unParry(){
        this.parried = false;
        this.turnpoints = this.turnpointsSafe;
    }
}

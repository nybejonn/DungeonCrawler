package dungeonrpg.dungeonrpg.entities;

/**
 * Class that extends abstract class Entity. Pretty self-explanatory. Attack is
 * now the probability for success.
 */
public class Enemy extends Entity {

    private final String name;
    private final int defProb;
    private final int attProb;
    private boolean parried;
    /**
     * Constructor.
     * @param name
     * @param attackProb
     * @param defProb
     * @param posX
     * @param posY
     * @param health
     * @param attack
     * @param turnpoints
     * @param sprite 
     */
    public Enemy(String name, int attackProb, int defProb, int posX, int posY, double health, double attack, int turnpoints, String sprite) {
        //attackProb ja defProb in range [1,100]
        super(posX, posY, health, attack, turnpoints, sprite);
        this.name = name;
        this.defProb = defProb;
        this.attProb = attackProb;
        this.parried = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getParried() {
        return this.parried;
    }

    public int getDefProb() {
        return this.defProb;
    }

    public int getAttProb() {
        return this.attProb;
    }
    /**
     * Method that puts enemy in state of parry. Lose one turn-point
     */
    public void parry() {
        this.parried = true;
        this.turnpoints--;
    }
    /**
     * Method for un-parrying after one turn.
     */
    public void unParry() {
        this.parried = false;
        this.turnpoints = this.turnpointsSafe;
    }
}

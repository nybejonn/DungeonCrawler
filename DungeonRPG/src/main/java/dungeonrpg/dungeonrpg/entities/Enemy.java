package dungeonrpg.dungeonrpg.entities;

public class Enemy extends Entity {

    private String name;
    private int defProb;
    private int attProb;

    public Enemy(String name, int attackProb, int defProb, int posX, int posY, double health, double attack, int turnpoints) {
        //attackProb ja defProb valilla [1,100]
        super(posX, posY, health, attack, turnpoints);
        this.name = name;
        this.defProb = defProb;
        this.attProb = attackProb;
    }
    public String getName(){
        return this.name;
    }
    public int getDefProb(){
        return this.defProb;
    }
    public int getAttProb(){
        return this.attProb;
    }
}

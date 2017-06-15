package dungeonrpg.dungeonrpg.entities;

/**
 * Class that extends abstract class Entity. Can be equipped by Player class,
 * for which it changes Attack, Health and Turn-points attributes.
 *
 */
public class Loot extends Entity {

    private final String name;

    public Loot(String name, int posX, int posY, int hlthBoost, double attackBoost, int turnPntsBoost, String sprite) {
        super(posX, posY, hlthBoost, attackBoost, turnPntsBoost, sprite);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

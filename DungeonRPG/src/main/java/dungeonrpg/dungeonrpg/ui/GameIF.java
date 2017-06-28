package dungeonrpg.dungeonrpg.ui;

import java.util.Set;

/**
 * Interface for game ui's.
 *
 * @author Jonne
 */
public interface GameIF {

    boolean strt();

    public int whereTo();

    public String battle();

    public void alert(int k, String name, int turnpoints);
    //k: 1 = wrong direction,2 = attack unsuccesful,3 = defence unsuccesful,
    //4 = defeat enemy, 5 = attack succsesfull, 6 = confrontation,7 = defence succesful,
    //8 = game over, 9 = enemy attack success, 10 = enemy def success, 11 = enemy attacks
    //12 = enemy attack fails, 13 = enemy defends, 14 = item found, 15 = empty invntory,
    //16 = how many turnpoints left 

    public String[] inventory(Set<String> inv);
}

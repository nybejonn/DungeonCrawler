package dungeonrpg.dungeonrpg.ui;

import java.util.Set;

/**
 * GameIF class for running tests on the Runner class.
 *
 */
public class TestUI implements GameIF {

    private Integer k;

    public TestUI(int k) {
        this.k = k;
    }

    @Override
    public boolean strt() {
        if (k == 1) {
            return false;
        }
        return true;
    }

    @Override
    public int whereTo() {
        return 1;
    }

    @Override
    public String battle() {
        return "1";
    }

    @Override
    public void alert(int k, String name, int turnpoints) {
    }

    @Override
    public String[] inventory(Set<String> inv) {
        String[] s = new String[2];
        for (String l : inv) {
            s[0] = l;
            break;
        }
        return s;
    }

}

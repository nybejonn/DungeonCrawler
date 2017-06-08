/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrpg.dungeonrpg.ui;

import java.util.Set;

/**
 *
 * @author Jonne
 */
public class TestUI implements Interface {

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String battle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alert(int k, String name, int turnpoints) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] inventory(Set<String> inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

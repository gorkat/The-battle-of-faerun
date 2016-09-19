package data;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tgork
 */
public class Nain extends Guerrier {
    private final int COEFF_DEFENSE = 2;

    public Nain() {
        super();
    }
    
    @Override
    public int subirDegats(int degats) {
        int degats_subis = degats / this.COEFF_DEFENSE;
        int new_HP_state = getPV() - degats_subis;
        
        setPV(new_HP_state);
        
        return degats_subis;
    }
    
    @Override
    public int getDefense() {
        return this.COEFF_DEFENSE;
    }
}

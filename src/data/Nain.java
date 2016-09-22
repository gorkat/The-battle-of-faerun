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
    public int getDefense() {
        return super.getDefense() * this.COEFF_DEFENSE;
    }
}

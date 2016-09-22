/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author tgorka
 */
public class Elfe extends Guerrier {
    private static int COEFF_ATTAQUE      = 2;
    private static final int COUT_DE_BASE = 2;

    public Elfe() {
        super();
    }
    
    @Override
    public int getForce() {
        return super.getForce() * COEFF_ATTAQUE;
    }
}

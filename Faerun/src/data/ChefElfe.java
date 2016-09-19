/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import utilitaires.LogAttaque;
import utilitaires.PlateauUtilitaire;

/**
 *
 * @author tgork
 */
public class ChefElfe extends Elfe {
    private static final int COEFF_ATTAQUE = 2;
    private final int COUT_DE_BASE         = 4;
    
    public ChefElfe() {
        super();
    }
    
    @Override
    public int getForce() {
        return super.getForce() * COEFF_ATTAQUE;
    }
    
    @Override
    public int getCOUT_DE_BASE() {
       return COUT_DE_BASE;
    }
}

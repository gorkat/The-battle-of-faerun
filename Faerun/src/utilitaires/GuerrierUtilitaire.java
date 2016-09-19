/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import data.Guerrier;

/**
 *
 * @author tgorka
 */
public class GuerrierUtilitaire {
    public static void printWarriorState(Guerrier guerrier) {
        String name = guerrier.getClass().getSimpleName();
        int PV      = guerrier.getPV();
        
        System.out.println(name + '[' + PV + "PV]");
    }
    
    public static void printlnCombat() {
        
    }
}

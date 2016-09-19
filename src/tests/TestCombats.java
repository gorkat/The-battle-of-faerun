/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import data.ChefElfe;
import data.Nain;
import utilitaires.LogAttaque;

/**
 *
 * @author tgork
 */
public class TestCombats {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nain n1 = new Nain();
        ChefElfe e1 = new ChefElfe();
        
        System.out.println("FIGHT !");
        
        while(e1.isAlive() && n1.isAlive()) {
            System.out.println("--------------------\n");
            
            LogAttaque attaque1 = e1.attaque(n1);
            System.out.println(attaque1.afficherCombat());
            
            LogAttaque attaque2 = n1.attaque(e1);
            System.out.println(attaque2.afficherCombat());
            
            System.out.println("\n");
        }
    }
    
}

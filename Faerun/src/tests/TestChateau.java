/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import data.Blasons;
import data.Chateau;
import data.ChefElfe;
import data.ChefNain;
import data.Elfe;
import data.Guerrier;
import data.Nain;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author tgorka
 */ 
public class TestChateau {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList recruesNovices = new LinkedList();
        recruesNovices.add(new Nain());
        recruesNovices.add(new Elfe());
        recruesNovices.add(new ChefElfe());
        recruesNovices.add(new ChefNain());
        recruesNovices.add(new Nain());
        
        ArrayList<Guerrier> soldats = new ArrayList<>();
        Chateau chateau             = new Chateau(Blasons.BLEU, recruesNovices);
        int tour                    = 1;
        
        while (soldats.size() != 5) {
            System.out.println("\n-------- Tour " + tour + "--------");
            
            soldats.addAll(chateau.entrainer());

            System.out.println("\n\nSoldats prêts : " + soldats.size());

            for ( Guerrier g : soldats) {
                System.out.println(g.getClass().getSimpleName());
            }
            
            chateau.genererRessource();
            tour++;
        }
    }
    
}

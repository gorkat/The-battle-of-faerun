/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import data.Blasons;
import data.Carreau;
import data.Chateau;
import data.ChefElfe;
import data.ChefNain;
import data.Elfe;
import data.Guerrier;
import data.Nain;
import data.Plateau;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        Chateau chateau = null;
        try {
            chateau = new Chateau(Blasons.BLEU, recruesNovices, new Carreau(0, new Plateau(5)));
        } catch (Plateau.PlateauInitException ex) {
            Logger.getLogger(TestChateau.class.getName()).log(Level.SEVERE, null, ex);
        }
        int tour = 1;
        
        while (chateau.getCampsDeBase().getGuerriersBleus().size() != 5) {
            System.out.println("\n-------- Tour " + tour + "--------");
            
            chateau.entrainer();

            System.out.println("\n\nSoldats prêts : " + chateau.getCampsDeBase().getGuerriersBleus().size());

            for (Guerrier g : chateau.getCampsDeBase().getGuerriersBleus()) {
                System.out.println(g.getClass().getSimpleName());
            }
            
            chateau.genererRessource();
            tour++;
        }
    }
    
}

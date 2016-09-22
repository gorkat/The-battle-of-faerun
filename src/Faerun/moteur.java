/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faerun;

import data.Blasons;
import data.ChefElfe;
import data.ChefNain;
import data.Elfe;
import data.Guerrier;
import data.Nain;
import data.Plateau;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tests.TestPlateau;
import utilitaires.LogAttaque;
import utilitaires.LogPlateau;

/**
 *
 * @author tgork
 */
public class moteur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        LinkedList<Guerrier> guerriersRouges = new LinkedList();
        guerriersRouges.add(new ChefElfe());
        
        LinkedList<Guerrier> guerriersBleus = new LinkedList();
        guerriersBleus.add(new ChefElfe());
        
        System.out.println("Création d'un plateau de 5 cases...");
        Plateau p = null;
        try {
            p = new Plateau(15);
        } catch (Plateau.PlateauInitException ex) {
            Logger.getLogger(TestPlateau.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(" Fait\n");
        
        LogPlateau log = new LogPlateau(p);

        Plateau.chateauRouge.ajouterRecrues(guerriersRouges);
        Plateau.chateauBleu.ajouterRecrues(guerriersBleus);
        
        int tours = 0;
        
        while (!Plateau.chateauBleu.defaite() && !Plateau.chateauRouge.defaite()) {
            System.out.println("\n------- tour " + tours + " -------\n");
            LinkedList<LogAttaque> combat = p.deplacement();

            combat.stream().forEach((attaque) -> {
                System.out.println(attaque.afficherCombat());
            });
            
            Plateau.chateauBleu.entrainer();
            Plateau.chateauRouge.entrainer();
            
            System.out.println(log.afficherPlateau());
            
            
            
            Plateau.chateauBleu.genererRessource();
            Plateau.chateauRouge.genererRessource();
            
            scan.nextLine();
            tours++;
        }
        
        if (Plateau.chateauBleu.defaite()) {
            System.out.println("Chateau" + Plateau.chateauRouge.getBlason() + " a gagné");
        } else {
            System.out.println("Chateau" + Plateau.chateauBleu.getBlason() + " a gagné");
        }
    }
}

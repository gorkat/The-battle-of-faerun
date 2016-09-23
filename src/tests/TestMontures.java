/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import data.Blasons;
import data.Carreau;
import data.ChevalDeGuerre;
import data.Guerrier;
import data.Nain;
import data.Plateau;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilitaires.LogAttaque;
import utilitaires.LogPlateau;

/**
 *
 * @author gorkat
 */
public class TestMontures {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Début du test sur les montures");
            
            System.out.println("Création d'un plateau");
            Plateau battlefield = new Plateau(15);
            
            System.out.println("Creation d'une unité montée Nain");
            Nain nainBaleze = new Nain();
            
            ChevalDeGuerre bucephal = new ChevalDeGuerre(nainBaleze);
            
            LinkedList<Guerrier> guerriersBleus = new LinkedList<>();
            guerriersBleus.add(bucephal);
            
            LinkedList<Guerrier> guerriers_rouges = new LinkedList<>();
            guerriers_rouges.add(new Nain());
            
            Scanner scanner = new Scanner(System.in);
            
            LogPlateau log = new LogPlateau(battlefield);
            
            Plateau.chateauBleu.ajouterRecrues(guerriersBleus);
            Plateau.chateauRouge.ajouterRecrues(guerriers_rouges);
            
            int tours = 0;
            
            while (!Plateau.chateauRouge.defaite()) {
                LinkedList<LogAttaque> combat = battlefield.deplacement();

                combat.stream().forEach((attaque) -> {
                    System.out.println(attaque.afficherCombat());
                });
                
                System.out.println(log.afficherPlateau());
                
                battlefield.chateauBleu.entrainer();
                battlefield.chateauRouge.entrainer();
                
                scanner.nextLine();
                tours++;
            }
            
        } catch (Plateau.PlateauInitException ex) {
            Logger.getLogger(TestMontures.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

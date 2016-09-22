/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import data.Blasons;
import data.Carreau;
import data.Plateau;
import java.util.LinkedList;

/**
 * A Class use to return a String representation of the battlefield's state.
 * @author tgorka
 */
public class LogPlateau {
    private final Plateau battlefield;

    public LogPlateau(Plateau battlefield) {
        this.battlefield = battlefield;
    }
    
    public String afficherPlateau() {
        LinkedList<Carreau> carreaux = battlefield.getCarreaux();
        
        LinkedList<String> etat_plateau = new LinkedList<>();
        etat_plateau.push(afficherChateau(Blasons.BLEU));
        
        carreaux.stream().map((Carreau c) -> {
            String etat_carreau = "---------- Case "
                    + (c.getNumero() + 1)
                    + " ----------\n";
            
            etat_carreau = c.getGuerriersBleus()
                    .stream()
                    .map((guerrier) -> guerrier.afficherEtat() + "\n")
                    .reduce(etat_carreau, String::concat);
            
            etat_carreau = c.getGuerriersRouges()
                    .stream()
                    .map((guerrier) -> guerrier.afficherEtat() + "\n")
                    .reduce(etat_carreau, String::concat);
            
            return etat_carreau;
        }).forEach((etat_carreau) -> {
            etat_plateau.push(etat_carreau);
        });
        
        etat_plateau.push(afficherChateau(Blasons.ROUGE));
        
        return etat_plateau.toString();
    }
    
    public String afficherChateau(Blasons couleur) {
        return "---------- Chateau"
                + couleur
                + " ------------\n";
    }
}

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
public class LogAttaque {
    private final Guerrier attaquant;
    private final Guerrier victime;
    private final int degatsDonnes;
    private final int degatsSubis;

    public LogAttaque(Guerrier attaquant, Guerrier victime) {
        this.attaquant = attaquant;
        this.victime   = victime;
        this.degatsDonnes = 0;
        this.degatsSubis  = 0;
    }
    
    public LogAttaque(Guerrier attaquant, Guerrier victime, int degatsDonnes, int degatsSubis) {
        this.attaquant    = attaquant;
        this.victime      = victime;
        this.degatsDonnes = degatsDonnes;
        this.degatsSubis  = degatsSubis;
    }
    
    /**
     * Return the combat's state
     * @return String
     */
    public String afficherCombat() {
        String mort;
        
        if (!getVictime().isAlive()) {
            // if victime does not survive to the attack
            // then print a message telling the player he's dead.
            mort = "\n" + this.afficherMort();
        } else if (!getAttaquant().isAlive()) {
            return "";
        } else {
            mort = "";
        }
        return "COMBAT : "
                + this.afficherEtatGuerrier(getAttaquant())
                + " tape "
                + this.afficherDegats()
                + this.afficherEtatGuerrier(getVictime())
                + mort;    
    }
    
    /**
     * Prints the warrior's class and its Life Points
     * @param guerrier
     * @return 
     */
    public String afficherEtatGuerrier(Guerrier guerrier) {
        return guerrier.getClass().getSimpleName()
                + '_'
                + guerrier.getFaction()
                + '[' 
                + guerrier.getPV() 
                + "PV]";
    }
    
    public String afficherDegats() {
        return '(' 
                + "degats donnés "
                + getDegatsDonnes()
                + " => degats subis -"
                + getDegatsSubis()
                + ") ";
    }
    
    public String afficherMort() {
        Guerrier winner,
                 looser;
        
        if (!getVictime().isAlive()) {
            looser = getVictime();
            winner = getAttaquant();
        } else {
            looser = getAttaquant();
            winner = getVictime();
        }
        
        
        return "MORT   : "
                + this.afficherEtatGuerrier(winner)
                + " tue "
                + this.afficherEtatGuerrier(looser);
    }

    public Guerrier getAttaquant() {
        return attaquant;
    }

    public Guerrier getVictime() {
        return victime;
    }

    public int getDegatsDonnes() {
        return degatsDonnes;
    }

    public int getDegatsSubis() {
        return degatsSubis;
    }
}

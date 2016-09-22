/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import utilitaires.LogAttaque;
import utilitaires.PlateauUtilitaire;

/**
 * A tile on the game board
 * @author tgorka
 */
public class Carreau {
    private final int numero;
    private final Plateau plateau;
    private final LinkedList<Guerrier> guerriers_rouges = new LinkedList<>();
    private final LinkedList<Guerrier> guerriers_bleus  = new LinkedList<>();
    
    /**
     * Constructeur de la classe Carreau
     * @param numero le numéro du Carreau sur le Plateau
     * @param p Le plateau de jeu.
     */
    public Carreau(int numero, Plateau p) {
        this.numero  = numero;
        this.plateau = p;
    }

    /**
     * Retourne vrai si le guerrier est un guerrier Rouge.
     * @param g Le guerrier à analyser
     * @return vrai si le guerrier est un guerrier Rouge, faux sinon.
     */
    public boolean estRouge(Guerrier g) {
        return g.getFaction() == Blasons.ROUGE;
    }
    
    /**
     * Makes Warriors of both teams on the same Carreau fighting
     * @return ArrayList A list of LogAttaque suming all actions
     * made by warriors during the fight.
     */
    public LinkedList<LogAttaque> combat() {
        int index_ennemi;
        
        LinkedList<LogAttaque> logs      = new LinkedList<>();
        LinkedList<Guerrier> combattants = new LinkedList<>();
        
        combattants.addAll(guerriers_bleus);
        combattants.addAll(guerriers_rouges);

        // For more fun
        Collections.shuffle(combattants);
        
        Iterator it = combattants.iterator();
        
        while (it.hasNext()) {
            Guerrier guerrier = (Guerrier) it.next();
            LinkedList<Guerrier> ennemis = guerrier.getFaction() == Blasons.ROUGE ? guerriers_bleus : guerriers_rouges;
            
            // If the warrior is alive and there are still ennemis
            if (guerrier.isAlive() && ennemis.size() > 0) {
                // Pick a random ennemi
                index_ennemi = PlateauUtilitaire.getRandom(ennemis.size());
                Guerrier victime = ennemis.get(index_ennemi);
                
                // Attack him
                LogAttaque log = guerrier.attaque(victime);

                // If ennemi isn't alive anymore, remove him from the ennemies team
                if (!victime.isAlive()) {
                    ennemis.remove(victime);
                }
                
                // Save combat log in the list
                logs.addLast(log);
            } else {
                // the current warrior is dead then we remove it from the list
                it.remove();
            }
        }
    
        return logs;
    }
    
    /**
     * Returns True if the warrior can move on the next Carreau.
     * This method prevents the warrior to move on the next Carreau
     * if its specialisation allows him to move over more than one Carreau
     * in a turn.
     * @param g The warrior to move.
     * @return True if the warriror can move, false otherwise.
     */
    public boolean peutAvancer(Guerrier g) {
        // If the Warrior belongs to RedCastle
        if (g.getFaction().equals(Blasons.ROUGE)) {
            // We ensure that this Carreau isn't the first 
            return !this.equals(plateau.getCarreaux().getFirst());
        } else {
            return !this.equals(plateau.getCarreaux().getLast());
        }
    }
    
    /**
     * Makes moving all Red warriors on the Carreau if they are allowed to.
     */
    public void avancerRouges() {       
        LinkedList<Guerrier> guerriers_to_remove = new LinkedList<>();
        
        guerriers_rouges.stream().map((guerrier) -> {
            if (peutAvancer(guerrier)) {
                Carreau next_carreau = plateau.getCarreau(this.numero - 1);
                guerrier.avancer(next_carreau);
            }
            return guerrier;
        }).filter((guerrier) -> (!guerrier.getPosition().equals(this))).forEach((guerrier) -> {
            guerriers_to_remove.add(guerrier);
        });
        
        guerriers_rouges.removeAll(guerriers_to_remove);
    }
    
    /**
     * Makes moving all Bleu warriors on the Carreau if they are allowed to.
     */
    public void avancerBleus() {
        LinkedList<Guerrier> guerriers_to_remove = new LinkedList<>();
        
        guerriers_bleus.stream().map((guerrier) -> {
            if (peutAvancer(guerrier)) {
                Carreau next_carreau = plateau.getCarreau(this.numero + 1);
                guerrier.avancer(next_carreau);
            }
            return guerrier;
        }).filter((guerrier) -> (!guerrier.getPosition().equals(this))).forEach((guerrier) -> {
            guerriers_to_remove.add(guerrier);
        });
        
        guerriers_bleus.removeAll(guerriers_to_remove);
    }

    public int getNumero() {
        return this.numero;
    }

    public LinkedList<Guerrier> getGuerriersRouges() {
        return guerriers_rouges;
    }

    public LinkedList<Guerrier> getGuerriersBleus() {
        return guerriers_bleus;
    }
    
    /**
     * Add a warrior to the Carreau in the right list.
     * @param blason The Warrior's faction
     * @param g the Warrior
     */
    public void addGuerrier(Blasons blason, Guerrier g) {
        if (estRouge(g)) {
            guerriers_rouges.add(g);
        } else {
            guerriers_bleus.add(g);
        }
    }
    
    /**
     * Add warriors to the Carreau. This method is mainly used to place
     * new warriors on the battlefield after they training.
     * @param couleur The Blason of the Warriors
     * @param guerriers A list of warriors to move on the Carreau.
     */
    public void addGuerriers(Blasons couleur, LinkedList<Guerrier> guerriers) {
        if (couleur == Blasons.ROUGE) {
            guerriers_rouges.addAll(guerriers);
        } else {
            guerriers_bleus.addAll(guerriers);
        }
    }

    /**
     * Makes warriors of the both teams to recovering they movement points.
     */
    void regenererGuerriers() {
        LinkedList<Guerrier> guerriers = new LinkedList<>();
        guerriers.addAll(guerriers_bleus);
        guerriers.addAll(guerriers_rouges);
        
        guerriers.stream().forEach((guerrier) -> {
            guerrier.repos();
        });
    }

    /**
     * Return True if some warriors of the both teams are on this Carreau
     * @return True if at least one warriror of the both teams are on this Carreau.
     */
    boolean estZoneDeCombat() {
        return this.guerriers_rouges.size() > 0 && this.guerriers_bleus.size() > 0;
    }
}

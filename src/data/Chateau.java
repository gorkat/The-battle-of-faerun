/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author tgorka
 */
public final class Chateau {
    private LinkedList<Guerrier> recruesNovices  = new LinkedList<>();
    private final int RESSOURCES_DE_BASE         = 3;
    private static final int RESSOURCES_PAR_TOUR = 1;
    private final Carreau CAMP_DE_BASE;
    private final Blasons blason;
    private int ressources;

    public Chateau(Blasons blason, LinkedList<Guerrier> nouvellesRecrues, Carreau camp_de_base) {
        this.ressources     = RESSOURCES_DE_BASE;
        this.CAMP_DE_BASE   = camp_de_base;
        this.blason         = blason;
        ajouterRecrues(nouvellesRecrues);
    }
    
    public Chateau(Blasons blason, Carreau camp_de_base) {
        this.blason     = blason;
        this.ressources = RESSOURCES_DE_BASE;
        this.CAMP_DE_BASE = camp_de_base;
    }
    
    /**
     * Trains recruits and send those who are ready to fight on the battlefield.
     */
    public void entrainer() {
        LinkedList<Guerrier> soldats_prets = new LinkedList<>();
        Iterator it = getRecruesNovices().iterator();
        
        // While castle has resources and there is at least one soldier in the list
        // or one soldier next
        while (this.getRessources() != 0 && it.hasNext()) {
            Guerrier recrue = (Guerrier) it.next();
            // while castle's resources is different of 0 and soldier is not ready
            while (this.getRessources() != 0 && !recrue.isReadyToFight()) {
                // Soldier eats a resource
                recrue.consommeRessources(1);
                // castle looses a resource
                this.setRessources(getRessources() - 1);
            }
            // if the previous While loop has ended because the soldier is ready
            if (recrue.isReadyToFight()) {
                // We remove the soldier from the list
                it.remove();
                // We add him to the ready soldiers list
                recrue.setPosition(CAMP_DE_BASE);
                soldats_prets.add(recrue);
            }
        }
        
        // end of training, all the ready soldiers are going to fight
        CAMP_DE_BASE.addGuerriers(getBlason(), soldats_prets);
    }

    public LinkedList<Guerrier> getRecruesNovices() {
        return recruesNovices;
    }

    private int getRessources() {
        return this.ressources;
    }

    public void setRessources(int ressources) {
        this.ressources = ressources;
    }

    /**
     * Makes the castle recovering one resource.
     */
    public void genererRessource() {
        setRessources(getRessources() + getRESSOURCES_PAR_TOUR());
    }

    public int getRESSOURCES_PAR_TOUR() {
        return RESSOURCES_PAR_TOUR;
    }

    public Blasons getBlason() {
        return this.blason;
    }

    public Carreau getCampsDeBase() {
        return CAMP_DE_BASE;
    }   
  
    /**
     * Add recruits to the recruits list.
     * @param nouvellesRecrues 
     */
    public void ajouterRecrues(LinkedList<Guerrier> nouvellesRecrues) {
        nouvellesRecrues.stream().map((guerrier) -> {
            guerrier.affecterFaction(this.getBlason());
            return guerrier;
        }).forEach((guerrier) -> {
            recruesNovices.add(guerrier);
        });
    }

    /**
     * Returns true if the castle is being defeated.
     * @return True if an ennemi unit is on the base camp and no ally is present. False otherwise. 
     */
    public boolean defaite() {
        if (getBlason() == Blasons.ROUGE) {
            return CAMP_DE_BASE.getGuerriersRouges().isEmpty()
                    && CAMP_DE_BASE.getGuerriersBleus().size() >= 1;
        }
        
        return CAMP_DE_BASE.getGuerriersBleus().isEmpty()
                && CAMP_DE_BASE.getGuerriersRouges().size() >= 1;
    }
}

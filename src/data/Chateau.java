/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author tgorka
 */
public class Chateau {
    private LinkedList<Guerrier> recruesNovices = new LinkedList<>();
    private final int RESSOURCES_DE_BASE        = 3;
    private final int RESSOURCES_PAR_TOUR       = 1;
    private final Blasons blason;
    private int ressources;

    public Chateau(Blasons blason, LinkedList<Guerrier> nouvellesRecrues) {
        this.ressources     = RESSOURCES_DE_BASE;
        this.blason         = blason;
        ajouterRecrues(nouvellesRecrues);
    }
    
    public Chateau(Blasons blason) {
        this.blason = blason;
    }
    
    public ArrayList<Guerrier> entrainer() {
        ArrayList<Guerrier> soldats_prets = new ArrayList<>();
        Iterator it = getRecruesNovices().iterator();
        
        System.out.println("Ressources du Chateau" + this.blason + " : " + getRessources());
        
        // While castle has resources and there is at least one soldier in the list
        // or one soldier next
        while (getRessources() != 0 && it.hasNext()) {
            Guerrier recrue = (Guerrier) it.next();
            System.out.println("\tEntrainement d'un "
                                + recrue.getClass().getSimpleName()
                                + '_'
                                + recrue.getFaction()
                                + " (reste "
                                + (recrue.getCOUT_DE_BASE() - recrue.getRessources())
                                + " ressources pour aller combattre);"
            );
            // while castle's resources is different of 0 and soldier is not ready
            while (this.getRessources() != 0 && !recrue.isReadyToFight()) {
                System.out.println("\tConsomme une ressource");
                // Soldier eats a resource
                recrue.consommeRessources(1);
                // castle looses a resource
                this.setRessources(getRessources() - 1);
                System.out.println("\tNécessite encore "
                                    + (recrue.getCOUT_DE_BASE() - recrue.getRessources())
                                    + " ressources "
                );
            }
            // if the previous While loop has ended because the soldier is ready
            if (recrue.isReadyToFight()) {
                System.out.println("Recrue "
                                    + recrue.getClass().getSimpleName()
                                    + '_'
                                    + recrue.getFaction()
                                    + " est prête au combat"
                );
                // We remove the soldier from the list
                it.remove();
                // We add him to the ready soldiers list
                soldats_prets.add(recrue);
            }
            
            System.out.println("Ressources du Chateau : " + getRessources());
        }
        
        // end of training, all the ready soldiers are going to fight
        return soldats_prets;
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

    public void genererRessource() {
        setRessources(getRessources() + getRESSOURCES_PAR_TOUR());
    }

    public int getRESSOURCES_PAR_TOUR() {
        return RESSOURCES_PAR_TOUR;
    }

    public Blasons getBlason() {
        return this.blason;
    }

    private void ajouterRecrues(LinkedList<Guerrier> nouvellesRecrues) {
        for (Guerrier guerrier : nouvellesRecrues) {
            guerrier.affecterFaction(this.getBlason());
            recruesNovices.add(guerrier);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import utilitaires.LogAttaque;

/**
 *
 * @author tgorka
 */
public class Plateau {
    private final LinkedList<Carreau> carreaux = new LinkedList<>();
    
    public static Chateau chateauRouge;
    public static Chateau chateauBleu;

    public Plateau(int nb_carreaux) throws PlateauInitException {
        if (nb_carreaux % 5 != 0 || nb_carreaux > 15) {
            throw new PlateauInitException();
        }
        
        initPlateau(nb_carreaux);
    }

    public LinkedList<Carreau> getCarreaux() {
        return this.carreaux;
    }
    
    public Carreau getCarreau(int numero) {
        return getCarreaux().get(numero);
    }
    
    private void initPlateau(int nb_carreaux) {
        while (carreaux.size() != nb_carreaux) {
            carreaux.add(new Carreau(carreaux.size(), this));
        }
        
        chateauBleu  = new Chateau(Blasons.BLEU, this.getCarreaux().getFirst());
        chateauRouge = new Chateau(Blasons.ROUGE, this.getCarreaux().getLast());
    }

    public static class PlateauInitException extends Exception {

        public PlateauInitException() {
            System.out.println("Le nombre de cases doit être un multiple de 5 et inférieur à 15!");
        }
    }
    
    public void affecterRecrues(LinkedList<Guerrier> recrues, Blasons chateau) {
        if (chateau == Blasons.ROUGE) {
            this.chateauRouge.ajouterRecrues(recrues);
        } else {
            this.chateauBleu.ajouterRecrues(recrues);
        }
    }
    
    /**
     * Makes the warriors moving if they can and fight if they have to.
     * @return 
     */
    public LinkedList<LogAttaque> deplacement() {
        LinkedList<LogAttaque> logs = new LinkedList<>();
        Iterator it                 = carreaux.iterator();
        Iterator reverseIt          = carreaux.descendingIterator();
        
        while (it.hasNext() && reverseIt.hasNext()) {
            Carreau cit = (Carreau) it.next();
            Carreau creverse = (Carreau) reverseIt.next();

            if (cit.estZoneDeCombat() || creverse.estZoneDeCombat()) {
                LinkedList<LogAttaque> combat = cit.combat();
                logs.addAll(combat);
            } else {
                cit.avancerBleus();
                creverse.avancerRouges();
            }
        }
        
        carreaux.stream().forEach((c) -> {
            c.regenererGuerriers();
        });
        
        return logs;
    }
}

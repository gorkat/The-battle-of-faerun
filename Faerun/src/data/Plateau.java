/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author tgorka
 */
public class Plateau {
    private ArrayList<Carreau> carreaux = new ArrayList<>();

    public Plateau(int nb_carreaux) throws PlateauInitException {
        if (nb_carreaux % 5 != 0 && nb_carreaux > 15) {
            throw new PlateauInitException();
        }
        
        initPlateau(nb_carreaux);
    }

    private void initPlateau(int nb_carreaux) {
        while (carreaux.size() != nb_carreaux) {
            carreaux.add(new Carreau());
        }
    }

    private static class PlateauInitException extends Exception {

        public PlateauInitException() {
            System.out.println("Le nombre de cases doit être un multiple de 5 !");
        }
    }
    
    
}

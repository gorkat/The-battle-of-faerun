package utilitaires;

import java.util.Random;

public class PlateauUtilitaire {
    // Attribut contenant un objet de type random
    private static final Random RANDOM = new Random();
    // Méthode qui simule le lancement d’un dé de 3 faces
    // et retourne le résultat
    public static int De3() {
        return RANDOM.nextInt(3)+1;
    }
    // Méthode qui simule plusieurs lancés d’un dé et retourne le résultat
    public static int De3(int nombreDes) {
        int somme = 0;
        
        for (int i = 0; i < nombreDes; i++) {
            somme = somme + De3();
        }
        
        return somme;
    }
    
    /**
     * Returns a random integer in a given interval.
     * @param interval
     * @return a random integer in a given interval.
     */
    public static int getRandom(int interval) {
        return RANDOM.nextInt(interval);
    }
}
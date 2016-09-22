/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import utilitaires.LogAttaque;
import utilitaires.PlateauUtilitaire;
/**
 *
 * @author tgorka
 */
public abstract class Guerrier {
    private final int COUT_DE_BASE = 1;
    private final int DEFENSE      = 1;
    private final int DEPLACEMENT  = 1;
    private final int FORCE        = 10;
    private int PV                 = 100;
    private Blasons faction        = Blasons.NEUTRE;
    private Carreau position;
    private int ressources;
    private int points_deplacement;

    public Guerrier() {
        this.points_deplacement = this.DEPLACEMENT;
    }

    public int getForce() {
        return FORCE;
    }

    public int getPV() {
        return PV;
    }
    
    public int getDefense() {
        return DEFENSE;
    }
    
    public int getCOUT_DE_BASE() {
        return COUT_DE_BASE;
    }
    
    private int getDeplacementBase() {
        return this.DEPLACEMENT;
    }
    
    public int getRessources() {
        return ressources;
    }

    public int getPoints_deplacement() {
        return points_deplacement;
    }

    public Blasons getFaction() {
        return this.faction;
    }
    
    /**
     * Retourne le carreau sur lequel se trouve le guerrier.
     * @return le carreau sur lequel se trouve le guerrier.
     */
    public Carreau getPosition() {
        return this.position;
    }
    
    protected void setPV(int new_pvs_state) {
        // if new_pvs_state is less than 0
        if (new_pvs_state < 0) {
            // PVs set to 0 (no negative value allowed)
            this.PV = 0;
        } else {
            // else PVs set to new_pvs_state
            this.PV = new_pvs_state;
        }
    }
    
    public void setPoints_deplacement(int points_deplacement) {
        this.points_deplacement = points_deplacement;
    }

    protected void setRessources(int ressources) {
        this.ressources = ressources;
    }
    
    /**
     * Affecte le guerrier à une faction.
     * @param blason la couleur à affecter au guerrier.
     */
    public void affecterFaction(Blasons blason) {
        this.faction = blason;
    }
    
    /**
     * Change le carreau sur lequel se trouve le guerrier.
     * @param position la nouvelle position du guerrier.
     */
    public void setPosition(Carreau position) {
        this.position = position;
    }

    /**
     * Makes the Warriror consuming a resource on training phase.
     * @param ressources the number of resource to consume.
     */
    public void consommeRessources(int ressources) {
        setRessources(getRessources() + ressources);
    }
    
    /**
     * Return true when the warrior has consumed enough resources to go fighting.
     * @return 
     */
    public boolean isReadyToFight() {
        return getRessources() == getCOUT_DE_BASE();
    }
    
    /**
     * @return true if PVs are greater than 0
     */
    public boolean isAlive() {
        return this.getPV() > 0;
    }
    
    /**
     * Makes the warrior undergoing damages.
     * @param degats the damages given to the Warrior
     * @return actual damages
     */
    public int subirDegats(int degats) {
        int degats_effectifs = degats / this.getDefense();
        int new_PV_state     = this.getPV() - degats_effectifs;
                    
        setPV(new_PV_state);
        
        return degats_effectifs;
    }
    
    /**
     * Makes the warrior attacking his ennemi
     * @param ennemi the ennemi to attack
     * @return LogAttaque
     */
    public LogAttaque attaque(Guerrier ennemi) {
        int degats,
            degatsSubis;
        
        // If the warriors are both alive
        if (this.isAlive() && ennemi.isAlive()) {
            // Damages calculationgetForce
            degats      = PlateauUtilitaire.De3(this.getForce());
            // Apply damages to the ennemi
            degatsSubis = ennemi.subirDegats(degats);
 
            LogAttaque log  = new LogAttaque(this, ennemi, degats, degatsSubis);
            
            return log;
        }

        return new LogAttaque(this, ennemi);
    }
    
    /**
     * Regenère les points de déplacement du guerrier.
     */
    public void repos() {
        setPoints_deplacement(this.getDeplacementBase());
    }
    
    
    /**
     * Si le guerrier possède assez de points de déplacement,
     * il avance sur le carreau passé en paramètre.
     * @param c le carreau sur lequel le guerrier doit se déplacer
     */
    public void avancer(Carreau c) {
        if (getPoints_deplacement() >= 1) {
            c.addGuerrier(this.getFaction(), this);
            setPosition(c);
            setPoints_deplacement(getPoints_deplacement() - 1);
        }
    }
    
    /**
     * Affiche l'état du guerrier
     * @return String L'état du guerrier sous forme de chaine de caractère.
     */
    public String afficherEtat() {
        return LogAttaque.afficherEtatGuerrier(this);
    }
}

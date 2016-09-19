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
    private final int FORCE        = 10;
    private int PV                 = 100;
    private Blasons faction        = Blasons.NEUTRE;
    private int ressources;

    public Guerrier() {}

    public int getForce() {
        return FORCE;
    }

    public int getPV() {
        return PV;
    }
    
    public int getDefense() {
        return 1;
    }
    
    public void consommeRessources(int ressources) {
        setRessources(getRessources() + ressources);
    }
    
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
     * @param degats the damages given to the Warrior
     * @return actual damages
     */
    public int subirDegats(int degats) {
        int new_PV_state = this.getPV() - degats;
                    
        setPV(new_PV_state);
        
        return degats;
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
            // Damages calculation
            degats      = PlateauUtilitaire.De3(this.getForce());
            // Apply damages to the ennemi
            degatsSubis = ennemi.subirDegats(degats);
 
            LogAttaque log  = new LogAttaque(this, ennemi, degats, degatsSubis);
            
            return log;
        }

        return new LogAttaque(this, ennemi);
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

    public int getCOUT_DE_BASE() {
        return COUT_DE_BASE;
    }
    
    public int getRessources() {
        return ressources;
    }

    protected void setRessources(int ressources) {
        this.ressources = ressources;
    }
    
    public void affecterFaction(Blasons blason) {
        this.faction = blason;
    }
    
    public Blasons getFaction() {
        return this.faction;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author gorkat
 */
public class ChevalDeGuerre extends Monture {
    private final int DEPLACEMENT = 2;
    private final int COUT_DE_BASE;
    
    public ChevalDeGuerre(Guerrier cavalier) {
        super(cavalier);
        this.COUT_DE_BASE = cavalier.getCOUT_DE_BASE() + 1;
    }

    public Guerrier getCavalier() {
        return cavalier;
    }

    @Override
    public int getDeplacementBase() {
        return DEPLACEMENT;
    }

    @Override
    public Blasons getFaction() {
        return getCavalier().getFaction();
    }

    @Override
    public void affecterFaction(Blasons blason) {
        getCavalier().affecterFaction(blason); 
    }

    @Override
    public int getCOUT_DE_BASE() {
        return COUT_DE_BASE;
    }

    @Override
    public int getForce() {
        return getCavalier().getForce();
    }

    @Override
    public boolean isAlive() throws MontureMorteException {
        if (this.getPV() > 0) {
            return true;
        }
        
        throw new MontureMorteException(this);
    }

    @Override
    public String getWarriorName() {
        return cavalier.getWarriorName()
                + "_sur_"
                + super.getWarriorName();
    }
    
}

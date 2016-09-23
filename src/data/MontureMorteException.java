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
public class MontureMorteException extends Exception {
    private Guerrier cavalier;
    private Monture monture;
    
    public MontureMorteException(Monture monture) {
        this.cavalier = monture.getCavalier();
        this.monture  = monture;
    }

    public Guerrier getCavalier() {
        return this.cavalier;
    }
    
    public Guerrier getMonture() {
        return this.monture;
    }
    
}

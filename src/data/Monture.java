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
public abstract class Monture extends Guerrier {
    protected Guerrier cavalier;

    public Monture(Guerrier cavalier) {
        this.cavalier = cavalier;
    }

    public abstract Guerrier getCavalier();
}

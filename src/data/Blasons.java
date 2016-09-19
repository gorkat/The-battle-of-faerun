/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author tgork
 */
public enum Blasons {
    BLEU("\033[34mbleu\033[0m"),
    ROUGE("\033[31mrouge\033[0m"),
    NEUTRE("neutre");
    
    private String name = "";
    
    Blasons(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
}

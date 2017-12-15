/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;
import java.util.ArrayList;
import Parser.Type.*;

/**
 *
 * @author givaudav
 */
public class Environnement {
    private ArrayList<EnvCouple> e;
    
    public Environnement() {
        this.e = new ArrayList();
        this.ajout("print_string", new TTuple(new TArray(), new TUnit()));
        this.ajout("print_int", new TTuple(new TInt(), new TUnit()));
    }
    
    public void ajout(String id, Type t) {
        System.out.println("Ajout de " + id + " de type " + t.getClass().toString());
        this.e.add(new EnvCouple(id, t));
    }
    
    public Type getTypeById(String id) {
        System.out.println("Recherche de " + id.toString());
        for (int i = 0; i<this.e.size(); i++) {
            if(e.get(i).getId().equals(id)) {
               return e.get(i).getType();
            }
        }
        return null; //GERER LES EXCEPTIONS
    }
    
    public void afficher() {
        System.out.println("Affichage du contenu de l'environnement :");
        for (int i = 0; i<this.e.size(); i++) {
            System.out.println(this.e.get(i).getId() + " -> " + this.e.get(i).getType());
        }
    }
}

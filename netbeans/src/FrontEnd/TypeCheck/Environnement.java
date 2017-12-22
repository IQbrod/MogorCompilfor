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
        ArrayList<Type> arr = new ArrayList();
        arr.add(new TString());
        arr.add(new TUnit());
        this.ajout("print_string", new TTuple(arr));
        arr.clear();
        arr.add(new TInt());
        arr.add(new TUnit());
        this.ajout("print_int", new TTuple(arr));
    }
    
    public Environnement(ArrayList<EnvCouple> arr) {
        this.e = arr;
    }
    
    public void ajout(String id, Type t) {
            this.e.add(new EnvCouple(id, t));
    }
    
    public Type getTypeById(String id) {
        for (int i = this.e.size()-1 ; i>=0 ; i--) {
            if(e.get(i).getId().equals(id)) {
               return e.get(i).getType();
            }
        }
        return null;
    }
    
    public void afficher() {
        System.out.println("Affichage du contenu de l'environnement :");
        for (int i = 0; i<this.e.size(); i++) {
            System.out.println(this.e.get(i).getId() + " : " + this.e.get(i).getType());
        }
    }
    
    public ArrayList<EnvCouple> getE() {
        return this.e;
    }
}

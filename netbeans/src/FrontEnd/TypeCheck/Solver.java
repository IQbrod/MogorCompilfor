/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;

import Parser.Type.*;
import static java.lang.System.exit;
import java.util.ArrayList;

/**
 *
 * @author givaudav
 */
public class Solver {
    private ArrayList<Equation> arr;
    
    public Solver(ArrayList<Equation> arr) {
        this.arr = arr;
    }
    
    private ArrayList<Equation> resolution(ArrayList<Equation> list) {
        if (list.isEmpty()) {
            return list;
        } else {
            Equation e = list.get(0);
            System.out.println(e.getT1().toString() + " = " + e.getT2().toString());
            if (!(e.isUnifiable())) {
                System.err.println("Programme mal typé non unifiable");
                exit(1);
            } else if (e.isEqual()) {
                list.remove(0);
                return resolution(list);
            } else if (e.getT1() instanceof TTuple && e.getT2() instanceof TTuple) {
                TTuple t1 = (TTuple)e.getT1();
                TTuple t2 = (TTuple)e.getT2();
                if (t1.ts.size() != t2.ts.size()) {
                    System.err.println("Programme mal typé tuple non unifiables");
                    exit(1);
                } else {
                    list.remove(0);
                    for (int i = 0; i<t1.ts.size(); i++) {
                        list.add(new Equation(t1.ts.get(i), t2.ts.get(i)));
                    }
                    return resolution(list);
                }
            } else if (e.getT1() instanceof TVar) {
                for (int i = 0; i<list.size(); i++) {
                    if (list.get(i).getT1() == e.getT1()) {
                        list.get(i).setT1(e.getT1());
                    }
                    if (list.get(i).getT2() == e.getT1()) {
                        list.get(i).setT2(e.getT1());
                    }
                }
                return list;
                //return resolution(list);
            } else {
                list.remove(0);
                list.add(e);
                return resolution(list);
            }
        }
        return list;
    }
    
    public void solve() {
        if (this.resolution(this.arr).isEmpty()) {
            System.out.println("Programme bien typé");
        } else {
            System.out.println("Résolution non terminée"); 
        }
    }
}

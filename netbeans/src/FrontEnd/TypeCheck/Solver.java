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
            Equation e = new Equation(list.get(0));
            if (!(e.isUnifiable())) {
                return list;
            } else if (e.isEqual()) {
                list.remove(0);
                return resolution(list);
            } else if (e.getT1() instanceof TTuple && e.getT2() instanceof TTuple) {
                TTuple t1 = (TTuple)e.getT1();
                TTuple t2 = (TTuple)e.getT2();
                if (t1.ts.size() != t2.ts.size()) {
                    return list;
                } else {
                    list.remove(0);
                    for (int i = 0; i<t1.ts.size(); i++) {
                        list.add(new Equation(t1.ts.get(i), t2.ts.get(i)));
                    }
                    return resolution(list);
                }
            } else if (e.getT1() instanceof TVar && !(e.getT2() instanceof TVar)) {
                replace(list, (TVar)e.getT1(), e.getT2());
                return resolution(list);
            } else if (!(e.getT1() instanceof TVar) && e.getT2() instanceof TVar) {
                replace(list, (TVar)e.getT2(), e.getT1());
                return resolution(list);
            } else {
                list.remove(0);
                list.add(e);
                return resolution(list);
            }
        }
    }
    
    public void solve() {
        if (this.resolution(this.arr).isEmpty()) {
            System.out.println("\033[32mProgramme bien typé\033[0m");
        } else {
            System.out.println("\033[31mProgramme mal typé\033[0m"); 
        }
    }
    
    private void replace(ArrayList<Equation> list, TVar toReplace, Type replace) {
        for (int i = 0; i<list.size(); i++) {
            if (list.get(i).getT1() instanceof TTuple) {
                TTuple t = (TTuple)list.get(i).getT1();
                for (int j = 0; j<t.ts.size(); j++) {
                    if (t.ts.get(j) == toReplace) {t.ts.set(j, replace);}
                }
            }
            
            if (list.get(i).getT2() instanceof TTuple) {
                TTuple t = (TTuple)list.get(i).getT2();
                for (int j = 0; j<t.ts.size(); j++) {
                    if (t.ts.get(j) == toReplace) {t.ts.set(j, replace);}
                }
            }
            
            if (list.get(i).getT1() == toReplace) {list.get(i).setT1(replace);}
            if (list.get(i).getT2() == toReplace) {list.get(i).setT2(replace);}
        }
    }
}

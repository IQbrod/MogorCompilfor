/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.Type;

import java.util.ArrayList;

/**
 *
 * @author sazeratj
 */
public class TTuple extends Type {
    public ArrayList<Type> ts;

    public TTuple(ArrayList<Type> t) {
        this.ts = new ArrayList();
        this.ts.addAll(t);
    }

    @Override
    public String toString() {
        if (this.ts.size() > 0) {
            String str = new String();
            for (int i = 0; i<this.ts.size()-1; i++) {
                str = str.concat(this.ts.get(i).toString() + " -> ");
            }
            str = str.concat(this.ts.get(this.ts.size()-1).toString());
            return str;
        } else {
            return "Empty tuple";
        }
    }
}

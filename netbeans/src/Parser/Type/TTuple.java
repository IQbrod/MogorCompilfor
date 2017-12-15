/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.Type;

/**
 *
 * @author sazeratj
 */
public class TTuple extends Type {

    private Type t1;
    private Type t2;

    public TTuple(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public Type getT1() {
        return t1;
    }

    public Type getT2() {
        return t2;
    }

    @Override
    public String toString() {
        return t1.toString() + " -> " + t2.toString();
    }
}

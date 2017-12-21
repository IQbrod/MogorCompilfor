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
public class TVar extends Type {
    String v;
    public TVar(String v) {
        this.v = v;
    }
    @Override
    public String toString() {
        return v; 
    }
}


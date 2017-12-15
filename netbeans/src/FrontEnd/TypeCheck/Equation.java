/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;
import Parser.AST.*;
import Parser.Type.*;
/**
 *
 * @author givaudav
 */
public class Equation {
    private Type t1;
    private Type t2;
    
    public Equation(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }
    
    public String toString() {
        return this.t1.toString() + " = " + this.t2.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;
import Parser.Type.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public Equation(Equation e) {
        this(e.getT1(), e.getT2());
    }
    
    public boolean isUnifiable() {
        return (this.t1.getClass() == this.t2.getClass() || this.t1 instanceof TVar || this.t2 instanceof TVar);
    }
    
    public boolean isEqual() {
        return (this.t1.getClass() == this.t2.getClass() && !(this.t1 instanceof TVar) && !(this.t1 instanceof TTuple));
    }
    
    @Override
    public String toString() {
        return this.t1.toString() + " = " + this.t2.toString();
    }

    public Type getT1() {
        return t1;
    }

    public Type getT2() {
        return t2;
    }

    public void setT1(Type t1) {
        this.t1 = t1;
    }

    public void setT2(Type t2) {
        this.t2 = t2;
    }
    
}

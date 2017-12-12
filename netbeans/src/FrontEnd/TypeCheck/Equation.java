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
    private Exp exp;
    private Type type;
    
    public Equation(Exp exp, Type type) {
        this.exp = exp;
        this.type = type;
    }
}

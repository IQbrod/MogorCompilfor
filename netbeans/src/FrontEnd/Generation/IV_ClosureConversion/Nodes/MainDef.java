/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.IV_ClosureConversion.Nodes;

import FrontEnd.Generation.IV_ClosureConversion.ClosureVisitor;
import Parser.ASTMincaml.Exp;

/**
 *
 * @author Adrien
 */
public class MainDef extends Node {
    public Exp e;
    
    public MainDef (Exp e) {
        this.e = e;
    }

    @Override
    public Node accept(ClosureVisitor v) {
        return null;
    }
}

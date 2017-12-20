/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.IV_ClosureConversion.Nodes;
import FrontEnd.Generation.II_AlphaConversion.AlphaConversionVisitor;
import FrontEnd.Generation.II_AlphaConversion.Ids;
import FrontEnd.Generation.IV_ClosureConversion.ClosureVisitor;
import FrontEnd.TypeCheck.AbsTypeCheckVisitor;
import FrontEnd.TypeCheck.Environnement;
import FrontEnd.TypeCheck.Equation;
import Parser.ASTMincaml.*;
import Parser.ObjVisitor;
import Parser.Type.Type;
import Parser.Visitor;
import java.util.ArrayList;

/**
 *
 * @author Adrien
 */
public class FunctionDef extends Node {
    public String label;
    public ArrayList<String> parameters;
    public Exp code;
    
    public FunctionDef(String label, ArrayList<String> parameters, Exp code) {
        this.label = label;
        this.parameters = parameters;
        this.code = code;
    }
    
    @Override
    public Node accept(ClosureVisitor v) {
        return null;
    }
}
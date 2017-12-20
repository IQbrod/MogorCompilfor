/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.ASTMincaml;

import FrontEnd.Generation.II_AlphaConversion.AlphaConversionVisitor;
import FrontEnd.Generation.II_AlphaConversion.Ids;
import FrontEnd.Generation.IV_ClosureConversion.ClosureVisitor;
import FrontEnd.Generation.IV_ClosureConversion.Nodes.Node;
import FrontEnd.TypeCheck.AbsTypeCheckVisitor;
import FrontEnd.TypeCheck.Environnement;
import FrontEnd.TypeCheck.Equation;
import Parser.ObjVisitor;
import Parser.Type.Type;
import Parser.Visitor;
import java.util.ArrayList;

/**
 *
 * @author sazeratj
 */
public class Array extends Exp {
    public final Exp e1;
    public final Exp e2;

    public Array(Exp e1, Exp e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public <E> E accept(ObjVisitor<E> v) {
        return v.visit(this);
    }
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
    
    @Override
    public void accept(AbsTypeCheckVisitor v, Environnement env, Type t, ArrayList<Equation> arr) {
        v.visit(this, env, t, arr);
    }
    
    @Override
    public Exp accept(AlphaConversionVisitor v, ArrayList<Ids> changements) {
        return v.visit(this,changements);
    }
    
    @Override
    public Node accept(ClosureVisitor v) {
        return v.visit(this);
    }
}

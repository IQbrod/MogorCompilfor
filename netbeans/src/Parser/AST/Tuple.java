/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.AST;

import FrontEnd.Generation.II_AlphaConversion.AlphaConversionVisitor;
import FrontEnd.Generation.II_AlphaConversion.Ids;
import FrontEnd.TypeCheck.AbsTypeCheckVisitor;
import FrontEnd.TypeCheck.Environnement;
import FrontEnd.TypeCheck.Equation;
import Parser.*;
import Parser.Type.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sazeratj
 */
public class Tuple extends Exp {
    public final List<Exp> es;

    public Tuple(List<Exp> es) {
        this.es = es;
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
}

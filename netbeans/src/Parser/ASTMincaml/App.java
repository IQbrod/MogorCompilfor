/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.ASTMincaml;

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
public class App extends Exp {
    public final Exp e;
    public final List<Exp> es;

    public App(Exp e, List<Exp> es) {
        this.e = e;
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
}
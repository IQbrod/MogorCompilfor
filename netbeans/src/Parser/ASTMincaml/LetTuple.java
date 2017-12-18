/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.ASTMincaml;

import FrontEnd.TypeCheck.AbsTypeCheckVisitor;
import FrontEnd.TypeCheck.Environnement;
import FrontEnd.TypeCheck.Equation;
import Parser.Type.Type;
import Parser.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sazeratj
 */
public class LetTuple extends Exp {
    public final List<Id> ids;
    public final List<Type> ts;
    public final Exp e1;
    public final Exp e2;

    public LetTuple(List<Id> ids, List<Type> ts, Exp e1, Exp e2) {
        this.ids = ids;
        this.ts = ts;
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
}
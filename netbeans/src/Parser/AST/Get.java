/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.AST;

import Parser.*;

/**
 *
 * @author sazeratj
 */
public class Get extends Exp {
    public final Exp e1;
    public final Exp e2;

    public Get(Exp e1, Exp e2) {
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
}
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
public class Neg extends Exp {
    public final Exp e;

    public Neg(Exp e) {
        this.e = e;
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

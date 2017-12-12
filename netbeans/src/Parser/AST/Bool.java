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
public class Bool extends Exp {
    public final boolean b;

    public Bool(boolean b) {
        this.b = b;
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
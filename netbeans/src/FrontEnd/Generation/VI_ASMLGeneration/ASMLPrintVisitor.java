/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.VI_ASMLGeneration;

import FrontEnd.Generation.VI_ASMLGeneration.ASTAsml.*;

/**
 *
 * @author sazeratj
 */
public class ASMLPrintVisitor implements ASMLVisitor {

    @Override
    public void visit(Anop e) {
        System.out.print("(nop)");
    }

    @Override
    public void visit(Aint e) {
        System.out.print(e.i);
    }

    @Override
    public void visit(Afloat e) {
        System.out.print(e.f);
    }

    @Override
    public void visit(Aident e) {
        System.out.print(e.id);
    }

    @Override
    public void visit(Alabel e) {
        System.out.print(e.id);
    }

    @Override
    public void visit(Aadd e) {
        e.e1.accept(this);
        System.out.print(" + ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Aneg e) {
        System.out.print("-");
        e.e.accept(this);
    }

    @Override
    public void visit(Asub e) {
        e.e1.accept(this);
        System.out.print(" - ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afadd e) {
        e.e1.accept(this);
        System.out.print(" + ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afdiv e) {
        e.e1.accept(this);
        System.out.print(" / ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afmul e) {
        e.e1.accept(this);
        System.out.print(" * ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afneg e) {
        System.out.print("-");
        e.e.accept(this);
    }

    @Override
    public void visit(Afsub e) {
        e.e1.accept(this);
        System.out.print(" - ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Aeq e) {
        this.visit(e.e1);
        System.out.print(" == ");
        if (e.e2 instanceof Aint)
            this.visit((Aint) e.e2);
        if (e.e2 instanceof Aident)
            this.visit((Aident) e.e2);
    }

    @Override
    public void visit(Afeq e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Afle e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Age e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Ale e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Anot e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Acall e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Acallclo e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Afunflt e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Afunlabel e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Afunmain e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Aparen e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Aif e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Alet e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Amem e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Anew e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

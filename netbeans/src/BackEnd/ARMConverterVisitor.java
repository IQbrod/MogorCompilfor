/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import FrontEnd.Generation.VI_ASMLGeneration.ASMLVisitor;
import FrontEnd.Generation.VI_ASMLGeneration.ASTAsml.*;
import java.util.HashMap;

/**
 *
 * @author sazeratj
 */
public class ARMConverterVisitor implements ASMLVisitor {
    private HashMap<String,String> tab; // Map varname => register
    private String curName; // Name of the current Let
    
    private int regCt; // Last Free register
    
    public ARMConverterVisitor(HashMap<String,String> arg) {
        this.tab = arg;
        this.regCt = 4;
    }
    
    @Override
    public void visit(Anop e) {
        System.out.println("NOP");
    }

    @Override
    public void visit(Aint e) {
        System.out.print("#"+e.i);
    }

    @Override
    public void visit(Afloat e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Aident e) {
        System.out.print(tab.get(e.id));
    }

    @Override
    public void visit(Alabel e) {
        System.out.print(tab.get(e.id));
    }

    @Override
    public void visit(Aadd e) {
        System.out.print("ADD ");
        tab.get(curName);
        System.out.print(", ");
        e.e1.accept(this);
        System.out.print(", ");
        e.e2.accept(this);
        System.out.print("\n");
    }

    @Override
    public void visit(Aneg e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Asub e) {
        System.out.print("SUB ");
        tab.get(curName);
        System.out.print(", ");
        e.e1.accept(this);
        System.out.print(", ");
        e.e2.accept(this);
        System.out.print("\n");
    }

    @Override
    public void visit(Afadd e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Afdiv e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Afmul e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Afneg e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Afsub e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Aeq e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        /* Enregistre le Let dans TAB */
        this.curName = e.e1.toString();
        tab.put(e.e1.toString(), "R"+regCt);
        regCt++;
        /* ATTENTION => Ne gère pas pour l'instant la limite de registre d'ARM */
        if (e.e2 instanceof Aint) {
            System.out.print("ADD ");
            tab.get(curName);
            System.out.print(", ");
            e.e1.accept(this);
            System.out.print(", ");
            e.e2.accept(this);
            System.out.print("\n"); 
        } else if (e.e2 instanceof Aident || e.e2 instanceof Alabel) {
            // Nothing to do => Already in TAB
        }
        e.e3.accept(this);
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

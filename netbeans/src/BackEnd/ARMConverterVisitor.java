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
    private int ifCt;
    
    public ARMConverterVisitor() {
        this.tab = new HashMap<String,String>();
        this.regCt = 4;
        this.ifCt = 0;
    }
    
    private void pushregister(String r, String v) {
        System.out.print("SUB " + r + ", " + r + ", " + r + "\n");
        System.out.print("ADD " + r + ", " + r + ", " + v + "\n");
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
        System.out.print(e.id);
    }

    @Override
    public void visit(Aadd e) {
        System.out.print("ADD ");
        System.out.print(tab.get(curName));
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
        System.out.print(tab.get(curName));
        System.out.print(", ");
        e.e1.accept(this);
        System.out.print(", ");
        e.e2.accept(this);tab.get(curName);
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
        int i = 0;
        for (Aident a : e.args) {
            pushregister("R"+i,tab.get(a.id));
            i++;
        }
        System.out.print("bl ");
        e.lb.accept(this);
        System.out.print("\n");
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
        System.out.println(".text\n.global _start\n\n_start:");
        e.e.accept(this);
    }

    @Override
    public void visit(Aparen e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Aif e) {
        e.b.accept(this);
        System.out.print("true" + this.ifCt + "\n");
        e.e2.accept(this);
        System.out.print("b done" + this.ifCt + "\n");
        System.out.print("true" + this.ifCt + ": ");
        e.e1.accept(this);
        System.out.print("\n");
        System.out.print("done" + this.ifCt + ": ");
        this.ifCt++;
    }

    @Override
    public void visit(Alet e) {
        /* Enregistre le Let dans TAB */
        this.curName = e.e1.id;
        tab.put(((Aident)e.e1).id, "R"+regCt);
        regCt++;
        /* ATTENTION => Ne gère pas pour l'instant la limite de registre d'ARM */
        if (e.e2 instanceof Aint) {
            pushregister(tab.get(curName),"#"+((Aint)e.e2).i);
        } else if (e.e2 instanceof Aident) {
            pushregister(tab.get(curName),tab.get(((Aident)e.e2).toString()));
        } else if (e.e2 instanceof Alabel) {
            pushregister(tab.get(curName),tab.get(((Alabel)e.e2).toString()));
        } else {
            e.e2.accept(this);
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

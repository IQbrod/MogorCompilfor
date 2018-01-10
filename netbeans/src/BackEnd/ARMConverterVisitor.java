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
    private String[] regs; //Registers from 4 to 12
    private int regCt; // Last Free register
    
    private HashMap<String,String> tab; // Map varname => [FP-offset] ou #i
    private int offCt; // Offset Counter
    private String curName; // Name of the current Let
    
    public ARMConverterVisitor() {
        this.tab = new HashMap<String,String>();
        this.regs = new String[9];
        this.regCt = 0;
        this.offCt = 4;
    }
    
    private void pullVar(String v) {
        System.out.print("LD R"+(regCt+4)+", "+ v +"\n");
        regCt = (regCt+1)%9;
    }
    
    private void pullImm(String v) {
        pushregister("R"+(regCt+4),v);
        regCt = (regCt+1)%9;
    }
    
    private void pushregister(String r, String v) {
        System.out.print("SUB " + r + ", " + r + ", " + r + "\n");
        System.out.print("ADD " + r + ", " + r + ", " + v + "\n");
    }
    
    private void store(String v) {
        int r = (regCt-1)%9+4;
        System.out.print("ST R"+ r + ", " + v + "\n");
    }
    
    @Override
    public void visit(Anop e) {
        System.out.println("NOP");
    }

    @Override
    public void visit(Aint e) {
        this.pullImm("#"+e.i);
        store(tab.get(curName));
    }

    @Override
    public void visit(Afloat e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Aident e) {
        this.pullVar(tab.get(e.id));
        store(tab.get(curName));
    }

    @Override
    public void visit(Alabel e) {
        System.out.print(e.id);
    }

    @Override
    public void visit(Aadd e) {
        this.pullVar(tab.get(e.e1.id));
        if(e.e2 instanceof Aint) {
            this.pullImm("#"+((Aint)e.e2).i);
        } else if (e.e2 instanceof Aident) {
            this.pullVar(tab.get(((Aident)e.e2).id));
        }
        
        int r1 = (regCt-2)%9+4;
        int r2 = (regCt-1)%9+4;
        
        System.out.print("ADD R"+(regCt+4)+", R"+r1+", R"+r2+"\n");
        regCt = (regCt+1)%9;
        
        store(tab.get(curName));
    }

    @Override
    public void visit(Aneg e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Asub e) {
        this.pullVar(tab.get(e.e1.id));
        if(e.e2 instanceof Aint) {
            this.pullImm("#"+((Aint)e.e2).i);
        } else if (e.e2 instanceof Aident) {
            this.pullVar(tab.get(((Aident)e.e2).id));
        }
        
        int r1 = (regCt-2)%9+4;
        int r2 = (regCt-1)%9+4;
        
        System.out.print("SUB R"+(regCt+4)+", R"+r1+", R"+r2+"\n");
        regCt = (regCt+1)%9;
        
        store(tab.get(curName));
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
            pullVar(tab.get(a.id));
            pushregister("R"+i,"R"+((regCt-1)%9+4));
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Alet e) {
        this.curName = e.e1.id;
        /* ATTENTION => Ne gère pas pour l'instant la limite de registre d'ARM */
        tab.put(curName,"[FP-"+offCt+"]");
        offCt += 4;
        e.e2.accept(this);
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

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
        System.out.print("add ");
        e.e1.accept(this);
        System.out.print(" ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Aneg e) {
        System.out.print("neg ");
        e.e.accept(this);
    }

    @Override
    public void visit(Asub e) {
        System.out.print("sub ");
        e.e1.accept(this);
        System.out.print(" ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afadd e) {
        System.out.print("fadd ");
        e.e1.accept(this);
        System.out.print(" ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afdiv e) {
        System.out.print("fdiv ");
        e.e1.accept(this);
        System.out.print(" ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afmul e) {
        System.out.print("fmul ");
        e.e1.accept(this);
        System.out.print(" ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afneg e) {
        System.out.print("fneg");
        e.e.accept(this);
    }

    @Override
    public void visit(Afsub e) {
        System.out.print("fsub ");
        e.e1.accept(this);
        System.out.print(" ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Aeq e) {
        e.e1.accept(this);
        System.out.print(" = ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afeq e) {
        e.e1.accept(this);
        System.out.print(" =. ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Afle e) {
        e.e1.accept(this);
        System.out.print(" <=. ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Age e) {
        e.e1.accept(this);
        System.out.print(" >= ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Ale e) {
        e.e1.accept(this);
        System.out.print(" <= ");
        e.e2.accept(this);
    }

    @Override
    public void visit(Anot e) {
        /** BE CAREFUL => Check and invert display in IF node **/
        e.e.accept(this);
    }

    @Override
    public void visit(Acall e) {
        System.out.print("call ");
        e.lb.accept(this);
        System.out.print("(");
        for (int i=0; i<e.args.size()-1;i++) {
            e.args.get(i).accept(this);
            System.out.print(",");
        }
        e.args.get(e.args.size()-1).accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(Acallclo e) {
        System.out.print("call "); // VOIR AVEC PROF SELON SEMANTIQUE
        e.lb.accept(this);
        System.out.print("(");
        for (Aident a : e.args) {
            a.accept(this);
            System.out.print(",");
        }
        System.out.print(")");
    }

    @Override
    public void visit(Afunflt e) {
        System.out.print("let ");
        e.id.accept(this);
        System.out.print(" = ");
        e.f.accept(this);
        System.out.print("\n");
        e.next.accept(this);
    }

    @Override
    public void visit(Afunlabel e) {
        System.out.print("let ");
        e.id.accept(this);
        System.out.print("(");
        for (Aident a : e.args) {
            a.accept(this);
            System.out.print(",");
        }
        System.out.print(") = ");
        e.e.accept(this);
        System.out.print("\n");
        e.next.accept(this);
    }

    @Override
    public void visit(Afunmain e) {
        System.out.print("let _ = ");
        e.e.accept(this);
        System.out.print("\n");
    }

    @Override
    public void visit(Aparen e) {
        System.out.print("(");
        e.e.accept(this);
        System.out.print(")");
    }

    @Override
    public void visit(Aif e) {
        System.out.print("if (");
        e.b.accept(this);
        System.out.print(") then (");
        if (e.b instanceof Anot) { // NOT NODE => Invert then and else
            e.e2.accept(this);
            System.out.print(") else (");
            e.e1.accept(this);
            System.out.print(")");
        } else {
            e.e1.accept(this);
            System.out.print(") else (");
            e.e2.accept(this);
            System.out.print(")");
        }
    }

    @Override
    public void visit(Alet e) {
        System.out.print("let ");
        e.e1.accept(this);
        System.out.print(" = ");
        e.e2.accept(this);
        System.out.print(" in ");
        e.e3.accept(this);
    }

    @Override
    public void visit(Amem e) {
        System.out.print("mem(");
        e.e1.accept(this);
        System.out.print("+");
        e.e2.accept(this);
        System.out.print(")");
        if (e.e3 != null) {
            System.out.print(" <- ");
            e.e3.accept(this);
        }
    }

    @Override
    public void visit(Anew e) {
        System.out.print("new ");
        e.e.accept(this);
    }
    
}

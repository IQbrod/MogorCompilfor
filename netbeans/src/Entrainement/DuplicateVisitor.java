/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainement;

import Parser.*;
import Parser.AST.*;
import Parser.Type.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sazeratj
 */
public class DuplicateVisitor implements ObjVisitor<Exp>{

    @Override
    public Exp visit(Unit e) {
        return new Unit();
    }

    @Override
    public Exp visit(Bool e) {
        return new Bool(e.b);
    }

    @Override
    public Exp visit(Int e) {
        return new Int(e.i);
    }

    @Override
    public Exp visit(Flt e) {
        return new Flt(e.f);
    }

    @Override
    public Exp visit(Not e) {
        Exp f = e.e.accept(this);
        Not n = new Not(f);
        return f;
    }

    @Override
    public Exp visit(Neg e) {
        Exp f = e.e.accept(this);
        return new Neg(f);
    }

    @Override
    public Exp visit(Add e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new Add(f1, f2);
    }

    @Override
    public Exp visit(Sub e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new Sub(f1, f2);
    }

    @Override
    public Exp visit(FNeg e) {
        Exp f = e.e.accept(this);
        return new FNeg(f);
    }

    @Override
    public Exp visit(FAdd e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new FAdd(f1, f2);
    }

    @Override
    public Exp visit(FSub e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new FSub(f1, f2);
    }

    @Override
    public Exp visit(FMul e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new FMul(f1, f2);
    }

    @Override
    public Exp visit(FDiv e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new FDiv(f1, f2);
    }

    @Override
    public Exp visit(Eq e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new Eq(f1, f2);
    }

    @Override
    public Exp visit(LE e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new LE(f1, f2);
    }

    @Override
    public Exp visit(If e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        Exp f3 = e.e3.accept(this);
        return new If(f1, f2, f3);
    }

    @Override
    public Exp visit(Let e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        Type t = e.t;
        Id id = e.id;
        return new Let(id, t, f1, f2);
    }

    @Override
    public Exp visit(Var e) {
        Id id = e.id;
        return new Var(id);
    }

    @Override
    public Exp visit(LetRec e) {
        FunDef gd = e.fd;
        Exp f = e.e.accept(this);
        return new LetRec(gd, f);
    }

    @Override
    public Exp visit(App e) {
        Exp f = e.e.accept(this);
        List<Exp> le = new ArrayList<>();
        for (Exp g : e.es) {
            le.add(g.accept(this));
        }
        return new App(e,le);
    }

    @Override
    public Exp visit(Tuple e) {
        List<Exp> le = new ArrayList<>();
        for (Exp g : e.es) {
            le.add(g.accept(this));
        }
        return new Tuple(le);
    }

    @Override
    public Exp visit(LetTuple e) {
        List<Id> lid = new ArrayList<>();
        for (Id id : e.ids) {
            lid.add(id);
        }
        List<Type> lt = new ArrayList<>();
        for (Type t : e.ts) {
            lt.add(t);
        }
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new LetTuple(lid,lt,f1,f2);
    }

    @Override
    public Exp visit(Array e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new Array(f1,f2);
    }

    @Override
    public Exp visit(Get e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        return new Get(f1,f2);
    }

    @Override
    public Exp visit(Put e) {
        Exp f1 = e.e1.accept(this);
        Exp f2 = e.e2.accept(this);
        Exp f3 = e.e3.accept(this);
        return new Put(f1,f2,f3);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.III_ReductionNestedLet;

import Parser.ASTMincaml.FSub;
import Parser.ASTMincaml.Unit;
import Parser.ASTMincaml.Tuple;
import Parser.ASTMincaml.Get;
import Parser.ASTMincaml.LetTuple;
import Parser.ASTMincaml.FunDef;
import Parser.ASTMincaml.FDiv;
import Parser.ASTMincaml.App;
import Parser.ASTMincaml.Int;
import Parser.ASTMincaml.Exp;
import Parser.ASTMincaml.Bool;
import Parser.ASTMincaml.Let;
import Parser.ASTMincaml.Sub;
import Parser.ASTMincaml.Array;
import Parser.ASTMincaml.LetRec;
import Parser.ASTMincaml.Flt;
import Parser.ASTMincaml.Put;
import Parser.ASTMincaml.LE;
import Parser.ASTMincaml.If;
import Parser.ASTMincaml.Var;
import Parser.ASTMincaml.Not;
import Parser.ASTMincaml.FMul;
import Parser.ASTMincaml.Neg;
import Parser.ASTMincaml.Add;
import Parser.ASTMincaml.FAdd;
import Parser.ASTMincaml.FNeg;
import Parser.ASTMincaml.Eq;
import Parser.*;
import Parser.Type.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sazeratj
 */
public class NestedLetVisitor implements ObjVisitor<Exp> {

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
        if (e.e1 instanceof Let) { //Nested Let
            Let l2 = new Let(e.id,e.t,((Let)e.e1).e2.accept(this),e.e2.accept(this));
            Let l1 = new Let(((Let)e.e1).id, ((Let)e.e1).t, ((Let)e.e1).e1.accept(this), l2);
            return l1.accept(this); //Permet de consulter les Let imbriqués :)
        } else {
            return new Let(e.id, e.t, e.e1.accept(this), e.e2.accept(this));
        }
    }

    @Override
    public Exp visit(Var e) {
        Id id = e.id;
        return new Var(id);
    }

    @Override
    public Exp visit(LetRec e) {
        FunDef gd = e.fd;
        //FunDef gd = new FunDef(e.fd.id,e.fd.type,e.fd.args,e.fd.e.accept(this));
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
        return new App(f,le);
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

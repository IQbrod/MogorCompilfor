/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.I_KNormalisation;

import Parser.ASTMincaml.*;
import Parser.*;
import Parser.Type.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sazeratj
 */
public class KNormVisitor implements ObjVisitor<Exp> {
    /* Fonctions */
    @Override
    public Exp visit(Unit e) {
        return new Unit();
    }

    @Override
    public Exp visit(Bool e) {
        if(e.b) {
            return new Eq(new Int(1),new Int(1));
        } else {
            return new Eq(new Int(1),new Int(0));
        }
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
        return new Not(e.e.accept(this));
    }

    @Override
    public Exp visit(Neg e) {
        return new Neg(e.e.accept(this));
    }

    @Override
    public Exp visit(Add e) {
        Id id1 = Id.gen(); Var v1 = new Var(id1);
        Id id2 = Id.gen(); Var v2 = new Var(id2);
        Add a = new Add(v1,v2);
        Let l2 = new Let(id2,new TInt(), e.e2.accept(this), a);
        Let l1 = new Let(id1,new TInt(), e.e1.accept(this), l2);
        
        return l1;
    }

    @Override
    public Exp visit(Sub e) {
        Id id1 = Id.gen(); Var v1 = new Var(id1);
        Id id2 = Id.gen(); Var v2 = new Var(id2);
        Sub s = new Sub(v1,v2);
        Let l2 = new Let(id2,new TInt(), e.e2.accept(this), s);
        Let l1 = new Let(id1,new TInt(), e.e1.accept(this), l2);
        
        return l1;
    }

    @Override
    public Exp visit(FNeg e) {
        return new FNeg(e.e.accept(this));
    }

    @Override
    public Exp visit(FAdd e) {
        Id id1 = Id.gen(); Var v1 = new Var(id1);
        Id id2 = Id.gen(); Var v2 = new Var(id2);
        FAdd a = new FAdd(v1,v2);
        Let l2 = new Let(id2,new TFloat(), e.e2.accept(this), a);
        Let l1 = new Let(id1,new TFloat(), e.e1.accept(this), l2);
        
        return l1;
    }

    @Override
    public Exp visit(FSub e) {
        Id id1 = Id.gen(); Var v1 = new Var(id1);
        Id id2 = Id.gen(); Var v2 = new Var(id2);
        FSub s = new FSub(v1,v2);
        Let l2 = new Let(id2,new TFloat(), e.e2.accept(this), s);
        Let l1 = new Let(id1,new TFloat(), e.e1.accept(this), l2);
        
        return l1;
    }

    @Override
    public Exp visit(FMul e) {
        Id id1 = Id.gen(); Var v1 = new Var(id1);
        Id id2 = Id.gen(); Var v2 = new Var(id2);
        FMul m = new FMul(v1,v2);
        Let l2 = new Let(id2,new TFloat(), e.e2.accept(this), m);
        Let l1 = new Let(id1,new TFloat(), e.e1.accept(this), l2);
        
        return l1;
    }

    @Override
    public Exp visit(FDiv e) {
        Id id1 = Id.gen(); Var v1 = new Var(id1);
        Id id2 = Id.gen(); Var v2 = new Var(id2);
        FDiv d = new FDiv(v1,v2);
        Let l2 = new Let(id2,new TFloat(), e.e2.accept(this), d);
        Let l1 = new Let(id1,new TFloat(), e.e1.accept(this), l2);
        
        return l1;
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
        return new If(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
    }

    @Override
    public Exp visit(Let e) {
        return new Let(e.id,e.t,e.e1.accept(this),e.e2.accept(this));
    }

    @Override
    public Exp visit(Var e) {
        return new Var(e.id);
    }

    @Override
    public Exp visit(LetRec e) {
        FunDef f = new FunDef(e.fd.id, e.fd.type, e.fd.args, e.fd.e.accept(this));
        return new LetRec(f,e.e.accept(this));
    }

    @Override
    public Exp visit(App e) {
        App a = new App(e.e.accept(this),new ArrayList<Exp>());
        if (e.e instanceof App) {
            Id id = Id.gen();
            Let l = new Let(id,new TVar(id.toString()),e.e, new Unit());
            a = new App(new Var(id),new ArrayList<Exp>());
            for (Exp g : e.es) {
                a.es.add(g.accept(this));
            }
            l.e2 = a;
            return l;
        } else {
            Let first = null;
            Let old = null;

            for (Exp g : e.es) {
                Id id = Id.gen();
                a.es.add(new Var(id));
                Let x = new Let(id,new TVar(id.toString()),g, new Unit());
                x = (Let) x.accept(this);
                if(old == null) { // FIRST LET
                    first = x;
                    old = x;
                }
                old.e2 = x;
                old = x;
            }
            if (first == null) {return a;} //Aucun Argument
            old.e2 = a;

            return first;
        }
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
        return new LetTuple(lid,lt,e.e1.accept(this),e.e2.accept(this));
    }

    @Override
    public Exp visit(Array e) {
        return new Array(e.e1.accept(this),e.e2.accept(this));
    }

    @Override
    public Exp visit(Get e) {
        return new Get(e.e1.accept(this),e.e2.accept(this));
    }

    @Override
    public Exp visit(Put e) {
        return new Put(e.e1.accept(this),e.e2.accept(this),e.e3.accept(this));
    }
    
}

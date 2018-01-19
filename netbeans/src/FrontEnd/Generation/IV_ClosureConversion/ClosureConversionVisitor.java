/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.IV_ClosureConversion;

import Parser.ASTMincaml.*;
import Parser.Id;
import Parser.Type.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Adrien
 */
public class ClosureConversionVisitor implements ClosureVisitor {

    public Exp main;
    public LetRec lastF;
    public Boolean c;
    public HashMap<Id, FunDef> functions;
    public HashMap<Id, ArrayList<Id>> freeVar;
    public HashMap<Id, LetRec> closure;

    public ClosureConversionVisitor() {
        try {
            throw new Error("On passe des arguments svp.");
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    public ClosureConversionVisitor(Exp main) {
        this.functions = new HashMap<Id, FunDef>();
        this.freeVar = new HashMap<Id, ArrayList<Id>>();
        this.closure = new HashMap<Id, LetRec>();
        this.main = main;
        this.c = false;
        this.lastF = null;
    }

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
        return new Not(e.e.accept(this));
    }

    @Override
    public Exp visit(Neg e) {
        return new Neg(e.e.accept(this));
    }

    @Override
    public Exp visit(Add e) {
        return new Add(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Sub e) {
        return new Sub(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(FNeg e) {
        return new FNeg(e.e.accept(this));
    }

    @Override
    public Exp visit(FAdd e) {
        return new FAdd(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(FSub e) {
        return new FSub(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(FMul e) {
        return new FMul(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(FDiv e) {
        return new FDiv(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Eq e) {
        return new Eq(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(LE e) {
        return new LE(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(If e) {
        return new If(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
    }

    @Override
    public Exp visit(Let e) {
        Let l;
        if (e.e2 instanceof LetRec) {
            LetRec lr = (LetRec) e.e2;
            l = new Let(e.id, e.t, e.e1.accept(this), lr.e);
            e.e2.accept(this);
        } else {
            l = new Let(e.id, e.t, e.e1.accept(this), e.e2.accept(this));
        }
        return l;
    }

    @Override
    public Exp visit(Var e) {
        return new Var(e.id);
    }

    public boolean estUnEntier(String chaine) {
        try {
            Integer.parseInt(chaine);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void freeVariables(LetRec f) {
        //Une variable libre est une variables en partie droite d'un let qui n'est pas un argument
        Exp e = f.fd.e;
        while (e != null) {
            if (e instanceof Let) {
                Let l = (Let) e;
                if (l.e1 instanceof Var) {
                    Var v = (Var) l.e1;
                    if (!estUnEntier(v.id.toString())) {
                        //On verifie que c'est un parametre
                        boolean trouve = false;
                        for (int i = 0; i < f.fd.args.size(); i++) {
                            if (v.id.toString().equals(f.fd.args.get(i).toString())) {
                                //C'est un parametre
                                trouve = true;
                            }
                        }
                        //Si ce n'est pas un parametre
                        if (!trouve) {
                            ArrayList<Id> ids = freeVar.get(f.fd.id);
                            if (ids == null) {
                                ids = new ArrayList<>();
                            }
                            ids.add(v.id);
                            freeVar.put(f.fd.id, ids);
                        }
                    }
                }
                if (l.e2 instanceof Let) {
                    e = l.e2;
                } else {
                    e = null;
                }
            } else {
                e = null;
            }
        }
    }

    @Override
    public Exp visit(LetRec e) {
        Exp ex;
        if (e.fd.e instanceof LetRec && e.fd.args.size() != 0) {
            //C'est une fonction imbriquée il peux y avoir une closure
            //On fait un make closure pour chaque variable de la fonction
            ex = e.fd.e;
            for (Id id : e.fd.args) {
                Id i = id.gen();
                ArrayList<Exp> v = new ArrayList<>();
                v.add(new Var(e.fd.id));
                v.add(new Var(id));
                closure.put(id, e);
                App a = new App(new Var(new Id("make_closure")), v);
                ex = new Let(i, Type.gen(), a, new Var(i));
            }
        } else {
            ex = e.fd.e;
        }
        FunDef fd = new FunDef(e.fd.id, e.fd.type, e.fd.args, ex);
        functions.put(e.fd.id, fd);
        LetRec f;
        if (e.fd.e instanceof LetRec) {
            lastF = e;
            f = new LetRec(fd, e.fd.e.accept(this));
        } else if (lastF == null) {
            f = new LetRec(fd, e.e.accept(this));
        } else {
            f = new LetRec(fd, lastF.e.accept(this));
        }
        main = f;
        freeVariables(f);
        return f;
    }

    @Override
    public Exp visit(App e) {
        App a;
        Var v;
        Boolean finterne = false;
        ArrayList<Exp> es = new ArrayList<>();
        if (e.e instanceof Var) {
            v = (Var) e.e;
            FunDef fd = functions.get(v.id);
            if (fd != null) {
                finterne = true;
            }
        }
        if (finterne) {
            es.add(e.e.accept(this));
            for (Exp ex : e.es) {
                es.add(ex.accept(this));
            }
            v = new Var(new Id("apply_direct"));
            a = new App(v.accept(this), es);
            c = true;
        } else if (c) {
            es.add(e.e.accept(this));
            for (Exp ex : e.es) {
                es.add(ex.accept(this));
            }
            v = new Var(new Id("apply_closure"));
            a = new App(v.accept(this), es);
            c = false;
        } else {
            for (Exp ex : e.es) {
                es.add(ex.accept(this));
            }
            a = new App(e.e.accept(this), es);
        }
        return a;
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
    public Exp visit(LetTuple e
    ) {
        return new LetTuple(e.ids, e.ts, e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Array e
    ) {
        return new Array(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Get e
    ) {
        return new Get(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Put e
    ) {
        return new Put(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
    }
}

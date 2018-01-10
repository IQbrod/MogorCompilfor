/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.IV_ClosureConversion;

import Parser.ASTMincaml.*;
import Parser.Id;
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
    public Exp dernierNoeud;
    public LetRec lastF;
    public ArrayList<FunDef> functions;
    public HashMap<Id, ArrayList<Id>> freeVar;

    public ClosureConversionVisitor() {
        try {
            throw new Error("On passe des arguments svp.");
        } catch (Error e) {
            e.printStackTrace();
        }
    }

    public ClosureConversionVisitor(Exp main) {
        this.functions = new ArrayList<>();
        this.freeVar = new HashMap<Id, ArrayList<Id>>();
        this.main = main;
        this.lastF = null;
        this.dernierNoeud = null;
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
        Exp e = f.e;
        while (e != null) {
            if (e instanceof Let) {
                Let l = (Let) e;
                if (l.e1 instanceof Eq) {
                    Eq eq = (Eq) l.e1;
                    if (eq.e2 instanceof Var) {
                        Var v = (Var) eq.e2;
                        if (!estUnEntier(v.id.toString())) {
                            //On verifie que c'est un parametre
                            boolean trouve = false;
                            for (int i = 0; i < f.fd.args.size(); i++) {
                                if (v.id.equals(f.fd.args.get(i))) {
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
        FunDef fd = new FunDef(e.fd.id, e.fd.type, e.fd.args, e.fd.e);
        functions.add(fd);
        LetRec f;
        if (lastF == null) {
            f = new LetRec(fd, e.e);
            lastF = f;
            main = f;
            e.e.accept(this);
        } else {
            f = new LetRec(fd, e.e);
            lastF = f;
            lastF = f;
            e.e.accept(this);
        }
        freeVariables(f);
        return f;
    }

    @Override
    public Exp visit(App e) {
        List<Exp> exps = new ArrayList<>();
        for (Exp ex : e.es) {
            exps.add(ex.accept(this));
        }
        return new App(e.e.accept(this), exps);
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
        return new LetTuple(e.ids, e.ts, e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Array e) {
        return new Array(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Get e) {
        return new Get(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Put e) {
        return new Put(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
    }
}

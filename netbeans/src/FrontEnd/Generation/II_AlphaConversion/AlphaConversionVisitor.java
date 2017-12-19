/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.II_AlphaConversion;

import Parser.*;
import Parser.ASTMincaml.*;
import Parser.Type.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sazeratj
 */
public class AlphaConversionVisitor implements AlphaVisitor {

    /* Fonctions */
    @Override
    public Exp visit(Unit e, ArrayList<Ids> changements) {
        return new Unit();
    }

    @Override
    public Exp visit(Bool e, ArrayList<Ids> changements) {
        return new Bool(e.b);
    }

    @Override
    public Exp visit(Int e, ArrayList<Ids> changements) {
        return new Int(e.i);
    }

    @Override
    public Exp visit(Flt e, ArrayList<Ids> changements) {
        return new Flt(e.f);
    }

    @Override
    public Exp visit(Not e, ArrayList<Ids> changements) {
        return new Not(e.e.accept(this, changements));
    }

    @Override
    public Exp visit(Neg e, ArrayList<Ids> changements) {
        return new Neg(e.e.accept(this, changements));
    }

    @Override
    public Exp visit(Add e, ArrayList<Ids> changements) {
        return new Add(e.e1.accept(this, changements), e.e2.accept(this, changements));
    }

    @Override
    public Exp visit(Sub e, ArrayList<Ids> changements) {
        return new Sub(e.e1.accept(this, changements), e.e2.accept(this, changements));

    }

    @Override
    public Exp visit(FNeg e, ArrayList<Ids> changements) {
        return new FNeg(e.e.accept(this, changements));
    }

    @Override
    public Exp visit(FAdd e, ArrayList<Ids> changements) {
        return new FAdd(e.e1.accept(this, changements), e.e2.accept(this, changements));

    }

    @Override
    public Exp visit(FSub e, ArrayList<Ids> changements) {
        return new FSub(e.e1.accept(this, changements), e.e2.accept(this, changements));

    }

    @Override
    public Exp visit(FMul e, ArrayList<Ids> changements) {
        return new FMul(e.e1.accept(this, changements), e.e2.accept(this, changements));

    }

    @Override
    public Exp visit(FDiv e, ArrayList<Ids> changements) {
        return new FDiv(e.e1.accept(this, changements), e.e2.accept(this, changements));

    }

    @Override
    public Exp visit(Eq e, ArrayList<Ids> changements) {
        return new Eq(e.e1.accept(this, changements), e.e2.accept(this, changements));

    }

    @Override
    public Exp visit(LE e, ArrayList<Ids> changements) {
        return new LE(e.e1.accept(this, changements), e.e2.accept(this, changements));

    }

    @Override
    public Exp visit(If e, ArrayList<Ids> changements) {
        return new If(e.e1.accept(this, changements), e.e2.accept(this, changements), e.e3.accept(this, changements));

    }

    public Ids contient(Id id, ArrayList<Ids> changements) {
        boolean trouve = false;
        int i = changements.size() - 1;
        Ids res = null;
        while (!trouve && i != -1) {
            if (changements.get(i).old.toString().equals(id.toString())) {
                trouve = true;
                res = changements.get(i);
            }
            i--;
        }
        return res;
    }

    @Override
    public Exp visit(Let e, ArrayList<Ids> changements) {
        Ids res = contient(e.id, changements);
        Id id;
        if (res != null) {
            //On est dans le cas ou il faut renommer
            id = Id.gen2();
            Ids ids = new Ids(e.id, id);
            if (res.nouv.toString().equals(res.old.toString())) {
                changements.remove(res);
            }
            changements.add(ids);
        } else {
            //On ajoute la valeur
            changements.add(new Ids(e.id, e.id));
            id = e.id;
        }
        ArrayList<Ids> chang = (ArrayList<Ids>) changements.clone();
        Exp exp1 = e.e1.accept(this, changements);
        Exp exp2 = e.e2.accept(this, chang);
        return new Let(id, e.t, exp1, exp2);
    }

    @Override
    public Exp visit(Var e, ArrayList<Ids> changements) {
        Ids res = contient(e.id, changements);
        if (res != null) {
            //La variable a déjà été renommée
            return new Var(res.nouv);
        } else {
            return new Var(e.id);
        }
    }

    @Override
    public Exp visit(LetRec e, ArrayList<Ids> changements) {
        Ids res = contient(e.fd.id, changements);
        Id id;
        if (res != null) {
            //On est dans le cas ou il faut renommer
            id = Id.gen2();
            Ids ids = new Ids(e.fd.id, id);
            if (res.nouv == res.old) {
                changements.remove(res);
            }
            changements.add(ids);
        } else {
            //On ajoute la valeur
            id = e.fd.id;
            changements.add(new Ids(id, id));
        }
        ArrayList<Ids> chang = (ArrayList<Ids>) changements.clone();
        ArrayList<Id> list = new ArrayList<Id>();
        for (Id i : e.fd.args) {
            Ids res2 = contient(i, changements);
            if (res2 != null) {
                //On est dans le cas ou il faut renommer
                id = Id.gen2();
                Ids ids = new Ids(i, id);
                if (res2.nouv == res2.old) {
                    changements.remove(res);
                }
                changements.add(ids);
                list.add(id);
            } else {
                list.add(i);
                id = e.fd.id;
                changements.add(new Ids(id, id));
            }
        }
        return new LetRec(new FunDef(e.fd.id, e.fd.type, list, e.fd.e.accept(this, changements)), e.e.accept(this, chang));
    }

    @Override
    public Exp visit(App e, ArrayList<Ids> changements) {
        List<Exp> le = new ArrayList<>();
        for (Exp g : e.es) {
            le.add(g.accept(this, changements));
        }
        return new App(e.e.accept(this, changements), le);
    }

    @Override
    public Exp visit(Tuple e, ArrayList<Ids> changements) {
        List<Exp> le = new ArrayList<>();
        for (Exp g : e.es) {
            le.add(g.accept(this, changements));
        }
        return new Tuple(le);
    }

    @Override
    public Exp visit(LetTuple e, ArrayList<Ids> changements) {
        ArrayList<Id> list = new ArrayList<Id>();
        for (Id i : e.ids) {
            Ids res = contient(i, changements);
            if (res != null) {
                list.add(res.nouv);

            } else {
                list.add(i);
            }
        }
        return new LetTuple(list, e.ts, e.e1.accept(this, changements), e.e2.accept(this, changements));
    }

    @Override
    public Exp visit(Array e, ArrayList<Ids> changements) {
        return new Array(e.e1.accept(this, changements), e.e2.accept(this, changements));
    }

    @Override
    public Exp visit(Get e, ArrayList<Ids> changements) {
        return new Get(e.e1.accept(this, changements), e.e2.accept(this, changements));
    }

    @Override
    public Exp visit(Put e, ArrayList<Ids> changements) {
        return new Put(e.e1.accept(this, changements), e.e2.accept(this, changements), e.e3.accept(this, changements));
    }

}

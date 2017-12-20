/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.IV_ClosureConversion;

import FrontEnd.Generation.II_AlphaConversion.Ids;
import FrontEnd.Generation.IV_ClosureConversion.Nodes.Node;
import Parser.ASTMincaml.Add;
import Parser.ASTMincaml.App;
import Parser.ASTMincaml.Array;
import Parser.ASTMincaml.Bool;
import Parser.ASTMincaml.Eq;
import Parser.ASTMincaml.Exp;
import Parser.ASTMincaml.FAdd;
import Parser.ASTMincaml.FDiv;
import Parser.ASTMincaml.FMul;
import Parser.ASTMincaml.FNeg;
import Parser.ASTMincaml.FSub;
import Parser.ASTMincaml.Flt;
import Parser.ASTMincaml.Get;
import Parser.ASTMincaml.If;
import Parser.ASTMincaml.Int;
import Parser.ASTMincaml.LE;
import Parser.ASTMincaml.Let;
import Parser.ASTMincaml.LetRec;
import Parser.ASTMincaml.LetTuple;
import Parser.ASTMincaml.Neg;
import Parser.ASTMincaml.Not;
import Parser.ASTMincaml.Put;
import Parser.ASTMincaml.Sub;
import Parser.ASTMincaml.Tuple;
import Parser.ASTMincaml.Unit;
import Parser.ASTMincaml.Var;
import Parser.Id;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrien
 */
public class ClosureConversionVisitor implements ClosureVisitor {

    @Override
    public Node visit(Unit e) {
        return new Unit();
    }

    @Override
    public Node visit(Bool e) {
        return new Bool(e.b);
    }

    @Override
    public Node visit(Int e) {
        return new Int(e.i);
    }

    @Override
    public Node visit(Flt e) {
        return new Flt(e.f);
    }

    @Override
    public Node visit(Not e) {
        return new Not((Exp) e.e.accept(this));
    }

    @Override
    public Node visit(Neg e) {
        return new Neg((Exp) e.e.accept(this));
    }

    @Override
    public Node visit(Add e) {
        return new Add((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(Sub e) {
        return new Sub((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(FNeg e) {
        return new FNeg((Exp) e.e.accept(this));
    }

    @Override
    public Node visit(FAdd e) {
        return new FAdd((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(FSub e) {
        return new FSub((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(FMul e) {
        return new FMul((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(FDiv e) {
        return new FDiv((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(Eq e) {
        return new Eq((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(LE e) {
        return new LE((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(If e) {
        return new If((Exp) e.e1.accept(this), (Exp) e.e2.accept(this), (Exp) e.e3.accept(this));
    }

    @Override
    public Node visit(Let e) {
        return new Let(e.id, e.t, (Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(Var e) {
        return new Var(e.id);
    }

    @Override
    public Node visit(LetRec e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node visit(App e) {
        List<Exp> exps = new ArrayList<>();
        for (Exp ex : e.es) {
            exps.add((Exp) ex.accept(this));
        }
        return new App((Exp) e.e.accept(this), exps);
    }

    @Override
    public Node visit(Tuple e) {
        List<Exp> le = new ArrayList<>();
        for (Exp g : e.es) {
            le.add((Exp) g.accept(this));
        }
        return new Tuple(le);
    }

    @Override
    public Node visit(LetTuple e) {
        return new LetTuple(e.ids, e.ts, (Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(Array e) {
        return new Array((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(Get e) {
        return new Get((Exp) e.e1.accept(this), (Exp) e.e2.accept(this));
    }

    @Override
    public Node visit(Put e) {
        return new Put((Exp) e.e1.accept(this), (Exp) e.e2.accept(this), (Exp) e.e3.accept(this));
    }

}

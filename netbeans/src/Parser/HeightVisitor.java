package Parser;

import Parser.ASTMincaml.FSub;
import Parser.ASTMincaml.Unit;
import Parser.ASTMincaml.Tuple;
import Parser.ASTMincaml.Get;
import Parser.ASTMincaml.LetTuple;
import Parser.ASTMincaml.FDiv;
import Parser.ASTMincaml.Exp;
import Parser.ASTMincaml.App;
import Parser.ASTMincaml.Int;
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
import java.util.*;

public class HeightVisitor implements ObjVisitor<Integer> {

    public Integer visit(Unit e) {
        // This tree is of height 0
        return 0;
    }

    public Integer visit(Bool e) {
        return 0;
    }

    public Integer visit(Int e) {
        return 0;
    }

    public Integer visit(Flt e) { 
        return 0;
    }

    public Integer visit(Not e) {
        return e.e.accept(this) + 1;
    }

    public Integer visit(Neg e) {
        return e.e.accept(this) + 1;
    }

    public Integer visit(Add e) {
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
    }

    public Integer visit(Sub e) {
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
   }

    public Integer visit(FNeg e){
        return e.e.accept(this) + 1;
    }

    public Integer visit(FAdd e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
    }

    public Integer visit(FSub e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
    }

    public Integer visit(FMul e) {
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
     }

    public Integer visit(FDiv e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
    }

    public Integer visit(Eq e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
    }

    public Integer visit(LE e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
    }

    public Integer visit(If e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        int res3 = e.e3.accept(this);
        return Math.max(res1, Math.max(res2, res3)) + 1 ;
    }

    public Integer visit(Let e) {
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2) + 1 ;
    }

    public Integer visit(Var e){
        return 0;
    }

    public Integer visit(LetRec e){
        int res1 = e.e.accept(this);
        int res2 = e.fd.e.accept(this);
        return Math.max(res1, res2) + 1 ;
    }

    public Integer visit(App e){
        int res1 = e.e.accept(this);
        for (Exp exp : e.es) {
            res1 = Math.max(res1, exp.accept(this));
        }
        return res1 + 1;
    }

    public Integer visit(Tuple e){
        int res1 = 0;
        for (Exp exp : e.es) {
            int res = exp.accept(this);
            res1 = Math.max(res, res1);
        }
        return res1 + 1;
    }

    public Integer visit(LetTuple e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2);
    }

    public Integer visit(Array e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2);
    }

    public Integer visit(Get e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        return Math.max(res1, res2);
    }

    public Integer visit(Put e){
        int res1 = e.e1.accept(this);
        int res2 = e.e2.accept(this);
        int res3 = e.e3.accept(this);
        return Math.max(res1, Math.max(res2, res3)) + 1 ;
    }
}



package Parser;

import Parser.ASTMincaml.FSub;
import Parser.ASTMincaml.Unit;
import Parser.ASTMincaml.Tuple;
import Parser.ASTMincaml.Get;
import Parser.ASTMincaml.LetTuple;
import Parser.ASTMincaml.FDiv;
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

public interface Visitor {

    void visit(Unit e);
    void visit(Bool e);
    void visit(Int e);
    void visit(Flt e);
    void visit(Not e);
    void visit(Neg e);
    void visit(Add e);
    void visit(Sub e);
    void visit(FNeg e);
    void visit(FAdd e);
    void visit(FSub e);
    void visit(FMul e);
    void visit(FDiv e);
    void visit(Eq e);
    void visit(LE e);
    void visit(If e);
    void visit(Let e);
    void visit(Var e);
    void visit(LetRec e);
    void visit(App e);
    void visit(Tuple e);
    void visit(LetTuple e);
    void visit(Array e);
    void visit(Get e);
    void visit(Put e);
}



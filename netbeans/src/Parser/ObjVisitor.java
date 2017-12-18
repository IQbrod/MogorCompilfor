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
import Parser.ASTMincaml.Eq;
import Parser.ASTMincaml.FNeg;

public interface ObjVisitor<E> {
    E visit(Unit e);
    E visit(Bool e);
    E visit(Int e);
    E visit(Flt e);
    E visit(Not e);
    E visit(Neg e);
    E visit(Add e);
    E visit(Sub e);
    E visit(FNeg e);
    E visit(FAdd e);
    E visit(FSub e);
    E visit(FMul e);
    E visit(FDiv e);
    E visit(Eq e);
    E visit(LE e);
    E visit(If e);
    E visit(Let e);
    E visit(Var e);
    E visit(LetRec e);
    E visit(App e);
    E visit(Tuple e);
    E visit(LetTuple e);
    E visit(Array e);
    E visit(Get e);
    E visit(Put e);
}



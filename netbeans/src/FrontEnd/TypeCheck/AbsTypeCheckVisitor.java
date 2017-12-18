/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;
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
import java.util.ArrayList;
import Parser.Type.*;
/**
 *
 * @author givaudav
 */
public interface AbsTypeCheckVisitor {
    void visit(Unit e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Bool e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Int e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Flt e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Not e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Neg e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Add e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Sub e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(FNeg e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(FAdd e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(FSub e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(FMul e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(FDiv e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Eq e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(LE e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(If e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Let e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Var e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(LetRec e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(App e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Tuple e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(LetTuple e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Array e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Get e, Environnement env, Type type, ArrayList<Equation> arr);
    void visit(Put e, Environnement env, Type type, ArrayList<Equation> arr);
}

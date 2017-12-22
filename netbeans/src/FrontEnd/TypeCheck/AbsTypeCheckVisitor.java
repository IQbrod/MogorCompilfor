/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;
import Parser.ASTMincaml.*;
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

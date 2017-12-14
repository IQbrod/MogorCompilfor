/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;
import Parser.AST.*;
import java.util.ArrayList;
import Parser.Type.*;
/**
 *
 * @author givaudav
 */
public interface AbsTypeCheckVisitor {
    ArrayList<Equation> visit(Unit e, Environnement env, Type type);
    ArrayList<Equation> visit(Bool e, Environnement env, Type type);
    ArrayList<Equation> visit(Int e, Environnement env, Type type);
    ArrayList<Equation> visit(Flt e, Environnement env, Type type);
    ArrayList<Equation> visit(Not e, Environnement env, Type type);
    ArrayList<Equation> visit(Neg e, Environnement env, Type type);
    ArrayList<Equation> visit(Add e, Environnement env, Type type);
    ArrayList<Equation> visit(Sub e, Environnement env, Type type);
    ArrayList<Equation> visit(FNeg e, Environnement env, Type type);
    ArrayList<Equation> visit(FAdd e, Environnement env, Type type);
    ArrayList<Equation> visit(FSub e, Environnement env, Type type);
    ArrayList<Equation> visit(FMul e, Environnement env, Type type);
    ArrayList<Equation> visit(FDiv e, Environnement env, Type type);
    ArrayList<Equation> visit(Eq e, Environnement env, Type type);
    ArrayList<Equation> visit(LE e, Environnement env, Type type);
    ArrayList<Equation> visit(If e, Environnement env, Type type);
    ArrayList<Equation> visit(Let e, Environnement env, Type type);
    ArrayList<Equation> visit(Var e, Environnement env, Type type);
    ArrayList<Equation> visit(LetRec e, Environnement env, Type type);
    ArrayList<Equation> visit(App e, Environnement env, Type type);
    ArrayList<Equation> visit(Tuple e, Environnement env, Type type);
    ArrayList<Equation> visit(LetTuple e, Environnement env, Type type);
    ArrayList<Equation> visit(Array e, Environnement env, Type type);
    ArrayList<Equation> visit(Get e, Environnement env, Type type);
    ArrayList<Equation> visit(Put e, Environnement env, Type type);
}

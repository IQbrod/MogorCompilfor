/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.IV_ClosureConversion;

import Parser.*;
import java.util.*;
import Parser.ASTMincaml.*;

public interface ClosureVisitor {

    public Exp visit(Unit e);
    public Exp visit(Bool e);
    public Exp visit(Int e);
    public Exp visit(Flt e);
    public Exp visit(Not e);
    public Exp visit(Neg e);
    public Exp visit(Add e);
    public Exp visit(Sub e);
    public Exp visit(FNeg e);
    public Exp visit(FAdd e);
    public Exp visit(FSub e);
    public Exp visit(FMul e);
    public Exp visit(FDiv e);
    public Exp visit(Eq e);
    public Exp visit(LE e);
    public Exp visit(If e);
    public Exp visit(Let e);
    public Exp visit(Var e);
    public Exp visit(LetRec e);
    public Exp visit(App e);
    public Exp visit(Tuple e);
    public Exp visit(LetTuple e);
    public Exp visit(Array e);
    public Exp visit(Get e);
    public Exp visit(Put e);
}



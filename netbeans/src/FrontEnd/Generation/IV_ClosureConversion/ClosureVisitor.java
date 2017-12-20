/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.IV_ClosureConversion;

import FrontEnd.Generation.IV_ClosureConversion.Nodes.Node;
import Parser.*;
import java.util.*;
import Parser.ASTMincaml.*;

public interface ClosureVisitor {

    public Node visit(Unit e);
    public Node visit(Bool e);
    public Node visit(Int e);
    public Node visit(Flt e);
    public Node visit(Not e);
    public Node visit(Neg e);
    public Node visit(Add e);
    public Node visit(Sub e);
    public Node visit(FNeg e);
    public Node visit(FAdd e);
    public Node visit(FSub e);
    public Node visit(FMul e);
    public Node visit(FDiv e);
    public Node visit(Eq e);
    public Node visit(LE e);
    public Node visit(If e);
    public Node visit(Let e);
    public Node visit(Var e);
    public Node visit(LetRec e);
    public Node visit(App e);
    public Node visit(Tuple e);
    public Node visit(LetTuple e);
    public Node visit(Array e);
    public Node visit(Get e);
    public Node visit(Put e);
}



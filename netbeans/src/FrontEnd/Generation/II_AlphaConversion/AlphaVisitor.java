package FrontEnd.Generation.II_AlphaConversion;

import Parser.*;
import java.util.*;
import Parser.AST.*;

public interface AlphaVisitor {

    public Exp visit(Unit e, ArrayList<Ids> changements);
    public Exp visit(Bool e, ArrayList<Ids> changements);
    public Exp visit(Int e, ArrayList<Ids> changements);
    public Exp visit(Flt e, ArrayList<Ids> changements);
    public Exp visit(Not e, ArrayList<Ids> changements);
    public Exp visit(Neg e, ArrayList<Ids> changements);
    public Exp visit(Add e, ArrayList<Ids> changements);
    public Exp visit(Sub e, ArrayList<Ids> changements);
    public Exp visit(FNeg e, ArrayList<Ids> changements);
    public Exp visit(FAdd e, ArrayList<Ids> changements);
    public Exp visit(FSub e, ArrayList<Ids> changements);
    public Exp visit(FMul e, ArrayList<Ids> changements);
    public Exp visit(FDiv e, ArrayList<Ids> changements);
    public Exp visit(Eq e, ArrayList<Ids> changements);
    public Exp visit(LE e, ArrayList<Ids> changements);
    public Exp visit(If e, ArrayList<Ids> changements);
    public Exp visit(Let e, ArrayList<Ids> changements);
    public Exp visit(Var e, ArrayList<Ids> changements);
    public Exp visit(LetRec e, ArrayList<Ids> changements);
    public Exp visit(App e, ArrayList<Ids> changements);
    public Exp visit(Tuple e, ArrayList<Ids> changements);
    public Exp visit(LetTuple e, ArrayList<Ids> changements);
    public Exp visit(Array e, ArrayList<Ids> changements);
    public Exp visit(Get e, ArrayList<Ids> changements);
    public Exp visit(Put e, ArrayList<Ids> changements);
}



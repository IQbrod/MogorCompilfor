package Parser.AST;

import java.util.*;
import Parser.*;
import FrontEnd.TypeCheck.*;
import Parser.Type.*;

public abstract class Exp {
    public abstract void accept(Visitor v);

    public abstract <E> E accept(ObjVisitor<E> v);
    
    public abstract ArrayList<Equation> accept(AbsTypeCheckVisitor v, Environnement env, Type t);
}
 

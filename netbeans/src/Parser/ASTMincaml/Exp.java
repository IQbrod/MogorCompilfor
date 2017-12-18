package Parser.ASTMincaml;

import java.util.*;
import Parser.*;
import FrontEnd.TypeCheck.*;
import Parser.Type.*;

public abstract class Exp {
    public abstract void accept(Visitor v);

    public abstract <E> E accept(ObjVisitor<E> v);
    
    public abstract void accept(AbsTypeCheckVisitor v, Environnement env, Type t, ArrayList<Equation> arr);
}
 
package Parser.ASTMincaml;

import FrontEnd.Generation.II_AlphaConversion.AlphaConversionVisitor;
import FrontEnd.Generation.II_AlphaConversion.Ids;
import FrontEnd.Generation.IV_ClosureConversion.ClosureVisitor;
import java.util.*;
import Parser.*;
import FrontEnd.TypeCheck.*;
import Parser.Type.*;

public abstract class Exp {
    public abstract void accept(Visitor v);

    public abstract <E> E accept(ObjVisitor<E> v);
    
    public abstract void accept(AbsTypeCheckVisitor v, Environnement env, Type t, ArrayList<Equation> arr);
    
    public abstract Exp accept(AlphaConversionVisitor v, ArrayList<Ids> changements);
    
    public abstract Exp accept(ClosureVisitor v);
}
 

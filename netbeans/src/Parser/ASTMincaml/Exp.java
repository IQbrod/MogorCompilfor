package Parser.ASTMincaml;

import FrontEnd.Generation.II_AlphaConversion.AlphaConversionVisitor;
import FrontEnd.Generation.II_AlphaConversion.Ids;
import FrontEnd.Generation.IV_ClosureConversion.ClosureVisitor;
import FrontEnd.Generation.IV_ClosureConversion.Nodes.Node;
import java.util.*;
import Parser.*;
import FrontEnd.TypeCheck.*;
import Parser.Type.*;

public abstract class Exp extends Node {
    public abstract void accept(Visitor v);

    public abstract <E> E accept(ObjVisitor<E> v);
    
    public abstract void accept(AbsTypeCheckVisitor v, Environnement env, Type t, ArrayList<Equation> arr);
    
    public abstract Exp accept(AlphaConversionVisitor v, ArrayList<Ids> changements);
}
 

package Parser.AST;

import java.util.*;
import Parser.*;

public abstract class Exp {
    public abstract void accept(Visitor v);

    public abstract <E> E accept(ObjVisitor<E> v);
}
 

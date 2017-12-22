package Parser.ASTMincaml;

import FrontEnd.Generation.II_AlphaConversion.AlphaConversionVisitor;
import FrontEnd.Generation.II_AlphaConversion.Ids;
import FrontEnd.Generation.IV_ClosureConversion.ClosureVisitor;
import FrontEnd.TypeCheck.AbsTypeCheckVisitor;
import FrontEnd.TypeCheck.Environnement;
import FrontEnd.TypeCheck.Equation;
import Parser.ASTMincaml.Exp;
import Parser.ASTMincaml.FunDef;
import Parser.ObjVisitor;
import Parser.Type.Type;
import Parser.Visitor;
import java.util.ArrayList;

public class Fct extends Exp {

    public final FunDef fd;
    public Exp suite;
    
    public Fct(FunDef fd, Exp suite) {
        this.fd = fd;
        this.suite = suite;
    }


    @Override
    public <E> E accept(ObjVisitor<E> v) {
        throw new UnsupportedOperationException("La methode n'est pas implémentée, ce noeud n'est pas utilisé dans ce cas");
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public void accept(AbsTypeCheckVisitor v, Environnement env, Type t, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("La methode n'est pas implémentée, ce noeud n'est pas utilisé dans ce cas");
    }

    @Override
    public Exp accept(AlphaConversionVisitor v, ArrayList<Ids> changements) {
        throw new UnsupportedOperationException("La methode n'est pas implémentée, ce noeud n'est pas utilisé dans ce cas");
    }
    
    @Override
    public Exp accept(ClosureVisitor v) {
        return v.visit(this);
    }
}

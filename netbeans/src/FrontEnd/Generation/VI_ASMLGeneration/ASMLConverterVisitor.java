/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.VI_ASMLGeneration;

import FrontEnd.Generation.VI_ASMLGeneration.ASTAsml.*;
import FrontEnd.Generation.VI_ASMLGeneration.Exceptions.BoolException;
import Parser.ASTMincaml.*;
import Parser.ObjVisitor;

/**
 *
 * @author sazeratj
 */
public class ASMLConverterVisitor implements ObjVisitor<ASMLNode> {

    @Override
    public ASMLNode visit(Unit e) {
        return new Anop();
    }

    @Override
    public ASMLNode visit(Bool e) {
        try {
            throw new BoolException();
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ASMLNode visit(Int e) {
        return new Aint(e.i);
    }

    @Override
    public ASMLNode visit(Flt e) {
        return new Afloat(e.f);
    }

    @Override
    public ASMLNode visit(Not e) {
        ASMLNode x = e.e.accept(this);
        return new Anot((Abool)x); //The node following Not must be a boolean
    }

    @Override
    public ASMLNode visit(Neg e) {
        ASMLNode x = e.e.accept(this);
        return new Aneg((Aident)x);
    }

    @Override
    public ASMLNode visit(Add e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        return new Aadd((Aident)x,(AvarOrImm)y);
    }

    @Override
    public ASMLNode visit(Sub e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        return new Asub((Aident)x,(AvarOrImm)y);
    }

    @Override
    public ASMLNode visit(FNeg e) {
        ASMLNode x = e.e.accept(this);
        return new Afneg((Aident)x);
    }

    @Override
    public ASMLNode visit(FAdd e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        return new Afadd((Aident)x,(Aident)y);
    }

    @Override
    public ASMLNode visit(FSub e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        return new Afsub((Aident)x,(Aident)y);
    }

    @Override
    public ASMLNode visit(FMul e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        return new Afmul((Aident)x,(Aident)y);
    }

    @Override
    public ASMLNode visit(FDiv e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        return new Afdiv((Aident)x,(Aident)y);
    }

    @Override
    public ASMLNode visit(Eq e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        return new Aeq((Aident)x,(AvarOrImm)y);
    }

    @Override
    public ASMLNode visit(LE e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        return new Ale((Aident)x,(AvarOrImm)y);
    }

    @Override
    public ASMLNode visit(If e) {
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        ASMLNode z = e.e3.accept(this);
        return new Aif((Abool)x,(ASMLexp)y,(ASMLexp)z);
    }

    @Override
    public ASMLNode visit(Let e) {
        Aident i = new Aident(e.id.toString());
        ASMLNode x = e.e1.accept(this);
        ASMLNode y = e.e2.accept(this);
        try {
            return new Alet(i,(ASMLexp)x,(ASMLexp)y);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ASMLNode visit(Var e) {
        return new Aident(e.toString());
    }

    @Override
    public ASMLNode visit(LetRec e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ASMLNode visit(App e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ASMLNode visit(Tuple e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ASMLNode visit(LetTuple e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ASMLNode visit(Array e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ASMLNode visit(Get e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ASMLNode visit(Put e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

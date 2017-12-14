/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;

import Parser.AST.*;
import Parser.Type.*;
import java.util.ArrayList;
/**
 *
 * @author sazeratj
 */
public class TypeCheckVisitor {
    public ArrayList<Equation> visit(Unit e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TUnit()));
        return arr;
    }

    public ArrayList<Equation> visit(Bool e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TBool()));
        return arr;
    }

    public ArrayList<Equation> visit(Int e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TInt()));
        return arr;
    }

    public ArrayList<Equation> visit(Flt e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TFloat()));
        return arr;
    }

    public ArrayList<Equation> visit(Not e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TBool()));
        arr.addAll(e.e.accept(this));
        return arr;
    }

    public ArrayList<Equation> visit(Neg e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TInt()));
        arr.addAll(e.e.accept(this));
        return arr;
    }

    public ArrayList<Equation> visit(Add e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TUnit()));
        arr.addAll(e.e1.accept(this));
        arr.addAll(e.e2.accept(this));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Sub e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TInt()));
        arr.addAll(e.e1.accept(this));
        arr.addAll(e.e2.accept(this));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(FNeg e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(FAdd e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(FSub e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(FMul e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(FDiv e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Eq e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(LE e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(If e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TInt()));
        arr.addAll(e.e1.accept(this));
        arr.addAll(e.e2.accept(this));
        arr.addAll(e.e3.accept(this));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Let e) {
        this.env.ajout(e.id.toString(), e.t);
        ArrayList<Equation> arr = new ArrayList();
        arr.addAll(e.e1.accept(this));
        arr.addAll(e.e2.accept(this));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Var e) {
        Type t1 = this.env.getTypeById(e.id.toString());
        if (t1 == null) {
            return null; //GERER LES EXCEPTIONS
        } else {
            ArrayList<Equation> arr = new ArrayList();
            arr.add(new Equation(e, t1));
            return arr;
        }
    }

    @Override
    public ArrayList<Equation> visit(LetRec e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(App e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Tuple e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(LetTuple e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Array e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Get e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Put e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

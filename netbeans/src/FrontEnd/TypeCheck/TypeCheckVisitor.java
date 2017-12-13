/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;

import Parser.*;
import Parser.AST.*;
import Parser.Type.*;
import java.util.ArrayList;
/**
 *
 * @author sazeratj
 */
public class TypeCheckVisitor implements ObjVisitor<ArrayList<Equation>> {

    @Override
    public ArrayList<Equation> visit(Unit e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TUnit()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Bool e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TBool()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Int e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Flt e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(e, new TFloat()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Not e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.addAll(e.e.accept(this));
        arr.add(new Equation(e, new TBool()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Neg e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Add e) {
        ArrayList<Equation> arr = new ArrayList();
        arr.addAll(e.e1.accept(this));
        arr.addAll(e.e2.accept(this));
        arr.add(new Equation(e, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Sub e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Let e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Var e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

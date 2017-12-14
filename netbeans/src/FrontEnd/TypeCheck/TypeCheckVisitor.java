/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;

import Parser.AST.*;
import Parser.Type.*;
import static java.lang.System.exit;
import java.util.ArrayList;
/**
 *
 * @author sazeratj
 */
public class TypeCheckVisitor implements AbsTypeCheckVisitor {

    @Override
    public ArrayList<Equation> visit(Unit e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TUnit(), type));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Bool e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TBool(), type));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Int e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Flt e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TFloat(), type));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Not e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TBool(), type));
        arr.addAll(e.e.accept(this, env, new TBool()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Neg e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Add e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e1.accept(this, env, new TInt()));
        arr.addAll(e.e2.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Sub e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e1.accept(this, env, new TInt()));
        arr.addAll(e.e2.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(FNeg e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(FAdd e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e1.accept(this, env, new TInt()));
        arr.addAll(e.e2.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(FSub e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e1.accept(this, env, new TInt()));
        arr.addAll(e.e2.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(FMul e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e1.accept(this, env, new TInt()));
        arr.addAll(e.e2.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(FDiv e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e1.accept(this, env, new TInt()));
        arr.addAll(e.e2.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Eq e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e1.accept(this, env, new TInt()));
        arr.addAll(e.e2.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(LE e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TInt(), type));
        arr.addAll(e.e1.accept(this, env, new TInt()));
        arr.addAll(e.e2.accept(this, env, new TInt()));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(If e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TBool(), type));
        arr.addAll(e.e1.accept(this, env, new TBool()));
        arr.addAll(e.e2.accept(this, env, type));
        arr.addAll(e.e3.accept(this, env, type));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Let e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.addAll(e.e1.accept(this, env, e.t));
        env.ajout(e.id.toString(), e.t);
        arr.addAll(e.e2.accept(this, env, type));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Var e, Environnement env, Type type) {
        Type t = env.getTypeById(e.id.toString());
        if (t == null) {
            System.out.println("Variable non trouvée");
            exit(0);
        }
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(t, type));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(LetRec e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.add(new Equation(new TUnit(), type));
        arr.addAll(e.e.accept(this, env, type));
        env.ajout(e.fd.id.toString(), e.fd.type);
        arr.addAll(e.fd.e.accept(this, env, e.fd.type));
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(App e, Environnement env, Type type) {
        ArrayList<Equation> arr = new ArrayList();
        arr.addAll(e.e.accept(this, env, type));
        for (int i = 0; i<e.es.size();i++) {
            arr.addAll(e.es.get(i).accept(this, env, type));
        }
        return arr;
    }

    @Override
    public ArrayList<Equation> visit(Tuple e, Environnement env, Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(LetTuple e, Environnement env, Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Array e, Environnement env, Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Get e, Environnement env, Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Equation> visit(Put e, Environnement env, Type type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

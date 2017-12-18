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
    public void visit(Unit e, Environnement env, Type type, ArrayList<Equation> arr) {
        arr.add(new Equation(new TUnit(), type));
    }

    @Override
    public void visit(Bool e, Environnement env, Type type, ArrayList<Equation> arr) {
        arr.add(new Equation(new TBool(), type));
    }

    @Override
    public void visit(Int e, Environnement env, Type type, ArrayList<Equation> arr) {
        System.out.println("Visite de Int");
        arr.add(new Equation(new TInt(), type));
    }

    @Override
    public void visit(Flt e, Environnement env, Type type, ArrayList<Equation> arr) {
        arr.add(new Equation(new TFloat(), type));
    }

    @Override
    public void visit(Not e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e.accept(this, env, new TBool(), arr);
        arr.add(new Equation(new TBool(), type));
    }

    @Override
    public void visit(Neg e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e.accept(this, env, new TInt(), arr);
        arr.add(new Equation(new TInt(), type));
    }

    @Override
    public void visit(Add e, Environnement env, Type type, ArrayList<Equation> arr) {
        System.out.println("Visite de Add");
        e.e1.accept(this, env, new TInt(), arr);
        e.e2.accept(this, env, new TInt(), arr);
        arr.add(new Equation(new TInt(), type));
    }

    @Override
    public void visit(Sub e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e1.accept(this, env, new TInt(), arr);
        e.e2.accept(this, env, new TInt(), arr);
        arr.add(new Equation(new TInt(), type));
    }

    @Override
    public void visit(FNeg e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e.accept(this, env, new TFloat(), arr);
        arr.add(new Equation(new TFloat(), type));
    }

    @Override
    public void visit(FAdd e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e1.accept(this, env, new TFloat(), arr);
        e.e2.accept(this, env, new TFloat(), arr);
        arr.add(new Equation(new TFloat(), type));
    }

    @Override
    public void visit(FSub e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e1.accept(this, env, new TFloat(), arr);
        e.e2.accept(this, env, new TFloat(), arr);
        arr.add(new Equation(new TFloat(), type));
    }

    @Override
    public void visit(FMul e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e1.accept(this, env, new TFloat(), arr);
        e.e2.accept(this, env, new TFloat(), arr);
        arr.add(new Equation(new TFloat(), type));
    }

    @Override
    public void visit(FDiv e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e1.accept(this, env, new TFloat(), arr);
        e.e2.accept(this, env, new TFloat(), arr);
        arr.add(new Equation(new TFloat(), type));
    }

    @Override
    public void visit(Eq e, Environnement env, Type type, ArrayList<Equation> arr) {
        if (e.e1 instanceof Int) {
            e.e1.accept(this, env, new TInt(), arr);
            e.e1.accept(this, env, new TInt(), arr);
        } else if (e.e1 instanceof Flt) {
            e.e1.accept(this, env, new TFloat(), arr);
            e.e1.accept(this, env, new TFloat(), arr);
        }
        arr.add(new Equation(new TBool(), type));
    }

    @Override
    public void visit(LE e, Environnement env, Type type, ArrayList<Equation> arr) {
        if (e.e1 instanceof Int) {
            e.e1.accept(this, env, new TInt(), arr);
            e.e1.accept(this, env, new TInt(), arr);
        } else if (e.e1 instanceof Flt) {
            e.e1.accept(this, env, new TFloat(), arr);
            e.e1.accept(this, env, new TFloat(), arr);
        }
        arr.add(new Equation(new TBool(), type));
    }

    @Override
    public void visit(If e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e1.accept(this, env, new TBool(), arr);
        e.e2.accept(this, env, new TUnit(), arr);
        e.e3.accept(this, env, new TUnit(), arr);
        arr.add(new Equation(new TUnit(), type));
    }

    @Override
    public void visit(Let e, Environnement env, Type type, ArrayList<Equation> arr) {
        System.out.println("Visite de Let");
        e.e1.accept(this, env, e.t, arr);
        env.ajout(e.id.toString(), e.t);
        e.e2.accept(this, env, type, arr);
    }

    @Override
    public void visit(Var e, Environnement env, Type type, ArrayList<Equation> arr) {
        System.out.println("Visite de Var");
        Type t = env.getTypeById(e.id.toString());
        arr.add(new Equation(t, type));
    }

    @Override
    public void visit(LetRec e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*App M M1..MN type
        B -> type
        eq1 = GenEquations(M -> A)  
        eq2 = GenEquations(Mi -> Ai)
    */
    
    @Override
    public void visit(App e, Environnement env, Type type, ArrayList<Equation> arr) {
        System.out.println("Visite de App");
        e.e.accept(this, env, type, arr);
        for (int i = 0; i<e.es.size(); i++) {
            e.es.get(i).accept(this, env, type, arr);
        }
    }

    @Override
    public void visit(Tuple e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(LetTuple e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Array e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Get e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Put e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}

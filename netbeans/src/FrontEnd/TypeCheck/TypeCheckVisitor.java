/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;

import Parser.ASTMincaml.*;
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
        e.e2.accept(this, env, type, arr);
        e.e3.accept(this, env, type, arr);
    }

    @Override
    public void visit(Let e, Environnement env, Type type, ArrayList<Equation> arr) {
        e.e1.accept(this, env, e.t, arr);
        env.ajout(e.id.toString(), e.t);
        e.e2.accept(this, env, type, arr);
    }

    @Override
    public void visit(Var e, Environnement env, Type type, ArrayList<Equation> arr) {
        Type t = env.getTypeById(e.id.toString());
        if (t == null) {
            System.err.println("\033[31mVariable non trouvée\033[0m");
            exit(1);
        } else if (t instanceof TTuple) {
            Type tOut = ((TTuple)t).ts.get(((TTuple)t).ts.size()-1);
            arr.add(new Equation(tOut, type));
        } else {
            arr.add(new Equation(t, type));
        }
    }

    @Override
    public void visit(LetRec e, Environnement env, Type type, ArrayList<Equation> arr) {
        ArrayList<Type> arrT = new ArrayList();
        for (int i = 0; i<e.fd.args.size(); i++) {
            Type t = Type.gen();
            env.ajout(e.fd.args.get(i).toString(), t);
            arrT.add(t);
        }
        Type t = Type.gen();
        arrT.add(t);
        TTuple fT = new TTuple(arrT);
        env.ajout(e.fd.id.toString(), fT);
        e.fd.e.accept(this, env, t, arr);
        env.ajout(e.fd.id.toString(), fT);
        e.e.accept(this, env, type, arr);
    }

    @Override
    public void visit(App e, Environnement env, Type type, ArrayList<Equation> arr) {
        if (e.e instanceof App) {
            e.e.accept(this, env, type, arr);
        } else {
            Type envT = env.getTypeById(e.e.toString());
            if (envT == null) {
                System.out.println("\033[31mFonction non trouvée.\033[0m");
                exit(1);
            } else {
                if (envT instanceof TVar) {
                    TVar appT = (TVar)envT;
                    if (e.es.size() > 1) {
                        System.out.println("\033[31mNombre d'arguments incorrect.\033[0m");
                        exit(1);
                    } else {
                        arr.add(new Equation(appT,type));
                        e.es.get(0).accept(this, env, appT, arr);
                    }
                } else {
                    TTuple appT = (TTuple)envT;
                    if (appT.ts.size()-1 != e.es.size()) {
                        System.out.println("\033[31mNombre d'arguments incorrect.\033[0m");
                        exit(1);
                    } else {
                        arr.add(new Equation(appT.ts.get(appT.ts.size()-1),type));
                        for (int i = 0; i<e.es.size(); i++) {
                            e.es.get(i).accept(this, env, appT.ts.get(i), arr);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void visit(Tuple e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not implemented yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(LetTuple e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not implemented yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Array e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not implemented yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Get e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not implemented yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(Put e, Environnement env, Type type, ArrayList<Equation> arr) {
        throw new UnsupportedOperationException("Not implemented yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
 
}

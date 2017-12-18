package Parser;

import Parser.ASTMincaml.FSub;
import Parser.ASTMincaml.Unit;
import Parser.ASTMincaml.Tuple;
import Parser.ASTMincaml.Get;
import Parser.ASTMincaml.LetTuple;
import Parser.ASTMincaml.FDiv;
import Parser.ASTMincaml.App;
import Parser.ASTMincaml.Exp;
import Parser.ASTMincaml.Int;
import Parser.ASTMincaml.Bool;
import Parser.ASTMincaml.Let;
import Parser.ASTMincaml.Sub;
import Parser.ASTMincaml.Array;
import Parser.ASTMincaml.LetRec;
import Parser.ASTMincaml.Flt;
import Parser.ASTMincaml.Put;
import Parser.ASTMincaml.LE;
import Parser.ASTMincaml.If;
import Parser.ASTMincaml.Var;
import Parser.ASTMincaml.Not;
import Parser.ASTMincaml.FMul;
import Parser.ASTMincaml.Neg;
import Parser.ASTMincaml.Add;
import Parser.ASTMincaml.FAdd;
import Parser.ASTMincaml.FNeg;
import Parser.ASTMincaml.Eq;
import java.util.*;

public class ToStringVisitor implements ObjVisitor<String> {
    @Override
    public String visit(Unit e) {
        return "()";
    }

    @Override
    public String visit(Bool e) {
        return Boolean.toString(e.b);
    }

    @Override
    public String visit(Int e) {
        return Integer.toString(e.i);
    }

    @Override
    public String visit(Flt e) {
        String s = String.format("%.2f", e.f);
        return s;
    }

    @Override
    public String visit(Not e) {
        String s = "(not ";
        s+=e.e.accept(this);
        s += ")";
        return s;
    }

    @Override
    public String visit(Neg e) {
        String s = "(- ";
        s+=e.e.accept(this);
        s+=")";
        return s;
    }

    @Override
    public String visit(Add e) {
        String s = "(";
        s+=e.e1.accept(this);
        s+=" + ";
        s+=e.e2.accept(this);
        s+=")";
        return s;
    }

    @Override
    public String visit(Sub e) {
        String s = "(";
        s+=e.e1.accept(this);
        s+=" - ";
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(FNeg e){
        String s = "(-. ";
        s+=e.e.accept(this);
        s+=")";
        return s;
    }

    @Override
    public String visit(FAdd e){
        String s = "(";
        s+=e.e1.accept(this);
        s+=(" +. ");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(FSub e){
        String s = "(";
        s+=e.e1.accept(this);
        s+=(" -. ");
        s+=e.e2.accept(this);
        s+=")";
        return s;
    }

    @Override
    public String visit(FMul e) {
        String s = ("(");
        s+=e.e1.accept(this);
        s+=(" *. ");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(FDiv e){
        String s = ("(");
        s+=e.e1.accept(this);
        s+=(" /. ");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(Eq e){
        String s = ("(");
        s+=e.e1.accept(this);
        s+=(" = ");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(LE e){
        String s = ("(");
        s+=e.e1.accept(this);
        s+=(" <= ");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(If e){
        String s = ("(if ");
        s+=e.e1.accept(this);
        s+=(" then ");
        s+=e.e2.accept(this);
        s+=(" else ");
        s+=e.e3.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(Let e) {
        String s = ("(let ");
        s+=(e.id.toString());
        s+=(" = ");
        s+=e.e1.accept(this);
        s+=(" in ");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(Var e){
        return (e.id.toString());
    }


    // print sequence of identifiers 
    static <E> String printInfix(List<E> l, String op) {
        if (l.isEmpty()) {
            return "";
        }
        Iterator<E> it = l.iterator();
        String s = "";
        s+=(it.next());
        while (it.hasNext()) {
            s+=(op + it.next());
        }
        return s;
    }

    // print sequence of Exp
    String printInfix2(List<Exp> l, String op) {
        if (l.isEmpty()) {
            return "";
        }
        Iterator<Exp> it = l.iterator();
        String s = "";
        s+=it.next().accept(this);
        while (it.hasNext()) {
            s+=(op);
            s+=it.next().accept(this);
        }
        return s;
    }

    @Override
    public String visit(LetRec e){
        String s = ("(let rec " + e.fd.id.toString() + " ");
        s+=printInfix(e.fd.args, " ");
        s+=(" = ");
        s+=e.fd.e.accept(this);
        s+=(" in ");
        s+=e.e.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(App e){
        String s = ("(");
        s+=e.e.accept(this);
        s+=(" ");
        s+=printInfix2(e.es, " ");
        s+=(")");
        return s;
    }

    @Override
    public String visit(Tuple e){
        String s =("(");
        s+=printInfix2(e.es, ", ");
        s+=(")");
        return s;
    }

    @Override
    public String visit(LetTuple e){
        String s = ("(let (");
        s+=printInfix(e.ids, ", ");
        s+=(") = ");
        s+=e.e1.accept(this);
        s+=(" in ");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(Array e){
        String s = ("(Array.create ");
        s+=e.e1.accept(this);
        s+=(" ");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(Get e){
        String s = e.e1.accept(this);
        s+=(".(");
        s+=e.e2.accept(this);
        s+=(")");
        return s;
    }

    @Override
    public String visit(Put e){
        String s = ("(");
        s+=e.e1.accept(this);
        s+=(".(");
        s+=e.e2.accept(this);
        s+=(") <- ");
        s+=e.e3.accept(this);
        s+=(")");
        return s;
    }
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entrainement;

import Parser.ASTMincaml.*;
import Parser.*;

/**
 *
 * @author michauad
 */
public class DuplicateVisitor2 implements ObjVisitor<Exp> {

    @Override
    public Exp visit(Unit e) {
        return new Unit();
    }

    @Override
    public Exp visit(Bool e) {
        return new Bool(e.b);
    }

    @Override
    public Exp visit(Int e) {
        return new Int(e.i);
    }

    @Override
    public Exp visit(Flt e) {
        return new Flt(e.f);
    }

    @Override
    public Exp visit(Not e) {
        return new Not(e.accept(this));
    }

    @Override
    public Exp visit(Neg e) {
        return new Neg(e.e.accept(this));
    }

    @Override
    public Exp visit(Add e) {
        Exp a = e.e1.accept(this);
        Exp b = e.e2.accept(this);
        Add f = new Add(a,b);
        return f;
    }

    @Override
    public Exp visit(Sub e) {
        return new Sub(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(FNeg e) {
        return new FNeg(e.e.accept(this));
    }

    @Override
    public Exp visit(FAdd e) {
        return new FAdd(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(FSub e) {
        return new FSub(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(FMul e) {
        return new FMul(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(FDiv e) {
        return new FDiv(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Eq e) {
        return new Eq(e.e1.accept(this),e.e2.accept(this));
    }

    @Override
    public Exp visit(LE e) {
        return new LE(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(If e) {
        return new If(e.e1.accept(this), e.e2.accept(this), e.e3.accept(this));
    }

    @Override
    public Exp visit(Let e) {
        return new Let(e.id, e.t,e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Var e) {
        return new Var(e.id);
    }

    @Override
    public Exp visit(LetRec e) {
        return new LetRec(e.fd, e.e.accept(this));
    }

    @Override
    public Exp visit(App e) {
        return new App(e.e.accept(this),e.es);
    }

    @Override
    public Exp visit(Tuple e) {
        return new Tuple(e.es);
    }

    @Override
    public Exp visit(LetTuple e) {
        return new LetTuple(e.ids, e.ts, e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Array e) {
        return new Array(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Get e) {
        return new Get(e.e1.accept(this), e.e2.accept(this));
    }

    @Override
    public Exp visit(Put e) {
        return new Put(e.e1.accept(this), e.e2.accept(this),e.e3.accept(this));
    }
    
}

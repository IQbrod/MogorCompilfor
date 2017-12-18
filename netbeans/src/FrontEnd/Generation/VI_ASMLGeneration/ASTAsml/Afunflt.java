/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.VI_ASMLGeneration.ASTAsml;

import FrontEnd.Generation.VI_ASMLGeneration.ASMLVisitor;

/**
 *
 * @author sazeratj
 */
public class Afunflt extends Afundef {
    public Alabel id;
    public Afloat f;
    public Afundef next;
    
    public Afunflt(Alabel arg1, Afloat arg2, Afundef arg3) {
        this.id = arg1;
        this.f = arg2;
        this.next = arg3;
    }
    
    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
    
}

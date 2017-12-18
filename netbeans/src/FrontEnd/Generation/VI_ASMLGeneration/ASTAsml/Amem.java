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
public class Amem extends ASMLexp {
    public Aident e1;
    public AvarOrImm e2;
    public Aident e3;
    
    public Amem(Aident arg1, AvarOrImm arg2) {
        this.e1 = arg1;
        this.e2 = arg2;
        this.e3 = null;
    }
    
    public Amem(Aident arg1, AvarOrImm arg2, Aident arg3) {
        this.e1 = arg1;
        this.e2 = arg2;
        this.e3 = arg3;
    }
    
    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
    
}

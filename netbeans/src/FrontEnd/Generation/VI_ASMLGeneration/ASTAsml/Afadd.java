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
public class Afadd extends ASMLexp {
    public Aident e1;
    public Aident e2;
    
    public Afadd(Aident e1, Aident e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
    
}

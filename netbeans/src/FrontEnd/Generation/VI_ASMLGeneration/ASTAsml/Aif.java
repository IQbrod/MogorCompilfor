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
public class Aif extends ASMLexp {
    public Abool b;
    public ASMLexp e1;
    public ASMLexp e2;
    
    public Aif(Abool arg1, ASMLexp arg2, ASMLexp arg3) {
        this.b = arg1;
        this.e1 = arg2;
        this.e2 = arg3;
    }

    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
    
}

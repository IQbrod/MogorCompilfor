/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.VI_ASMLGeneration.ASTAsml;

import FrontEnd.Generation.VI_ASMLGeneration.ASMLVisitor;
import FrontEnd.Generation.VI_ASMLGeneration.NestedLetException;

/**
 *
 * @author sazeratj
 */
public class Alet extends ASMLexp {
    public Aident e1;
    public ASMLexp e2;
    public ASMLexp e3;
    
    public Alet(Aident arg1, ASMLexp arg2, ASMLexp arg3) throws NestedLetException {
        this.e1 = arg1;
        this.e3 = arg3;
        
        if (arg2 instanceof Alet) {
            throw new NestedLetException();
        } else {
            this.e2 = arg2;
        }
    }

    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
}

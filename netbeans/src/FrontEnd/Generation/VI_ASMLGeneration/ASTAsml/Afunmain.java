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
public class Afunmain extends Afundef {
    public ASMLexp e;
    
    public Afunmain(ASMLexp arg) {
        this.e = arg;
    }

    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
}

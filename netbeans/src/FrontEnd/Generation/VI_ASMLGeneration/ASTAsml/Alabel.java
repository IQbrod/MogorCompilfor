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
public class Alabel extends ASMLexp {
    public String id;
    
    public Alabel(String arg) {
        this.id = arg;
    }
    
    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.VI_ASMLGeneration.ASTAsml;

import FrontEnd.Generation.VI_ASMLGeneration.ASMLVisitor;
import java.util.ArrayList;

/**
 *
 * @author sazeratj
 */
public class Acallclo extends ASMLexp {
    public Aident lb;
    public ArrayList<Aident> args;
    
    public Acallclo(Aident arg1, ArrayList<Aident> arg2) {
        this.lb = arg1;
        this.args = arg2;
    }

    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
}

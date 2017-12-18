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
public class Afunlabel extends Afundef {
    public Alabel id;
    public ArrayList<Aident> args;
    public ASMLexp e;
    public Afundef next;
    
    public Afunlabel(Alabel arg1, ArrayList<Aident> arg2, ASMLexp arg3, Afundef arg4) {
        this.id = arg1;
        this.args = arg2;
        this.e = arg3;
        this.next = arg4;
    }
    
    
    @Override
    public void accept(ASMLVisitor v) {
        v.visit(this);
    }
    
}

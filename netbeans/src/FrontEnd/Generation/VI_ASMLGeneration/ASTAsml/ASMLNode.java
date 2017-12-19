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
public abstract class ASMLNode {
    public abstract void accept(ASMLVisitor v);

    //public abstract <E> E accept(ASMLObjVisitor<E> v);
}

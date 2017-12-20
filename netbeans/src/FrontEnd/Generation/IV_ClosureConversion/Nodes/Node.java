/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.IV_ClosureConversion.Nodes;

import FrontEnd.Generation.IV_ClosureConversion.ClosureVisitor;

/**
 *
 * @author Adrien
 */
public abstract class Node {
    public abstract Node accept(ClosureVisitor v);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser.Type;

/**
 *
 * @author sazeratj
 */
public class TFun extends Type {
    private Type source;
    private Type destination;
    
    public TFun(Type s, Type d) {
        this.source = s;
        this.destination = d;
    }
}

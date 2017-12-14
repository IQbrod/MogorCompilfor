/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.TypeCheck;

import Parser.Type.Type;

/**
 *
 * @author givaudav
 */
public class EnvCouple {
    private String id;
    private Type type;
    
    public EnvCouple(String id, Type type) {
        this.id = id;
        this.type = type;
    }
    
    public String getId() {
        return this.id;
    }
    
    public Type getType() {
        return this.type;
    }
}

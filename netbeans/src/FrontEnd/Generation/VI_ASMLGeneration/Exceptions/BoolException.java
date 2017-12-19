/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd.Generation.VI_ASMLGeneration.Exceptions;

/**
 *
 * @author sazeratj
 */
public class BoolException extends Exception{
    public BoolException() {
        super("AST contains a Bool Node");
    }
}

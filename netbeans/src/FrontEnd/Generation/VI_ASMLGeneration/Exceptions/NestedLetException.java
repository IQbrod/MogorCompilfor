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
public class NestedLetException extends Exception {
    public NestedLetException() {
       super("Nested Lets are forbidden in ASML");
    }
}

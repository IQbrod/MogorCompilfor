/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entrainement.DuplicateVisitor;
import Parser.ASTMincaml.Exp;
import Parser.Lexer;
import Parser.Parser;
import Parser.ToStringVisitor;
import java.io.FileReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author blanctaj
 */
public class DuplicateVisitorTest {
    String rep;
    String mincaml;
    
    public DuplicateVisitorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {    
        /* Calcule du répertoire MINCAML */
        rep = System.getProperty("user.dir");
        rep = rep.substring(0,rep.indexOf("netbeans"));
        mincaml = rep + "mincaml/";
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void simpleDuplicateTest() {
        try {
            Parser p = new Parser(new Lexer(new FileReader(mincaml + "ack.ml")));
            Exp expression = (Exp) p.parse().value;
            assert (expression != null);

            String originalString = expression.accept(new ToStringVisitor());

            Exp duplicate = expression.accept(new DuplicateVisitor());
            String duplicatedString = expression.accept(new ToStringVisitor());
            
            assertEquals("AST dupliqué identique à l'original",originalString,duplicatedString);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}

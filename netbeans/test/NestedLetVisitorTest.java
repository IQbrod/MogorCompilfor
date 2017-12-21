/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import FrontEnd.Generation.I_KNormalisation.KNormVisitor;
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
public class NestedLetVisitorTest {
    String rep;
    String mincaml;
    
    public NestedLetVisitorTest() {
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
    
    @Test //TODO
    public void simpleKNormTest() {
        try {
            Parser p = new Parser(new Lexer(new FileReader(mincaml + "simple_add.ml")));
            Exp expression = (Exp) p.parse().value;
            assert (expression != null);

            String originalString = expression.accept(new ToStringVisitor());

            Exp kNormalized = expression.accept(new KNormVisitor());
            String kNormedString = kNormalized.accept(new ToStringVisitor());
            
            assertEquals("AST KNormalisé correctement","(let x = 1 in (let y = 2 in (let ?v0 = x in (let ?v1 = y in (?v0 + ?v1)))))",kNormedString);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package Parser;

import Parser.AST.Exp;
import java_cup.runtime.*;
import java.io.*;
import java.util.*;

public class Main {
  static public void main(String argv[]) {    
    try {
      String fileName;  
        
      if (argv.length == 0) { // Aucun argument
        /* On récupère le fichier .ml à parser */
        java.util.Scanner s = new java.util.Scanner(System.in);
        System.out.println("Select file to parse (.ml) :");
        fileName = "../mincaml/" + s.nextLine();
        s.close();
        
      } else { // Sinon on utilise l'argument passé en paramètre
          fileName = argv[0];
      }
      
      Parser p = new Parser(new Lexer(new FileReader(fileName)));
      Exp expression = (Exp) p.parse().value;      
      assert (expression != null);

      System.out.println("------ AST ------");
      expression.accept(new PrintVisitor());
      System.out.println();

      System.out.println("------ Height of the AST ----");
      int height = Height.computeHeight(expression);
      System.out.println("using Height.computeHeight: " + height);

      ObjVisitor<Integer> v = new HeightVisitor();
      height = expression.accept(v);
      System.out.println("using HeightVisitor: " + height);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


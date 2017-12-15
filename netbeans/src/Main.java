

import Entrainement.*;
import FrontEnd.Generation.III_ReductionNestedLet.NestedLetVisitor;
import FrontEnd.Generation.I_KNormalisation.KNormVisitor;
import FrontEnd.TypeCheck.*;
import Parser.*;
import Parser.AST.*;
import Parser.Type.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    static String option = "";
    static String fileName = "";
    final static String help = "\n"
        + "╔══════════════════════════════════════════════════════════╗\n"
        + "║         USAGE: ./mincaml [options] [fileName.ml]         ║\n"
        + "╟──────┬──────────────┬───────────────────┬────────────────╢\n"
        + "║      │  --parse, -p │ Parse l'AST       │ default        ║\n"
        + "║      │  --train, -e │ Copie l'AST       │ Entrainement   ║\n"
        + "║  O   │  --type,  -t │ Vérifie le typage │ Type-Check     ║\n"
        + "║  P   │  --knorm, -k │ AST après KNorm   │ KNormalisation ║\n"
        + "║  T   │  --alpha, -a │ AST après AConv   │ AlphaConversion║\n"
        + "║  I   │  --reduc, -r │ AST après RNesLet │ RNestedLet     ║\n"
        + "║  O   │  --close, -c │ AST après CConver │ ClosureConv    ║\n"
        + "║  N   │  --gen,   -g │ Generation ASML   │ ASMLGeneration ║\n"
        + "║      │  --opti,  -o │ ASML après Opti   │ Optimisation   ║\n"
        + "║      │  --help,  -h │ Display Help      │ Help           ║\n"
        + "║      │  --stg,   -s │ Display String AST│ StringVisitor  ║\n"
        + "╟──────┴──────────────┴───────────────────┴────────────────╢\n"
        + "║        fileName.ml :  file in \"mincaml\" directory        ║\n"    
        + "╚══════════════════════════════════════════════════════════╝\n";
    
    static public void main(String argv[]) {    
        /* Calcule du répertoire MINCAML */
        String rep = System.getProperty("user.dir");
        rep = rep.substring(0,rep.indexOf("netbeans"));
        String mincaml = rep + "mincaml/";
        /* Traitement des arguments */
        if (argv.length == 0) { // Aucun argument
            option = "-h";
        } else if (argv.length == 1) { // 1 seul argument (fichier.ml)
            option = argv[0];
            if (! (option.equals("-h") || option.equals("--help")) ) {
                System.out.println("\033[33mMissing Argument FileName\033[0m");
                java.util.Scanner s = new java.util.Scanner(System.in);
                System.out.print("Select file to parse (.ml): ");
                fileName = mincaml + s.nextLine();
                s.close();
            }
        } else { // Sinon on utilise les paramètres
            option = argv[0];
            fileName = argv[1];
        }
       
        /* Traitements des options */
        switch(option) {
            case "-h":
            case "--help":
                System.out.println(help);
                break;
            case "-p":
            case "--parse":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    System.out.print("AST: ");
                    expression.accept(new PrintVisitor());
                    System.out.println();
                    
                    System.out.print("Height: ");
                    ObjVisitor<Integer> v = new HeightVisitor();
                    int height = expression.accept(v);
                    System.out.println(height);
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-e":
            case "--train":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    System.out.print("AST: \t\t");
                    expression.accept(new PrintVisitor());
                    System.out.println();
                    
                    Exp duplicate = expression.accept(new DuplicateVisitor());
                    System.out.print("Duplicated AST: \t");
                    duplicate.accept(new PrintVisitor());
                    System.out.println();
                    
                    Exp duplicate2 = expression.accept(new DuplicateVisitor2());
                    System.out.print("Duplicated2 AST: ");
                    duplicate2.accept(new PrintVisitor());
                    System.out.println();
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-s":
            case "--string": //Test classe ToStringVisitor
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    System.out.print("AST: \t\t");
                    System.out.println(expression.accept(new ToStringVisitor()));
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-t":
            case "--type":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);

                    System.out.println("------ Type equations ----");
                    Environnement predef = new Environnement();
                    ArrayList<Equation> eqArray = new ArrayList();
                    expression.accept(new TypeCheckVisitor(), predef, new TUnit(), eqArray);
                    for (int i = 0; i<eqArray.size(); i++) {
                        System.out.println(eqArray.get(i).toString());
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-k":
            case "--knorm":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    System.out.println("AST:");
                    expression.accept(new PrintVisitor());
                    System.out.println();
                    
                    Exp k = expression.accept(new KNormVisitor());
                    System.out.println("KNorm AST: ");
                    k.accept(new PrintVisitor());
                    System.out.println();
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-a":
            case "--alpha":
                System.out.println("\033[33mNot Yet Implemented\033[0m");
                break;
            case "-r":
            case "--reduc":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    System.out.println("AST:");
                    expression.accept(new PrintVisitor());
                    System.out.println();
                    
                    //Exp k = expression.accept(new KNormVisitor());
                    Exp r = expression.accept(new NestedLetVisitor());
                    System.out.println("NestedLetReduced AST: ");
                    r.accept(new PrintVisitor());
                    System.out.println();
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-c":
            case "--close":
                System.out.println("\033[33mNot Yet Implemented\033[0m");
                break;
            case "-g":
            case "--gen":
                System.out.println("\033[33mNot Yet Implemented\033[0m");
                break;
            case "-o":
            case "--opti":
                System.out.println("\033[33mNot Yet Implemented\033[0m");
                break;
            default:
                System.out.println(help);
                System.err.println("Unknown Option "+argv[0]);
                ;

        }
    }
}


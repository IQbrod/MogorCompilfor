

import BackEnd.ARMConverterVisitor;
import Parser.ASTMincaml.Exp;
import Entrainement.*;
import FrontEnd.Generation.II_AlphaConversion.AlphaConversionVisitor;
import FrontEnd.Generation.III_ReductionNestedLet.NestedLetVisitor;
import FrontEnd.Generation.II_AlphaConversion.Ids;
import FrontEnd.Generation.IV_ClosureConversion.ClosureConversionVisitor;
import FrontEnd.Generation.I_KNormalisation.KNormVisitor;
import FrontEnd.Generation.VI_ASMLGeneration.*;
import FrontEnd.Generation.VI_ASMLGeneration.ASTAsml.*;
import FrontEnd.TypeCheck.*;
import Parser.*;
import Parser.Type.*;
import java.io.*;
import java.util.ArrayList;

public class Main {
    static String option = "";
    static String fileName = "";
    static String outputFile = "";
    final static String help = "\n"
        + "╔══════════════════════════════════════════════════════════╗\n"
        + "║   USAGE:  ./mincaml [options] [fileName.ml] (-o [file])  ║\n"
        + "║   USAGE:  ./mincaml (-o [file]) [options] [fileName.ml]  ║\n"
        + "╟──────┬──────────────┬───────────────────┬────────────────╢\n"
        + "║      │  --parse, -p │ Parse l'AST       │ default        ║\n"
        + "║      │  --train, -e │ Copie l'AST       │ Entrainement   ║\n"
        + "║  O   │  --type,  -t │ Vérifie le typage │ Type-Check     ║\n"
        + "║  P   │  --knorm, -k │ AST après KNorm   │ KNormalisation ║\n"
        + "║  T   │  --alpha, -a │ AST après AConv   │ AlphaConversion║\n"
        + "║  I   │  --reduc, -r │ AST après RNesLet │ RNestedLet     ║\n"
        + "║  O   │  --close, -c │ AST après CConver │ ClosureConv    ║\n"
        + "║  N   │  --output,-o │ Choix Fichier Out │ Fichier Sortie ║\n"
        + "║  S   │  --gen,   -g │ Generation ASML   │ ASMLGeneration ║\n"
        + "║      │  --help,  -h │ Display Help      │ Help           ║\n"
        + "║      │  --stg,   -s │ Display String AST│ StringVisitor  ║\n"
        + "║      │         -arm │ Mincaml to ARM    │ ARM Generation ║\n"
        + "╟──────┴──────────────┴───────────────────┴────────────────╢\n"
        + "║               fileName.ml :  File to Parse               ║\n"
        + "║               file        :  Output File                 ║\n"
        + "╚══════════════════════════════════════════════════════════╝\n";
    
    static public void main(String argv[]) {    
        /* Calcule du répertoire MINCAML */
        String rep = System.getProperty("user.dir");
        String mincaml = null;
        if (rep.contains("MogorCompilfor")) {
            rep = rep.substring(0,rep.indexOf("MogorCompilfor"));
            mincaml = rep + "MogorCompilfor/mincaml/";
        }
        
        /* Traitement des arguments */
        if (argv.length == 0) { // Aucun argument
            option = "-h";
        } 
        
        else if (argv.length == 1) { // 1 seul argument
            
            if (argv[0].contains(".ml")) { // User a passé l'argument fileName.ml
                System.out.println("\033[33mMissing Argument Option (-h for help)\033[0m");
                System.out.println("\033[33mUsing -arm by default\033[0m");
                option = "-arm";
                fileName = argv[0];
            } 
            
            else if (argv[0].charAt(0) == '-') { // User a passé l'argument option
                option = argv[0];
                if (! (option.equals("-h") || option.equals("--help") || option.equals("-o") || option.equals("--output")) ) {
                    System.out.println("\033[33mMissing Argument FileName (-h for help)\033[0m");
                    java.util.Scanner s = new java.util.Scanner(System.in);
                    System.out.print("Select file to parse (.ml): ");
                    fileName = s.nextLine();
                    s.close();
                }
            } 
            
            else { // Argument invalide
                option = argv[0];
            }
        } 
        
        else { 
            if (argv[0].contains(".ml")) { // Argument1 = fileName.ml
                if (argv.length == 3) {
                    System.out.println("\033[33mMissing Argument Option\033[0m");
                    System.out.println("\033[33mUsing -arm by default\033[0m");
                    option = "-arm";
                    fileName = argv[0];
                    if (argv[1].equals("-o") || argv[1].equals("--output")) {
                        outputFile = argv[2];
                    } else {
                        option = "--args";
                    }
                }
                
                else if (argv.length == 2) {
                    if (argv[1].contains("-")) {
                        fileName = argv[0];
                        option = argv[1];
                    } else {
                        System.err.println("Too few arguments (-h for help)");
                        option = "--args";
                    }
                } else {
                    System.err.println("Too many arguments (-h for help)");
                    option = "--args";
                }
            } 
            
            else if (argv[0].charAt(0) == '-') { // Argument1 = option
                option = argv[0];
                if (option.equals("-h") || option.equals("--help")) {
                    /* Nothing to do here */
                } else if (option.equals("-o") || option.equals("--output")) {
                    outputFile = argv[1];
                    if (argv.length == 2) {
                        System.err.println("Too few arguments (-h for help)");
                        option = "--args";
                    } else if (argv.length == 3) {
                        if (argv[2].contains(".ml")) { // User a passé l'argument fileName.ml
                            System.out.println("\033[33mMissing Argument Option (-h for help)\033[0m");
                            System.out.println("\033[33mUsing -arm by default\033[0m");
                            option = "-arm";
                            fileName = argv[2];
                        } else {
                            option = argv[2]+".";
                        }
                    } else if (argv.length == 4) {
                        if (argv[2].charAt(0) == '-') { // User a passé l'argument option
                            option = argv[2];
                            if(argv[3].contains(".ml")) { // User a passé l'argument fileName.ml
                                fileName = argv[3];
                            } else {
                                System.err.println("Not a mincaml file "+argv[3]);
                                option = "--args";
                            }
                        } else {
                            option = argv[2]+".";
                        }
                    } else {
                        System.err.println("Too many arguments (-h for help)");
                        option = "--args";
                    }
                } else {
                    if(argv[1].contains(".ml")) { // User a passé l'argument fileName.ml
                        fileName = argv[1];
                    } else {
                        System.err.println("Not a mincaml file "+argv[1]);
                        option = "--args";
                    }
                    /* Si il y a des arguments suivants il faut "-o file" */
                    if(argv.length == 3 || argv.length > 4) {
                        System.err.println("Too many arguments (-h for help)");
                        option = "--args";
                    }
                    if(argv.length == 4) {
                        if (argv[2].equals("-o") || argv[2].equals("--output")) {
                            outputFile = argv[3];
                        } else {
                            System.err.println("Invalid option "+argv[2]);
                            option = "--args";
                        }
                    }
                    
                }
            }
            
            else { // Argument1 invalide
                option = argv[0];
            }
        }
        
        /* TRAITEMENT FILENAME */
        File fi = new File(fileName);
        if(! fi.exists() && mincaml != null) { 
            fileName = mincaml + fileName;
        }
              
        /* Traitements des options */
        switch(option) {
            case "-h":
            case "--help":
                System.out.println(help);
                break;
            case "-p":
            case "--parse":
                if (! outputFile.equals("")) { // User a utilisé -o
                    System.out.println("\033[33mOption -o ignorée\033[0m");
                }
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    System.out.print("AST: ");
                    expression.accept(new PrintVisitor(null));
                    System.out.println();
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-e":
            case "--train":
                if (! outputFile.equals("")) { // User a utilisé -o
                    System.out.println("\033[33mOption -o ignorée\033[0m");
                }
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    System.out.print("AST: \t\t");
                    expression.accept(new PrintVisitor(null));
                    System.out.println();
                    
                    Exp duplicate = expression.accept(new DuplicateVisitor());
                    System.out.print("Duplicated AST: \t");
                    duplicate.accept(new PrintVisitor(null));
                    System.out.println();
                    
                    Exp duplicate2 = expression.accept(new DuplicateVisitor2());
                    System.out.print("Duplicated2 AST: ");
                    duplicate2.accept(new PrintVisitor(null));
                    System.out.println();
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-s":
            case "--string": //Test classe ToStringVisitor
                if (! outputFile.equals("")) { // User a utilisé -o
                    System.out.println("\033[33mOption -o ignorée\033[0m");
                }
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
                if (! outputFile.equals("")) { // User a utilisé -o
                    System.out.println("\033[33mOption -o ignorée\033[0m");
                }
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    Environnement predef = new Environnement();
                    ArrayList<Equation> eqArray = new ArrayList();
                    expression.accept(new TypeCheckVisitor(), predef, new TUnit(), eqArray);
                    Solver s = new Solver(eqArray);
                    s.solve();
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
                    Exp k = expression.accept(new KNormVisitor());
                    
                    if (outputFile.equals("")) { // User n'a pas utilisé -o
                        System.out.println("AST:");
                        expression.accept(new PrintVisitor(null));
                        System.out.println();

                        System.out.println("KNorm AST: ");
                        k.accept(new PrintVisitor(null));
                        System.out.println();
                    } else {
                        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile)), true));
                        k.accept(new PrintVisitor(null));
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-a":
            case "--alpha":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    Exp k = expression.accept(new KNormVisitor());
                    Exp a = k.accept(new AlphaConversionVisitor(), new ArrayList<>());
                    
                    if (outputFile.equals("")) { // User n'a pas utilisé -o
                        System.out.println("AST:");
                        expression.accept(new PrintVisitor(null));
                        System.out.println();

                        System.out.println("AlphaConversion AST: ");
                        a.accept(new PrintVisitor(null));
                        System.out.println();
                    } else {
                        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile)), true));
                        a.accept(new PrintVisitor(null));
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-r":
            case "--reduc":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    Exp k = expression.accept(new KNormVisitor());
                    Exp a = k.accept(new AlphaConversionVisitor(), new ArrayList<>());
                    Exp r = a.accept(new NestedLetVisitor());
                    
                    if (outputFile.equals("")) { // User n'a pas utilisé -o
                        System.out.println("AST:");
                        expression.accept(new PrintVisitor(null));
                        System.out.println();

                        System.out.println("NestedLet AST: ");
                        r.accept(new PrintVisitor(null));
                        System.out.println();
                    } else {
                        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile)), true));
                        r.accept(new PrintVisitor(null));
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-c":
            case "--close":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    Exp k = expression.accept(new KNormVisitor());

                    Exp a = k.accept(new AlphaConversionVisitor(), new ArrayList<>());
                    Exp r = a.accept(new NestedLetVisitor());
                    ClosureConversionVisitor ccv = new ClosureConversionVisitor(r);
                    Exp c = r.accept(ccv);
                    if (outputFile.equals("")) { // User n'a pas utilisé -o
                        System.out.println("AST:");
                        expression.accept(new PrintVisitor(null));
                        System.out.println();

                        System.out.println("ClosureConversion AST: ");
                        ccv.main.accept(new PrintVisitor(ccv.functions));
                        System.out.println();
                    } else {
                        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile)), true));
                        ccv.main.accept(new PrintVisitor(ccv.functions));
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-g":
            case "-asml":
            case "--gen":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    Exp k = expression.accept(new KNormVisitor());
                    Exp a = k.accept(new AlphaConversionVisitor(), new ArrayList<>());
                    Exp r = a.accept(new NestedLetVisitor());
                    Exp c = r.accept(new ClosureConversionVisitor(r));
                    ASMLNode aexp = c.accept(new ASMLConverterVisitor());
                    Afunmain main = new Afunmain((ASMLexp)aexp);
                    
                    if (outputFile.equals("")) { // User n'a pas utilisé -o
                        System.out.println("AST:");
                        expression.accept(new PrintVisitor(null));
                        System.out.println();

                        System.out.println("ASML: ");
                        main.accept(new ASMLPrintVisitor());
                        System.out.println();
                    } else {
                        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile)), true));
                        main.accept(new ASMLPrintVisitor());
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "-o":
            case "--output":
                System.out.println("\033[33mOption -o: Missing Arguments File & FileName.ml\033[0m");
                break;
            case "--args":
                /* Nothing to do here */
                break;
            case "-arm":
                try {
                    Parser p = new Parser(new Lexer(new FileReader(fileName)));
                    Exp expression = (Exp) p.parse().value;
                    assert (expression != null);
                    
                    Exp k = expression.accept(new KNormVisitor());
                    Exp a = k.accept(new AlphaConversionVisitor(), new ArrayList<>());
                    Exp r = a.accept(new NestedLetVisitor());
                    Exp c = r.accept(new ClosureConversionVisitor(r));
                    ASMLNode aexp = c.accept(new ASMLConverterVisitor());
                    Afunmain main = new Afunmain((ASMLexp)aexp);
                    
                    if (outputFile.equals("")) { // User n'a pas utilisé -o
                        System.out.println("AST:");
                        expression.accept(new PrintVisitor(null));
                        System.out.println();
                        
                        System.out.println("ASML:");
                        main.accept(new ASMLPrintVisitor());
                        System.out.println();
                        
                        System.out.println("ARM: ");
                        main.accept(new ARMConverterVisitor());
                        System.out.println();
                    } else {
                        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(outputFile)), true));
                        main.accept(new ARMConverterVisitor());
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.err.println("Unknown Option "+option);
                System.out.println(help);
        }
    }
}


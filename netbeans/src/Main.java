

import Parser.*;
import Parser.*;
import Parser.AST.*;
import Parser.Type.*;
import java.io.*;

public class Main {
    static String option = "";
    static String fileName = "";
    final static String help = "\n"
        + "╔══════════════════════════════════════════════════════════╗\n"
        + "║         USAGE: ./mincaml [options] [fileName.ml]         ║\n"
        + "╟──────┬──────────────┬───────────────────┬────────────────╢\n"
        + "║      │  --parse, -p │ Parse l'AST       │ default        ║\n"
        + "║  O   │  --train, -e │ Copie l'AST       │ Entrainement   ║\n"
        + "║  P   │  --type,  -t │ Vérifie le typage │ Type-Check     ║\n"
        + "║  T   │  --knorm, -k │ AST après KNorm   │ KNormalisation ║\n"
        + "║  I   │  --alpha, -a │ AST après AConv   │ AlphaConversion║\n"
        + "║  O   │  --reduc, -r │ AST après RNesLet │ RNestedLet     ║\n"
        + "║  N   │  --close, -c │ AST après CConver │ ClosureConv    ║\n"
        + "║      │  --gen,   -g │ Generation ASML   │ ASMLGeneration ║\n"
        + "║      │  --opti,  -o │ ASML après Opti   │ Optimisation   ║\n"
        + "║      │  --help,  -h │ Display Help      │ Help           ║\n"
        + "╟──────┴──────────────┴───────────────────┴────────────────╢\n"
        + "║        fileName.ml :  file in \"mincaml\" directory        ║\n"    
        + "╚══════════════════════════════════════════════════════════╝";
    
    static public void main(String argv[]) {    
        /* Calcule du répertoire MINCAML */
        String rep = System.getProperty("user.dir");
        rep = rep.substring(0,rep.indexOf("netbeans"));
        String mincaml = rep + "mincaml/";
        /* Traitement des arguments */
        if (argv.length == 0) { // Aucun argument
            System.out.println(help);
            System.out.println("\033[33mUsing default option: --parse\033[0m");
            option = "-p";
            /* On récupère le fichier .ml à parser */
            java.util.Scanner s = new java.util.Scanner(System.in);
            System.out.print("Select file to parse (.ml): ");
            fileName = mincaml + s.nextLine();
            s.close();
        } else if (argv.length == 1) { // 1 seul argument (fichier.ml)
            System.out.println("\033[33mUsing default option: --parse\033[0m");
            option = "-p";
            fileName = mincaml + argv[0];
        } else { // Sinon on utilise les paramètres
            option = argv[0];
            fileName = argv[1];
        }
        try {
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


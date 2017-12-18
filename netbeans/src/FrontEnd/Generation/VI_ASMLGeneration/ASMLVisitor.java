package FrontEnd.Generation.VI_ASMLGeneration;

import FrontEnd.Generation.VI_ASMLGeneration.ASTAsml.*;

public interface ASMLVisitor {
    /* Final nodes */
    void visit(Anop e);
    void visit(Aint e);
    void visit(Afloat e);
    void visit(Aident e);
    void visit(Alabel e);
    
    /* Arithmetic Op */
    void visit(Aadd e);
    void visit(Aneg e);
    void visit(Asub e);
    void visit(Afadd e);
    void visit(Afdiv e);
    void visit(Afmul e);
    void visit(Afneg e);
    void visit(Afsub e);
    
    /* Boolean Op */
    void visit(Aeq e);
    void visit(Afeq e);
    void visit(Afle e);
    void visit(Age e);
    void visit(Ale e);
    void visit(Anot e);
    
    /* Functions */
    void visit(Acall e);
    void visit(Acallclo e);
    void visit(Afunflt e);
    void visit(Afunlabel e);
    void visit(Afunmain e);
    
    /* Commands */
    void visit(Aparen e);
    void visit(Aif e);
    void visit(Alet e);
    void visit(Amem e);
    void visit(Anew e);
}



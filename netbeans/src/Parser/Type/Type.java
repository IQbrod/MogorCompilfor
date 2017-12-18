package Parser.Type;

public abstract class Type {
    private static int x = 0;
    public static Type gen() {
        return new TVar("var" + x++);
    }
    
}

package Parser;

public class Id {
    String id;
    Id(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return id;
    }
    static int x = -1;
    public static Id gen() {
        x++;
        return new Id("?v" + x);
    }

    static int x2 = 0;
    public static Id gen2() {
        x2++;
        return new Id("x" + x2);
    }
}

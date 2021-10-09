package calculation4.level1;

public class Token {
    public final String token;
    public final int s, e;

    public Token(String token, int s, int e) {
        this.token = token;
        this.s = s;
        this.e = e;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"("+token+", ["+s+","+e+"])";
    }
}

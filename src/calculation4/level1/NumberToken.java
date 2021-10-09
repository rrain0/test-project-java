package calculation4.level1;

public class NumberToken extends Token {
    public int radix;
    public String number;

    public NumberToken(String token, int s, int e, String number, int radix) {
        super(token, s, e);
        this.radix = radix;
        this.number = number;
    }
}

package calculation;

public class Num extends Elem {
    public final Number val;

    public Num(int s, int e, String val) {
        super(s, e);
        this.val = new Number(Double.parseDouble(val));
    }
    public Num(String val) {
        this.val = new Number(Double.parseDouble(val));
    }
    public Num(Number val) {
        this.val = val;
    }


    @Override public boolean isNullElem(){ return false; }
    @Override public boolean isUndefined(){ return false; }
    @Override public boolean isFunction(){ return false; }
    @Override public boolean isNumber(){ return true; }
    @Override public boolean isOpenBracket(){ return false; }
    @Override public boolean isCloseBracket(){ return false; }


    @Override
    public String toString() {
        return "Num{" +
                "s=" + s +
                ", e=" + e +
                ", " + val +
                '}';
    }
}

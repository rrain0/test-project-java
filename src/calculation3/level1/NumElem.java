package calculation3.level1;


public class NumElem extends Elem {
    public final String val;

    public NumElem(int s, int e, String val) {
        super(s, e);
        this.val = val;
    }
    public NumElem(String val) {
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

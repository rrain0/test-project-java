package calculation3.level1;


public class FunElem extends Elem {
    public final String f;

    public FunElem(int s, int e, String f) {
        super(s, e);
        this.f = f;
    }


    @Override public boolean isNullElem(){ return false; }
    @Override public boolean isUndefined(){ return false; }
    @Override public boolean isFunction(){ return true; }
    @Override public boolean isNumber(){ return false; }
    @Override public boolean isOpenBracket(){ return false; }
    @Override public boolean isCloseBracket(){ return false; }

    @Override
    public String toString() {
        return "Fun{" +
                "s=" + s +
                ", e=" + e +
                ", " + f +
                '}';
    }
}

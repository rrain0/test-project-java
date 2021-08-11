package calculation;

public class Func extends Elem{
    public final Function f;

    public Func(int s, int e, Function f) {
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
        return "Func{" +
                "s=" + s +
                ", e=" + e +
                ", " + f +
                '}';
    }
}

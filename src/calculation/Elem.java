package calculation;

public class Elem {
    public int s; //start index inclusive
    public int e; //end index exclusive

    public Elem(){}

    public Elem(int s) {
        this.s = s;
    }

    public Elem(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public boolean isNullElem(){ return s==e; }
    public boolean isUndefined(){ return !isNullElem(); }
    public boolean isFunction(){ return false; }
    public boolean isNumber(){ return false; }
    public boolean isOpenBracket(){ return false; }
    public boolean isCloseBracket(){ return false; }

    @Override
    public String toString() {
        return "Elem{" +
                "s=" + s +
                ", e=" + e +
                '}';
    }
}

package calculation3.level1;


public class BracketElem extends Elem {
    public boolean isOpen;

    public BracketElem(int s, int e, boolean isOpen) {
        super(s, e);
        this.isOpen = isOpen;
    }

    @Override public boolean isNullElem(){ return false; }
    @Override public boolean isUndefined(){ return false; }
    @Override public boolean isFunction(){ return false; }
    @Override public boolean isNumber(){ return false; }
    @Override public boolean isOpenBracket(){ return isOpen; }
    @Override public boolean isCloseBracket(){ return !isOpen; }

    @Override
    public String toString() {
        return "Bracket{" +
                "s=" + s +
                ", e=" + e +
                ", isOpen=" + isOpen +
                '}';
    }
}

package calculation;

public class Number {
    public final double val;

    public Number(double val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Number{" +
                "val=" + val +
                '}';
    }


    public Number negate(){ return new Number(-val); }
    public Number add(Number n){ return new Number(val+n.val); }
    public Number sub(Number n){ return new Number(val-n.val); }
    public Number mult(Number n){ return new Number(val*n.val); }
    public Number div(Number n){ return new Number(val/n.val); }
    public Number pow(Number n){ return new Number(Math.pow(val,n.val)); }
    public static Number lg(Number n){ return new Number(Math.log10(n.val)); }
    public static Number sh(Number n){ return new Number(Math.sinh(n.val)); }
    public static Number arcsin(Number n){ return new Number(Math.asin(n.val)); }
    public static Number sin(Number n){ return new Number(Math.sin(n.val)); }
    public static Number factorial(Number n){
        //return new Number(Math.sin(n.val));
        return null;
    }
}

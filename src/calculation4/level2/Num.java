package calculation4.level2;


import calculation4.level1.Token;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Num extends Node {
    public final double v;

    public Num(double v) {
        super(null);
        this.v = v;
    }

    public Num(double v, Token token) {
        super(token);
        this.v = v;
    }

    public Num(Num v, Token token) {
        super(token);
        this.v = v.v;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName()+"("+v+", up#"+(up==null?null:up.number)+")";
    }

    public static Num pi = new Num(Math.PI);
    public static Num e = new Num(Math.E);
    public static Num inf = new Num(Double.POSITIVE_INFINITY);
    public static Num NaN = new Num(Double.NaN);
    public static Num zero = new Num(0.0);
    public static Num one = new Num(1.0);


    public static Num minus(Num a, Num b){ return new Num(a.v-b.v); }
    public static Num negate(Num a) { return new Num(-a.v); }
    public static Num plus(Num a, Num b){ return new Num(a.v+b.v); }
    public static Num mult(Num a, Num b){ return new Num(a.v*b.v); }
    public static Num div(Num a, Num b){ return new Num(a.v/b.v); }
    public static Num down(Num a){ return new Num(1/a.v); }
    public static Num pow(Num a, Num b){ return new Num(Math.pow(a.v,b.v)); }

    public static Num factor(Num a) {
        try{
            //если было 2.0, то возвратит 2; но если было 2.1, то будет исключение
            int f = new BigDecimal(a.v).intValueExact();
            if (f<0) return NaN;
            if (f==0) return one;
            BigInteger factor = BigInteger.ONE;
            for (int i = 2; i <= f; i++) {
                factor = factor.multiply(BigInteger.valueOf(i));
            }
            return new Num(factor.doubleValue());
        } catch (Exception e){ return NaN; }
    }

    public static Num lg(Num a){return new Num(Math.log10(a.v));}
}

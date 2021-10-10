package calculation4;

import calculation4.level1.Token;
import calculation4.level2.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static calculation4.level2.Fun.*;


// todo дробь - ответ - часть пи или е

public final class TokensToObjects {
    private TokensToObjects() {}


    public static F pass = args -> args[0];
    public static F minus = args -> Num.minus(args[0],args[1]);
    public static F negate = args -> Num.negate(args[0]);
    public static F plus = args -> Num.plus(args[0],args[1]);
    public static F mult = args -> Num.mult(args[0],args[1]);
    public static F div = args -> Num.div(args[0],args[1]);
    public static F down = args -> Num.down(args[0]);
    public static F pow = args -> Num.pow(args[0],args[1]);
    public static F factor = args -> Num.factor(args[0]);
    public static F lg = args -> Num.lg(args[0]);


    //private static final Generate gImplicitMult = (t)->new Fun(1,mult,1,IMPLICIT_MULT,"implicit mult", t);
    public static Node getImplicitMult(Token t, int priority){
        return new Fun(1,mult,1,priority,"implicit mult", t);
    }

    //private static final Generate gUnaryMinus = (t)->new Fun(0,negate,1,IMPLICIT_MULT,"implicit mult", t);

    // у унарного минуса/плюса приоритет как у левого оператора, но не ниже чем ADD
    public static Node getUnaryMinus(Token t, int leftOperatorPriority){
        return new Fun(0,negate,1,Integer.max(ADD,leftOperatorPriority),"unary minus", t);
    }
    public static Node getUnaryPlus(Token t, int leftOperatorPriority){
        return new Fun(0,pass,1,Integer.max(ADD,leftOperatorPriority),"unary plus", t);
    }
    public static Node getUnaryDiv(Token t, int leftOperatorPriority){
        return new Fun(0,down,1,Integer.max(MULT,leftOperatorPriority),"unary div", t);
    }


    public static final Map<String,Generate> staticTokensMap;
    private static void add(String staticToken, Generate o){ staticTokensMap.put(staticToken, o); }
    public static Set<String> staticTokens(){ return staticTokensMap.keySet(); }
    public static Node getNode(Token t){ return staticTokensMap.get(t.token).generate(t); }
    static {
        staticTokensMap = new HashMap<>();
        Generate g = null;

        g = (t)->new Num(Num.pi,t);
        add("π",g);add("pi",g);add("PI",g);

        add("e", (t)->new Num(Num.e,t));

        g = (t)->new Num(Num.inf,t);
        add("∞",g);add("inf",g);add("infinity",g);add("Infinity",g);

        g = (t)->new Num(Num.NaN,t);
        add("NaN",g);add("nan",g);

        g = (t)->new Fun(0,pass,1,OPEN_BRACKET,"open",t);
        add("(",g);add("[",g);

        g = (t)->new Fun(1,pass,0,CLOSE_BRACKET,"close",t);
        add(")",g);add("]",g);

        //add("::");

        g = (t)->new Fun(1,plus,1,ADD,"plus", t);
        add("+",g);

        g = (t)->new Fun(1,minus,1,ADD,"minus", t);
        add("-",g);add("−",g); // длинный минус (тире)

        g = (t)->new Fun(1,mult,1,MULT, "mult", t);
        add("*",g);add("×",g);

        g = (t)->new Fun(1,div,1,MULT,"div", t);
        add("/",g);add("÷",g);

        g = (t)->new Fun(1,pow,1,POW,"pow", t);
        add("^",g);

        g = (t)->new Fun(1,factor,0,FACTOR,"factor", t);
        add("!",g);

        //add("!!");
        //add("°");
        //add("%");

        //add("²"); // ^2
        //add("³"); // ^3
        //add("⁴"); // ^4
        //add("√");add("sqrt"); //√²
        //add("∛");add("cbrt"); //√³
        //add("∜");add("qdrt"); //√⁴

        //add("sin");
        //add("arcsin");add("asin");

        //add("cos");
        //add("arccos");add("acos");

        //add("tg");add("tan");
        //add("arctg");add("arctan");add("atg");add("atan");

        //add("ctg");add("cot");
        //add("arcctg");add("actg");add("arccot");add("acot");

        //add("sh");add("sinh");
        //add("arsh");add("arsinh");add("ash");add("asinh");

        //add("ch");add("cosh");
        //add("arch");add("arcosh");add("ach");add("acosh");

        //add("th");add("tanh");
        //add("arth");add("artanh");add("ath");add("atanh");

        //add("cth");add("coth");
        //add("arcth");add("arcoth");add("acth");add("acoth");

        //add("ln");

        g = (t)->new Fun(0,lg,1,FUNC,"lg", t);
        add("lg",g);

        //add("lb");
        //add("log");

        //add("abs");
        //add("inv");
        //add("sgn");
    }


}

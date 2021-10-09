package calculation4;

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
    public static F plus = args -> Num.plus(args[0],args[1]);
    public static F mult = args -> Num.mult(args[0],args[1]);
    public static F div = args -> Num.div(args[0],args[1]);
    public static F pow = args -> Num.pow(args[0],args[1]);
    public static F factor = args -> Num.factor(args[0]);
    public static F lg = args -> Num.lg(args[0]);


    public static final Map<String,Generate> staticTokensMap;
    private static void add(String staticToken, Generate o){ staticTokensMap.put(staticToken, o); }
    public static Set<String> staticTokens(){ return staticTokensMap.keySet(); }
    public static Node getNode(String key){ return staticTokensMap.get(key).generate(); }
    static {
        staticTokensMap = new HashMap<>();
        Generate g = null;

        g = ()->Num.pi;
        add("π",g);add("pi",g);add("PI",g);

        add("e", ()->Num.e);

        g = ()->Num.inf;
        add("∞",g);add("inf",g);add("infinity",g);add("Infinity",g);

        g = ()->Num.NaN;
        add("NaN",g);add("nan",g);

        g = ()->new Fun(0,pass,1,BRACKET);
        add("(",g);add("[",g);

        g = ()->new Fun(0,pass,0,BRACKET);
        add(")",g);add("]",g);

        //add("::");

        g = ()->new Fun(1,plus,1,ADD);
        add("+",g);

        g = ()->new Fun(1,minus,1,ADD);
        add("-",g);add("−",g); // длинный минус (тире)

        g = ()->new Fun(1,mult,1,MULT);
        add("*",g);add("×",g);

        g = ()->new Fun(1,div,1,MULT);
        add("/",g);add("÷",g);

        g = ()->new Fun(1,pow,1,POW);
        add("^",g);

        g = ()->new Fun(1,factor,1,FACTOR);
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

        g = ()->new Fun(0,lg,1,FUNC);
        add("lg",g);

        //add("lb");
        //add("log");

        //add("abs");
        //add("inv");
        //add("sgn");
    }


}

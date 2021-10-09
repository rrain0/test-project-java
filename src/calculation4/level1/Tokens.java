package calculation4.level1;

import java.util.HashSet;
import java.util.Set;

public final class Tokens {
    private Tokens(){}





    public static final Set<String> staticTokens;
    private static void add(String staticToken){ staticTokens.add(staticToken); }
    static {
        staticTokens = new HashSet<>();
        add("π");add("pi");add("PI");
        add("e");
        add("∞");add("inf");add("infinity");add("Infinity");
        add("NaN");add("nan");

        add("(");add("[");
        add(")");add("]");
        add("::");

        add("+");
        add("-");add("−"); // длинный минус (тире)
        add("*");add("×");
        add("/");add("÷");

        add("^");
        add("!");
        add("!!");
        add("°");
        add("%");

        add("²"); // ^2
        add("³"); // ^3
        add("⁴"); // ^4
        add("√");add("sqrt"); //√²
        add("∛");add("cbrt"); //√³
        add("∜");add("qdrt"); //√⁴

        add("sin");
        add("arcsin");add("asin");

        add("cos");
        add("arccos");add("acos");

        add("tg");add("tan");
        add("arctg");add("arctan");add("atg");add("atan");

        add("ctg");add("cot");
        add("arcctg");add("actg");add("arccot");add("acot");

        add("sh");add("sinh");
        add("arsh");add("arsinh");add("ash");add("asinh");

        add("ch");add("cosh");
        add("arch");add("arcosh");add("ach");add("acosh");

        add("th");add("tanh");
        add("arth");add("artanh");add("ath");add("atanh");

        add("cth");add("coth");
        add("arcth");add("arcoth");add("acth");add("acoth");

        add("ln");
        add("lg");
        add("lb");
        add("log");

        add("abs");
        add("inv");
        add("sgn");
    }

    public static final Set<Integer> plusMinus;
    static {
        plusMinus = new HashSet<>();
        plusMinus.add((int)'+');
        plusMinus.add((int)'-');
        plusMinus.add((int)'−');
    }

    public final static Set<Integer> empties;
    static {
        empties = new HashSet<>();
        empties.add((int)' ');
        empties.add((int)'\n');
    }

    public final static Set<Integer> digitSeparators;
    static {
        digitSeparators = new HashSet<>();
        digitSeparators.add((int)' ');
        digitSeparators.add((int)'_');
        digitSeparators.add((int)'\'');
    }



}

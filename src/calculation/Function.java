package calculation;


import java.util.HashMap;
import java.util.Objects;

import static calculation.Number.*;


/*
priority:
0 +-
1 /*
2 ^
3 func
 */


public class Function {
    public static final HashMap<String, Function> funcs;


    public static final int maxPriority = 7;

    //умножение, когда между числами явно не ставится знак умножить
    //парсер не распознаёт эту функцию
    public static final int IMPLICIT_MULT = 7;

    public static final int POST_FUNC = 6;

    //отрицание числа - унарный минус (сюда же плюс, но его можно просто убрать)
    //+n -> n
    //-n -> [-n]
    //парсер не распознаёт эту функцию
    public static final int NEGATE = 5;

    //удаление скобок вокруг одиночного числа
    //(5) -> 5
    //парсер не распознаёт эту функцию
    public static final int BRACKET_REMOVING = 4;
    public static final int FUNC = 3;
    public static final int POWER = 2;
    public static final int MULT = 1;
    public static final int ADD = 0;

    static {
        funcs = new HashMap<>();
        funcs.put("!", new Function("!", POST_FUNC, 1, 0, n->factorial(n[0])));

        funcs.put("-negate", new Function("-negate", NEGATE, 0, 1, n->n[0].negate()));

        funcs.put("lg", new Function("lg", FUNC, 0, 1, n->lg(n[0])));
        funcs.put("sh", new Function("sh", FUNC, 0, 1, n->sh(n[0])));
        funcs.put("arcsin", new Function("arcsin", FUNC, 0, 1, n->arcsin(n[0])));
        funcs.put("sin", new Function("sin", FUNC, 0, 1, n->sin(n[0])));

        funcs.put("^", new Function("^", POWER, 1, 1, n->n[0].pow(n[1])));

        funcs.put("*", new Function("*", MULT, 1, 1, n->n[0].mult(n[1])));
        funcs.put("/", new Function("/", MULT, 1, 1, n->n[0].div(n[1])));

        funcs.put("+", new Function("+", ADD, 1, 1, n->n[0].add(n[1])));
        funcs.put("-", new Function("-", ADD, 1, 1, n->n[0].sub(n[1])));


    }

    public final String name;
    public final int priority;
    public final int preArgs;
    public final int postArgs;
    public final F func;

    public Function(/*@NonNull*/ String name, int priority, int preArgs, int postArgs, F func) {
        this.name = name;
        this.priority = priority;
        this.preArgs = preArgs;
        this.postArgs = postArgs;
        this.func = func;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return name.equals(function.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                //", priority=" + priority +
                //", preArgs=" + preArgs +
                //", postArgs=" + postArgs +
                //", func=" + func +
                '}';
    }

    @FunctionalInterface
    public interface F {
        Number make(Number... n); //apply
    }
}

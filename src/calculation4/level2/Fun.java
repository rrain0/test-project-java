package calculation4.level2;

import java.util.ArrayList;
import java.util.List;

public class Fun extends Node {
    public static final int ADD = 0;
    public static final int MULT = 1;
    public static final int POW = 2;
    public static final int FUNC = 3;
    public static final int IMPLICIT_MULT = 3;
    public static final int FACTOR = 4;
    public static final int BRACKET = 5;




    public final int preArgsCnt, postArgsCnt;
    public final int priority;

    public final F fun;

    public List<Object> args = new ArrayList<>();

    public Fun(int preArgsCnt, F fun, int postArgsCnt, int priority) {
        this.preArgsCnt = preArgsCnt;
        this.postArgsCnt = postArgsCnt;
        this.priority = priority;
        this.fun = fun;
    }

    public boolean canCalculate(){
        if (args.size()==preArgsCnt+postArgsCnt)
            for (int i = 0; i < args.size(); i++) {
                if (!(args.get(i) instanceof Num)) return false;
            }
        else return false;
        return true;
    }



    /*@Override
    public String toString() {
        return getClass().getSimpleName()+"("+v+")";
    }*/

}

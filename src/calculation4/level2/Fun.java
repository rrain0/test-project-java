package calculation4.level2;


import calculation4.level1.Token;


public class Fun extends Node {
    // возможно можно делать обход 2 индексами с операндом между ними +0*

    // приоритет играет роль только если 2 функции претендуют на операнд между ними
    public static final int OPEN_BRACKET = -2;
    public static final int CLOSE_BRACKET = -1;
    public static final int ADD = 0;
    public static final int MULT = 1;
    public static final int POW = 2;
    public static final int FUNC = 3;
    // неявное умножение будет вставлено между двумя Node,
    // если для левого не нужны правые аргументы и для правого не нужны левые аргументы
    public static final int IMPLICIT_MULT = 4;
    public static final int FACTOR = 5;


    private static int counter = 0;



    public final int number;
    public final int preArgsCnt, postArgsCnt;
    public final int priority;
    public final String name;

    public final F fun;

    public final Node[] args;

    // preargs [0,1]
    // postargs [0,n]
    public Fun(int preArgsCnt, F fun, int postArgsCnt, int priority, String name, Token token) {
        super(token);
        number = counter++;
        this.preArgsCnt = preArgsCnt;
        args = new Node[preArgsCnt+postArgsCnt];
        this.postArgsCnt = postArgsCnt;
        this.priority = priority;
        this.fun = fun;
        this.name = name;
    }

    /*public boolean canCalculate(){
        if (args.size()==preArgsCnt+postArgsCnt)
            for (int i = 0; i < args.size(); i++) {
                if (!(args.get(i) instanceof Num)) return false;
            }
        else return false;
        return true;
    }*/

    public void setArg(int idx, Node down){
        args[idx] = down;
        down.up = this;
        down.upIdx = idx;
    }

    public void becomeUpon(Node n, int nNewIdx){
        if (up==n) throw new RuntimeException("Левая функция ссылается на текущую (правую), над которой правая пытается стать выше");
        if (n.up!=null){
            n.up.replace(n,this);
        }
        this.setArg(nNewIdx,n);
    }

    public void replace(Node old, Node neW){
        setArg(old.upIdx, neW);
        old.up = null;
        old.upIdx = -1;
    }

    public void lift(int argNumber){
        if (up!=null){
            Node down = args[argNumber];
            up.setArg(upIdx, down);
            down = up;

            Fun up2 = up.up;
            if (up2!=null){
                up2.setArg(up.upIdx, this);
            } else {
                up = null;
                upIdx = -1;
            }

            setArg(argNumber, down);
        }
    }

    /*public boolean canCalculate(){
        for (int i = 0; i < args.length; i++) {
            if (!(args[i] instanceof Num)) return false;
        }
        return true;
    }*/

    public Num calculate(){
        Num[] nums = new Num[args.length];
        for (int i = 0; i < args.length; i++) nums[i] = (Num)args[i];
        return fun.make(nums);
    }


    @Override public boolean hasOperands(){ return preArgsCnt+postArgsCnt > 0; }
    @Override public boolean hasPreOperands(){ return preArgsCnt > 0; }
    @Override public boolean hasPostOperands(){ return postArgsCnt > 0; }

    @Override public boolean isMinus(){ return name.equals("minus"); }
    @Override public boolean isPlus(){ return name.equals("plus"); }
    @Override public boolean isDiv(){ return name.equals("div"); }

    @Override public boolean isOpenBracket(){ return priority==OPEN_BRACKET; }
    @Override public boolean isCloseBracket(){ return priority==CLOSE_BRACKET; }

    @Override
    public String toString() {
        return String.format(
            "%s#%s(%s<-%s->%s, up#%s, %s)",
            getClass().getSimpleName(), number, preArgsCnt, name, postArgsCnt, up==null ? null : up.number, token
        );
    }

}

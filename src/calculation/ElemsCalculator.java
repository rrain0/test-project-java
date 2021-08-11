package calculation;


import java.util.ArrayList;
import java.util.List;

public class ElemsCalculator {
    private List<Elem> elems;

    public ElemsCalculator(ExprParser exprParser){
        this.elems = new ArrayList<>(exprParser.getElems().getElems());
        this.elems.remove(0);
    }

    public Number calculate2(){
        //Queue<Func> funcQueue = new ArrayDeque<>();
        while (true){
            int sz = size();

            for (int i = 0; i < size(); ) {
                Function f;
                //System.out.println("fdjg "+i);
                if (isNum(i)
                        && isNum(i+1)){//неявное умножение
                    f = Function.funcs.get("*");
                    add(i, new Num(f.func.make(getNumber(remove(i)), getNumber(remove(i)))));
                } else if (isFunc(i) && getFunction(i).name.equals("+")
                        && (i-1<0 || !(get(i-1).isCloseBracket() || isNum(i-1) || isFuncLowerOrEqPrior(i-1, Function.ADD)))){
                    remove(i);
                    //i--;
                } else if (isFunc(i) && getFunction(i).name.equals("-")
                        && (i-1<0 || !(get(i-1).isCloseBracket() || isNum(i-1) || isFuncLowerOrEqPrior(i-1, Function.ADD)))
                        && isNum(i+1)
                        && (i+2>=size() || !(isNum(i+2) || get(i+2).isOpenBracket() || isFuncHigherPrior(i+2, Function.MULT)))){
                    f = Function.funcs.get("-negate");
                    remove(i);
                    add(i, new Num(f.func.make(getNumber(remove(i)))));
                    //i--;
                }


                i++;
            }

            if (size()==sz) break;
        }

        if (size()==1 && isNum(0)) return getNumber(0);
        return null;
    }

    private Elem get(int idx){ return elems.get(idx); }
    private void add(int idx, Elem elem){ elems.add(idx, elem); }
    private Elem remove(int idx){ return elems.remove(idx); }
    private int size(){ return elems.size(); }

    private Number getNumber(Elem e){ return ((Num)e).val; }
    private Function getFunction(Elem e){ return ((Func)e).f; }
    private boolean isFuncLowerOrEqPrior(Elem elem, int priority){ return elem.isFunction() && getFunction(elem).priority<=priority; }
    private boolean isFuncHigherPrior(Elem elem, int priority){ return elem.isFunction() && getFunction(elem).priority>priority; }

    private boolean isFunc(int idx){ return idx>=0 && idx<size() && get(idx).isFunction(); }
    private boolean isNum(int idx){ return idx>=0 && idx<size() && get(idx).isNumber(); }


    private Number getNumber(int idx){ return ((Num)get(idx)).val; }
    private Function getFunction(int idx){ return ((Func)get(idx)).f; }
    private boolean isFuncLowerOrEqPrior(int idx, int priority){ return get(idx).isFunction() && getFunction(idx).priority<=priority; }
    private boolean isFuncHigherPrior(int idx, int priority){ return get(idx).isFunction() && getFunction(idx).priority>priority; }

    private boolean checkLeftPriority(int idx, int priority){
        return idx<0
                || isNum(idx) && priority>=Function.IMPLICIT_MULT
                || get(idx).isCloseBracket() && priority>=Function.MULT
                || isFuncLowerOrEqPrior(idx, priority);
    }


    /*public Number calculate(){

        applyUnaryAdd();
        removeBracketsAroundNumber();

        while (true){
            int size = elems.size();

            for (int p = Function.maxPriority; p >=0 ; p--) {
                if (p==Function.IMPLICIT_MULT) {
                    Function mult = Function.funcs.get("*");
                    for (int i = 0; i < elems.size()-1; ) {
                        if (elems.get(i).isNumber() && elems.get(i+1).isNumber()){
                            Num n = (Num)elems.get(i);
                            Num n2 = (Num)elems.get(i+1);
                            n.val = new Number(mult.func.make(n.val.val, n2.val.val));
                            elems.remove(i+1);
                        } else i++;
                    }
                } else {
                    loop: for (int i = 0; i < elems.size(); i++) {
                        if (elems.get(i).isFunction()){
                            Func f = (Func)elems.get(i);
                            if (f.f.priority==p){
                                for (int j = i-f.f.preArgs; j <= i+f.f.postArgs; j++) {
                                    if (j==i) continue;
                                    if (j<0 || j>=elems.size() || !elems.get(j).isNumber()) continue loop;
                                }
                                double[] args = new double[f.f.preArgs+f.f.postArgs];
                                for (int j = i-f.f.preArgs, arg = 0; j <= i+f.f.postArgs; j++) {
                                    if (j==i) continue;
                                    args[arg++]=((Num)elems.get(j)).val.val;
                                }
                                Elem e = new Num(f.f.func.make(args));
                                i-=f.f.preArgs;
                                for (int j = f.f.preArgs+1+f.f.postArgs; j>0 ; j--) {
                                    elems.remove(i);
                                }
                                elems.add(i, e);
                            }
                        }
                    }
                }

                applyUnaryAdd();
                removeBracketsAroundNumber();
            }

            if (elems.size()==size) break;
        }

        if (elems.size()==1 && elems.get(0).isNumber()) return ((Num)elems.get(0)).val;
        return null;
    }*/


    /*private void applyUnaryAdd(){
        for (int i = 0; i < elems.size()-1; i++) {
            if ((i-1==-1 || elems.get(i-1).isOpenBracket() || (elems.get(i-1).isFunction() && ((Func)elems.get(i-1)).f.priority>Function.ADD))
                    && elems.get(i).isFunction()
                    && elems.get(i+1).isNumber()){
                if (((Func)elems.get(i)).f.name.equals("+")) elems.remove(i);
                else if (((Func)elems.get(i)).f.name.equals("-")) {
                    elems.remove(i);
                    *//*Number n = ((Num)elems.get(i)).val;
                    n.val = -n.val;*//*
                    Num n = (Num)elems.get(i);
                    n.val = new Number(-n.val.val);
                }
            }
        }
    }*/

    //удаление скобок вокруг одиночного числа, например, (55)->55
    private void removeBracketsAroundNumber(){
        for (int i = 1; i < elems.size()-1; ) {
            if (elems.get(i-1).isOpenBracket() && elems.get(i).isNumber() && elems.get(i+1).isCloseBracket()){
                i--;
                elems.remove(i+2);
                elems.remove(i);
                if (i==0) i++;
            } else i++;
        }
    }

}

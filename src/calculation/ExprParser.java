package calculation;


import java.util.ArrayList;
import java.util.List;

public class ExprParser {
    private Expr expr;
    private ElemsContainer elems;


    public Expr getExpr() { return expr; }
    public void setExpr(Expr expr) { this.expr = expr; }
    public ElemsContainer getElems() { return elems; }

    public ExprParser(Expr expr){
        this.expr = expr;
        elems = new ElemsContainer();
        expr.setElemsParser(this);
    }

    public void updateElems(int s, int len){
        int sElemIdx = elems.movePosAndGetFirst(s, len);
        elems.removeInvalidAfter(sElemIdx);// TODO: 08.09.2020 запихать в  movePosAndGetFirst
        scanElems(sElemIdx);
    }

    private void scanElems(int elemIdx){

        Elem undef = null;
        Elem elem;

        for (int i = elems.get(elemIdx).s; i < expr.len(); ) {
            elem = null;

            //функции не могут начинаться с этих символов, они будут начинаться с малеьких латинских букв
            switch(expr.charAt(i)){//0.,123456789
                case '0':case '.':case ',':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
                    elem = findNumber(i);
                    break;

                case '+':
                    elem = new Func(i, i+1, Function.funcs.get("+"));
                    break;

                case '-': case '−':
                    elem = new Func(i, i+1, Function.funcs.get("-"));
                    break;

                case '*': case '×':
                    elem = new Func(i, i+1, Function.funcs.get("*"));
                    break;

                case '/': case '÷':
                    elem = new Func(i, i+1, Function.funcs.get("/"));
                    break;

                case '^':
                    elem = new Func(i, i+1, Function.funcs.get("^"));
                    break;

                case '(':
                    elem = new Bracket(i, i+1, true);
                    break;

                case ')':
                    elem = new Bracket(i, i+1, false);
                    break;
            }

            if (i+6<=expr.len()) switch (expr.substring(i, i+6)){
                case "arcsin":
                    elem = new Func(i, i+6, Function.funcs.get("arcsin"));
                    break;
            }

            if (i+3<=expr.len()) switch (expr.substring(i, i+3)){
                case "sin":
                    elem = new Func(i, i+3, Function.funcs.get("sin"));
                    break;
            }

            if (i+2<=expr.len()) switch (expr.substring(i, i+2)){
                case "lg":
                    elem = new Func(i, i+2, Function.funcs.get("lg"));
                    break;
                case "sh":
                    elem = new Func(i, i+2, Function.funcs.get("sh"));
                    break;
            }


            //если распознался элемент и раньше был пробел, то перед элементом вставляем undefined
            if (undef!=null && elem!=null){
                undef.e = i;
                elemIdx = elems.addElem(elemIdx, undef);
                undef = null;
                elemIdx++;
            }

            if (elem!=null){
                elemIdx = elems.addElem(elemIdx, elem);
                elems.removeInvalidAfter(elemIdx);
                if (elems.nextIsSequential(elemIdx)) break;//если конец текущего элемнта совпал с началом следующего элемента, то вставляем undefined и выходим
                elemIdx++;
                i=elem.e;
            } else {
                if (undef==null) undef = new Elem(i);
                i++;

                if (i==expr.len()){//если конец строки и был пробел, то вставляем undefined и выходим
                    undef.e = expr.len();
                    elemIdx = elems.addElem(elemIdx, undef);
                    elems.removeInvalidAfter(elemIdx);
                    break;
                }
                if (elemIdx<elems.size() && i==elems.get(elemIdx).s){//если конец undefined совпал с началом следующего элемента, то вставляем undefined и выходим
                    undef.e=i;
                    elemIdx = elems.addElem(elemIdx, undef);
                    elems.removeInvalidAfter(elemIdx);
                    break;
                }

            }


            /*if (undef!=null && (elem!=null || i+1 == expr.len())){
                undef.e = i;
                elemIdx = elems.addElem(elemIdx, undef);
                if (i+1 == expr.len()) elems.removeOldAfter(elemIdx);
                undef = null;
                elemIdx++;
            }
            if (elem!=null){
                elemIdx = elems.addElem(elemIdx, elem);
                elems.removeOldAfter(elemIdx);
                if (elems.nextIsSequential(elemIdx)) break; else elemIdx++;
                i=elem.e;
            } else {
                if (undef==null) undef = new Elem(i);
                i++;
                if (i==expr.len()) {
                    undef.e = i;
                    elemIdx = elems.addElem(elemIdx, undef);
                    elems.removeOldAfter(elemIdx);
                    undef = null;
                    elemIdx++;
                }
            }*/

        }
    }



    private Elem findNumber(int s){
        int e;
        loop: for (e = s; e <= expr.len(); e++) {
            if (e==expr.len()) break loop;
            switch (expr.charAt(e)){
                case '0':case '.':case ',':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
                    break;
                default: break loop;
            }
        }
        return new Num(s, e, expr.substring(s, e));
    }

    

    public static class ElemsContainer{
        private final List<Elem> elems;// TODO: 10.09.2020 параллельно хранить список скобок

        public ElemsContainer() {
            elems = new ArrayList<>();
            elems.add(new Elem(0, 0));
        }

        private Elem get(int idx){ return elems.get(idx); }
        private int size(){ return elems.size(); }
        private void replaceElem(int elemIdx, Elem elem){ elems.set(elemIdx, elem); }
        private int addElem(int elemIdx, Elem elem){
            if (elemIdx==0) elemIdx++;
            elems.add(elemIdx, elem);
            return elemIdx;
        }
        private void removeInvalidAfter(int idx){
            Elem elem = elems.get(idx);
            while (true){
                if (idx+1<elems.size() && elems.get(idx+1).s<elem.e) {
                    elems.remove(idx+1);
                } else break;
            }
        }
        private boolean nextIsSequential(int currIdx){
            return currIdx+1 != elems.size() && elems.get(currIdx).e == elems.get(currIdx + 1).s;
        }
        //вернуть индекс первого элемента, оставшегося без изменений (обход: начало <-- конец)
        private int movePosAndGetFirst(int s, int len){
            for (int i = elems.size()-1; i >= 0; i--) {
                Elem elem = elems.get(i);
                if (elem.e==s) return i;
                elem.e+=len;
                if (elem.s<s) {
                    elems.remove(i);//удаление точно невалидного элемента
                    return i-1;
                }
                elem.s+=len;
            }
            return 0;
        }


        public List<Elem> getElems() {
            return elems;
        }
    }
}

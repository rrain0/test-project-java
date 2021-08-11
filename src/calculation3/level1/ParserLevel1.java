package calculation3.level1;


import calculation3.Expr;

import java.util.ArrayList;
import java.util.List;

public class ParserLevel1 {
    private Expr expr;
    private ElemsContainer elems;


    public Expr getExpr() { return expr; }
    public void setExpr(Expr expr) { this.expr = expr; }
    public ElemsContainer getElems() { return elems; }

    public ParserLevel1(Expr expr){
        this.expr = expr;
        elems = new ElemsContainer();
        expr.setExprParser(this);
    }

    public void updateElems(int s, int oldLen, int newLen){ //индекс начала, длина удаляемого, длина вставляемого
        int sElemIdx = elems.ShiftAndRemoveElemsAndGetFirstToScan(s, oldLen, newLen);
        scanElems(sElemIdx);
    }

    private void scanElems(int elemIdx){ //первый пустой индекс в массиве элементов

        Elem undef = null;
        Elem elem;

        for (int i = elems.get(elemIdx).s; i < expr.len(); ) {
            elem = null;


            if (i+1<=expr.len()) switch(expr.charAt(i)){

                case '+':
                    elem = new FunElem(i, i+1, "+");
                    break;

                case '-': case '−':
                    elem = new FunElem(i, i+1, "-");
                    break;

                case '*': case '×':
                    elem = new FunElem(i, i+1, "*");
                    break;

                case '/': case '÷':
                    elem = new FunElem(i, i+1, "/");
                    break;

                case '^':
                    elem = new FunElem(i, i+1, "^");
                    break;

                case '(':
                    elem = new BracketElem(i, i+1, true);
                    break;

                case ')':
                    elem = new BracketElem(i, i+1, false);
                    break;
            }

            if (i+6<=expr.len()) switch (expr.substring(i, i+6)){
                case "arcsin":
                    elem = new FunElem(i, i+6, "arcsin");
                    break;
            }

            if (i+3<=expr.len()) switch (expr.substring(i, i+3)){
                case "sin":
                    elem = new FunElem(i, i+3, "sin");
                    break;
            }

            if (i+2<=expr.len()) switch (expr.substring(i, i+2)){
                case "lg":
                    elem = new FunElem(i, i+2, "lg");
                    break;
                case "sh":
                    elem = new FunElem(i, i+2, "sh");
                    break;
            }

            if (i+1<=expr.len()) switch(expr.charAt(i)){//0.,123456789
                case '0':case '.':case ',':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
                    elem = findNumber(i);
                    break;
            }


            if (elem == null) {
                if (undef == null) {
                    elemIdx = elems.addElem(elemIdx, undef = new Elem(i, ++i));
                    elems.removeInvalidAfter(elemIdx);
                    if (elems.nextIsSequential(elemIdx)) break;
                    elemIdx++;
                } else undef.e = ++i;
            } else {
                undef = null;
                elemIdx = elems.addElem(elemIdx, elem);
                elems.removeInvalidAfter(elemIdx);
                if (elems.nextIsSequential(elemIdx)) break;
                elemIdx++;
                i=elem.e;
            }

        }
    }



    private Elem findNumber(int s){
        int e;
        loop: for (e = s; e < expr.len(); e++) {
            switch (expr.charAt(e)){
                case '0':case '.':case ',':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':
                    break;
                default: break loop;
            }
        }
        return new NumElem(s, e, expr.substring(s, e));
    }

    

    public static class ElemsContainer{
        private final List<Elem> elems;// TODO: 10.09.2020 параллельно хранить список скобок

        public ElemsContainer() {
            elems = new ArrayList<>();
            elems.add(new Elem(0, 0));
        }

        private Elem get(int idx){ return elems.get(idx); }
        private int size(){ return elems.size(); }
        //private void replaceElem(int elemIdx, Elem elem){ elems.set(elemIdx, elem); }
        private int addElem(int elemIdx, Elem elem){
            if (elemIdx==0) elemIdx=1;
            elems.add(elemIdx, elem);
            return elemIdx;
        }

        private void removeInvalidAfter(int idx){
            Elem elem = elems.get(idx);
            while (idx+1<elems.size() && elems.get(idx+1).s<elem.e){
                elems.remove(idx+1);
            }
        }

        // вернуть истину, если следующий элемент идёт сразу за предыдущим или конец элементов
        private boolean nextIsSequential(int currIdx){
            return currIdx+1 < elems.size() && elems.get(currIdx).e == elems.get(currIdx+1).s;
        }

        //вернуть индекс первого элемента для повторного сканирования (обход: начало <-- конец)
        private int ShiftAndRemoveElemsAndGetFirstToScan(int s, int oldLen, int newLen){
            int lenDiff = newLen - oldLen;
            for (int i = elems.size()-1; i >= 0; i--) {
                Elem elem = elems.get(i);
                if (elem.s > s+oldLen){
                    elem.s += lenDiff;
                    elem.e += lenDiff;
                } else if (elem.e > s){
                    elems.remove(i);
                } else return i;
            }
            return 0;
        }


        public List<Elem> getElems() {
            return elems;
        }
    }
}

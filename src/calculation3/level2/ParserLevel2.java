package calculation3.level2;

import calculation3.level1.Elem;
import calculation3.level1.FunElem;
import calculation3.level1.NumElem;
import calculation3.level1.ParserLevel1;

import java.util.ArrayList;
import java.util.List;

public class ParserLevel2 {
    private ParserLevel1 parser1;
    private List<Elem> elems;
    private List<Elem2> elem2s;

    public ParserLevel2(ParserLevel1 parser1) {
        this.parser1 = parser1;
        elems = parser1.getElems().getElems();
        elem2s = new ArrayList<>();
    }

    public List<Elem2> getElem2s() {
        return elem2s;
    }

    public void parse(){
        for(Elem e : elems){
            Elem2 e2 = cast(e);
            if (e2 != null) elem2s.add(e2);
        }
    }


    private Elem2 cast(Elem e){
        Elem2 e2 = null;
        if (e.isNumber()) {
            NumElem ee = (NumElem)e;
            e2 = new NumElem2(e.s, e.e, new Number(Double.parseDouble(((NumElem) e).val)));
        } else if (e.isFunction()) {
            FunElem ee = (FunElem)e;
            e2 = new FunElem2(e.s, e.e, Function.funcs.get(ee.f));
        } else if (e.isOpenBracket()){
            e2 = new OpenBracketElem2(e.s, e.e);
        } else if (e.isCloseBracket()){
            e2 = new CloseBracketElem2(e.s, e.e);
        }
        return e2;
    }

}

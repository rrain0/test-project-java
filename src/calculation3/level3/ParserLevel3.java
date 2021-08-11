package calculation3.level3;

import calculation3.level2.Elem2;
import calculation3.level2.ParserLevel2;

import java.util.List;

public class ParserLevel3 {
    private ParserLevel2 parser2;
    private List<Elem2> elem2s;

    public ParserLevel3(ParserLevel2 parser2) {
        this.parser2 = parser2;
        elem2s = parser2.getElem2s();
    }

    public void firstParse(){
        Elem2 prev = new Elem2(), curr = null;
        for (int i = 0; i < elem2s.size(); i++) {
            prev = curr;
            curr = elem2s.get(i);

        }
    }

    public void parse(){

    }

}

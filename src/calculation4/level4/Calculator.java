package calculation4.level4;

import calculation4.level2.Fun;
import calculation4.level2.Node;
import calculation4.level2.Num;

import java.util.ArrayList;
import java.util.List;

import static calculation4.Utils.*;

public class Calculator {

    private List<Node> nodeList;

    public Calculator(List<Node> nodeList) {
        this.nodeList = nodeList;
    }


    public Num calculate(){
        List<Fun> stack = new ArrayList<>();
        stack.add((Fun)getLast(nodeList));
        Num answer = null;

        loop: while (!stack.isEmpty()){
            Fun f = getLast(stack);
            for (int i = 0; i < f.args.length; i++) {
                if (!(f.args[i] instanceof Num)) {
                    stack.add((Fun)f.args[i]);
                    continue loop;
                }
            }
            answer = f.calculate();
            if (f.up!=null) f.up.setArg(f.upIdx, answer);
            removeLast(stack);
        }

        return answer;
    }
}

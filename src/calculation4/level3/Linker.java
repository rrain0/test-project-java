package calculation4.level3;

import calculation4.level1.Token;
import calculation4.level2.Fun;
import calculation4.level2.Node;
import calculation4.level2.Num;

import static calculation4.TokensToObjects.*;
import static calculation4.Utils.*;
import static calculation4.level2.Fun.IMPLICIT_MULT;
import static calculation4.level2.Fun.MULT;

import java.util.ArrayList;
import java.util.List;

public class Linker {

    private List<Node> nodeList;

    public Linker(List<Node> nodeList) {
        this.nodeList = nodeList;
        supplement();
        makeLinks();
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    private void supplement(){
        int start = nodeList.get(0).token.s;
        int end = nodeList.get(nodeList.size()-1).token.e;
        nodeList.add(0, getNode(new Token("(", start,start))); // фиктивная открывающая скобка в начале
        nodeList.add(getNode(new Token(")", end,end))); // фиктивная закрывающая скобка в конце


        for (int i = 1; i < nodeList.size(); i++) {
            Node prev = get(nodeList, i-1);
            Node n = get(nodeList, i);

            // can't work with log(5)(5) log5(5) log(5)5
            if (!prev.hasPostOperands() && !n.hasPreOperands()){
                int priority = IMPLICIT_MULT;
                if (!(prev instanceof Num) || !(n instanceof Num)) priority = MULT;
                nodeList.add(i, getImplicitMult(new Token("", prev.token.e,prev.token.e), priority));
            }

            if (n.isMinus() && prev.hasPostOperands()){
                nodeList.set(i, getUnaryMinus(n.token, ((Fun)prev).priority));
            } else if (n.isPlus() && prev.hasPostOperands()){
                nodeList.set(i, getUnaryPlus(n.token, ((Fun)prev).priority));
            } else if (n.isDiv() && prev.hasPostOperands()){
                nodeList.set(i, getUnaryDiv(n.token, ((Fun)prev).priority));
            }
        }
    }

    private void makeLinks0(){
        List<Node> brackets = new ArrayList<>();

        for (int i = 0; i < nodeList.size(); i++) {
            Node n = nodeList.get(i);
            if (n instanceof Fun){
                Fun f = (Fun)n;
                if (f.isOpenBracket()) {
                    Fun open = f;
                    brackets.add(open);
                    open.setArg(open.preArgsCnt, nodeList.get(i+1)); // first post arg
                } else if (f.isCloseBracket()){
                    Node open = removeLast(brackets);
                    f.becomeUpon(open, 0);
                } else {
                    if (f.hasPreOperands()){ // пока что учитываем 1 преоперанд
                        Node prev = nodeList.get(i-1);
                        if (prev.up==null || prev.up.priority <= f.priority) {
                            f.becomeUpon(prev, f.preArgsCnt-1);
                        } else {
                            f.becomeUpon(prev.up, f.preArgsCnt-1);

                        }

                    }
                    if (f.hasPostOperands()){ // пока что учитываем 1 постоперанд
                        f.setArg(f.preArgsCnt, nodeList.get(i+1));
                    }
                }
            }
        }
    }


    private void makeLinks(){
        List<Node> brackets = new ArrayList<>();

        for (int i = 0; i < nodeList.size(); i++) {
            Node n = nodeList.get(i);
            if (n instanceof Fun){
                Fun f = (Fun)n;
                if (f.isOpenBracket()) {
                    Fun open = f;
                    brackets.add(open);
                    open.setArg(open.preArgsCnt, nodeList.get(i+1)); // first post arg
                } else if (f.isCloseBracket()){
                    Node open = removeLast(brackets);
                    f.becomeUpon(open, 0);
                } else {
                    if (f.hasPreOperands()){ // пока что учитываем 1 преоперанд
                        Node prev = nodeList.get(i-1);
                        f.becomeUpon(prev, f.preArgsCnt-1); // войти в дерево

                        Fun up = f.up;
                        while (up.priority >= f.priority){
                            f.lift(f.preArgsCnt-1); // подняться по дереву
                            up = f.up;
                        }
                    }
                    if (f.hasPostOperands()){ // пока что учитываем 1 постоперанд
                        f.setArg(f.preArgsCnt, nodeList.get(i+1));
                    }
                }
            }
        }
    }


}

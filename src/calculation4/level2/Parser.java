package calculation4.level2;

import calculation4.level1.NumberToken;
import calculation4.level1.StaticToken;
import calculation4.level1.Token;
import calculation4.level1.Tokens;

import java.util.ArrayList;
import java.util.List;

import static calculation4.TokensToObjects.getNode;

public class Parser {

    private List<Token> tokenList;
    private List<Node> nodeList = new ArrayList<>();

    public Parser(List<Token> tokenList) {
        this.tokenList = tokenList;
        parse();
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    private void parse(){
        for (var t : tokenList){
            if (t instanceof StaticToken) nodeList.add(getNode(t));
            else if (t instanceof NumberToken) nodeList.add(parseNumber((NumberToken)t));
        }
    }

    private Num parseNumber(NumberToken nt){
        String n = nt.number;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Tokens.digitSeparators.forEach(c->sb.append((char)(int)c));
        sb.append(']');
        n = n.replaceAll(sb.toString(), "");
        if (nt.radix==10) n = n.replaceAll("E", "e");
        return new Num(Double.parseDouble(n), nt);
    }

}

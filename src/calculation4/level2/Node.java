package calculation4.level2;

import calculation4.level1.Token;

public abstract class Node {
    public Token token;

    public Fun up;
    public int upIdx = -1;

    public Node(Token token) {
        this.token = token;
    }


    public boolean hasOperands(){ return false; }
    public boolean hasPreOperands(){ return false; }
    public boolean hasPostOperands(){ return false; }

    public boolean isMinus(){ return false; }
    public boolean isPlus(){ return false; }
    public boolean isDiv(){ return false; }

    public boolean isOpenBracket(){ return false; }
    public boolean isCloseBracket(){ return false; }
}

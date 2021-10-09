package calculation4.level1;

import java.util.List;

import static calculation4.Utils.listToString;

public class TokenParseException extends RuntimeException {
    public List<Token> tokenList;
    public int fromIdx;

    public TokenParseException(List<Token> tokenList, int fromIdx) {
        this.tokenList = tokenList;
        this.fromIdx = fromIdx;
    }

    @Override
    public String toString() {
        return "TokenParseException: \n" +
            "Can't parse from idx "+fromIdx+"\n" +
            "Found tokens: "+listToString(tokenList);
    }
}

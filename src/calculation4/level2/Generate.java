package calculation4.level2;

import calculation4.level1.Token;

@FunctionalInterface
public interface Generate {
    Node generate(Token token);
}

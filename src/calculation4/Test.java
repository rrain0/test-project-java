package calculation4;

import calculation4.level1.Token;
import calculation4.level1.TokenParseException;
import calculation4.level1.Tokenizer;
import calculation4.level2.Node;
import calculation4.level2.Parser;

import java.util.List;

import static calculation4.Utils.listToString;

public class Test {
    public static void main(String[] args) {
        {
            String expr = "5+9*5E+2/.1E2";
            System.out.println("expression: "+expr);
            try {
                Tokenizer tokenizer = new Tokenizer(expr, 10);
                List<Token> tokenList = tokenizer.getTokens();
                System.out.println(listToString(tokenList));
                Parser parser = new Parser(tokenList);
                List<Node> nodeList = parser.getNodeList();
                System.out.println(listToString(nodeList));
            } catch (TokenParseException e) {
                e.printStackTrace();
            }
        }
        {
            String expr = "(π/3*9^3*tg(3°))+\n" +
                "([π/3(9+6*tg(15°))^3/tg(15°)]-[π/3*9^3/tg(15°)])+\n" +
                "([π/3(9+6*tg(15°))^3*tg(3°)]-[π/3*10^2(10*tg(3°))])+\n" +
                "(π*10^2*(10-(9+6*tg(15°)-10)*tg(3°)-(25-20)tg(3°)))+\n" +
                "([π/3*12.5^3*tg(3°)]-[π/3*10^3*tg(3°)])+\n" +
                "(π*12.5^2(50-6-10-15-15))+\n" +
                "(3√(3)/2*((10+1.482)*2/√(3))^2*15)+\n" +
                "(π/3*([10+1.482+(10+1.482)*2/√(3)]/2)^3*tg(3°))-( π/3*8^3*tg(3°))+\n" +
                "(π*8^2*(15-([10+1.482+(10+1.482)*2/√(3)]/2*tg(3°))-1.5))+\n" +
                "([π/3*8^3*tg(45°)]-[π/3*(8-1.5)^3*tg(45°)])+\n" +
                "(π/3*(8-1.5)^3*tg(3°))";
            System.out.println("expression: "+expr);
            try {
                Tokenizer tokenizer = new Tokenizer(expr, 10);
                List<Token> tokenList = tokenizer.getTokens();
                System.out.println(listToString(tokenList));
            } catch (TokenParseException e) {
                e.printStackTrace();
            }
        }
        {
            String expr = "* dfjdktand .0E+1 df+00'";
            System.out.println("expression: "+expr);
            try {
                Tokenizer tokenizer = new Tokenizer(expr, 10);
                List<Token> tokenList = tokenizer.getTokens();
                System.out.println(listToString(tokenList));
            } catch (TokenParseException e) {
                e.printStackTrace();
            }
        }
        {
            String expr = "* 5_5'5 5.'E_+ 67";
            System.out.println("expression: "+expr);
            try {
                Tokenizer tokenizer = new Tokenizer(expr, 10);
                List<Token> tokenList = tokenizer.getTokens();
                System.out.println(listToString(tokenList));
            } catch (TokenParseException e) {
                e.printStackTrace();
            }
        }
        {
            String expr = "* 5radix0::0radix16::A34.Atanoct7. ";
            System.out.println("expression: "+expr);
            try {
                Tokenizer tokenizer = new Tokenizer(expr, 10);
                List<Token> tokenList = tokenizer.getTokens();
                System.out.println(listToString(tokenList));
            } catch (TokenParseException e) {
                e.printStackTrace();
            }
        }

        {
            String expr = "+sintantanhlogloglogloglogloglog/loglog2log";
            System.out.println("expression: "+expr);
            try {
                Tokenizer tokenizer = new Tokenizer(expr, 10);
                List<Token> tokenList = tokenizer.getTokens();
                System.out.println(listToString(tokenList));
            } catch (TokenParseException e) {
                e.printStackTrace();
            }
        }

        /*{
            String expr = "+sintanNtanhlogloglogloglogloglog/loglog2log";
            try {
                System.out.println("expression: "+expr);
                List<Token> tokenList = getTokens(expr);
                System.out.println(listToString(tokenList));
            } catch (TokenParseException e) {
                e.printStackTrace();
            }
        }*/

        {
            String expr = "+sin(tan)tanh+++)logloglogloglogloglog/ loglog2log";
            System.out.println("expression: "+expr);
            try {
                Tokenizer tokenizer = new Tokenizer(expr, 10);
                List<Token> tokenList = tokenizer.getTokens();
                System.out.println(listToString(tokenList));
            } catch (TokenParseException e) {
                e.printStackTrace();
            }
        }
    }
}

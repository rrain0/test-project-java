package calculation;

public class TEST {
    public static void main(String[] args) {

        Expr expr;
        ExprParser exprParser;
        ElemsCalculator elemsCalculator;


        expr = new Expr();
        exprParser = new ExprParser(expr);
        expr.insert(0, "-8");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println("ANSWER: " + new ElemsCalculator(exprParser).calculate2());
        System.out.println();


        expr = new Expr();
        exprParser = new ExprParser(expr);

        expr.insert(0, "8.5");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        expr.insert(0, "arclg");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        expr.remove(0,3);
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        expr.insert(5, "arclg");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        expr.replace(5,9, "sh");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        expr.insert(8, ")");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        expr.insert(7, "(");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println();

        expr = new Expr();
        exprParser = new ExprParser(expr);

        expr.insert(0, "8.5");
        expr.insert(0, "arc");
        expr.insert(3, "sh");
        expr.insert(4, "in");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println();

        expr = new Expr();
        exprParser = new ExprParser(expr);

        expr.insert(0, "66+55");
        expr.insert(3, "sin");
        expr.insert(3, "arc");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println();

        expr = new Expr();
        exprParser = new ExprParser(expr);

        expr.insert(0, "+");
        expr.insert(1, "231");
        expr.insert(0, "+");
        expr.insert(3, "8");
        expr.insert(0, "2");
        expr.insert(5, "+");
        expr.insert(5, "-");
        expr.insert(5, "+");
        expr.insert(5, "+");
        expr.insert(11, "354");
        expr.insert(11, "lg");
        expr.insert(15, "sin");
        expr.insert(15, "arc");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println();


        expr = new Expr();
        exprParser = new ExprParser(expr);
        expr.insert(0, "2+3*10");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println("ANSWER: " + new ElemsCalculator(exprParser).calculate2());
        System.out.println();

        expr = new Expr();
        exprParser = new ExprParser(expr);
        expr.insert(0, "(2+3)*10");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println("ANSWER: " + new ElemsCalculator(exprParser).calculate2());
        System.out.println();

        expr = new Expr();
        exprParser = new ExprParser(expr);
        expr.insert(0, "2+3*8*lg100");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println("ANSWER: " + new ElemsCalculator(exprParser).calculate2());
        System.out.println();

        expr = new Expr();
        exprParser = new ExprParser(expr);
        expr.insert(0, "-(+2-(-lg100))*2+2");
        System.out.println(expr.getExpr());
        exprParser.getElems().getElems().forEach(System.out::println);
        System.out.println("ANSWER: " + new ElemsCalculator(exprParser).calculate2());
        System.out.println();

    }


}

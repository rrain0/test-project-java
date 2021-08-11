package calculation3;


import calculation3.level1.ParserLevel1;

public class TEST {
    public static void main(String[] args) {

        Expr expr;
        ParserLevel1 parserLevel1;


        expr = new Expr();
        parserLevel1 = new ParserLevel1(expr);
        expr.insert(0, "-8");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        System.out.println();


        expr = new Expr();
        parserLevel1 = new ParserLevel1(expr);

        expr.insert(0, "8.5");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        expr.insert(0, "arclg");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        expr.remove(0,3);
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        expr.insert(5, "arclg");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        expr.replace(5,9, "sh");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        expr.insert(8, ")");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        expr.insert(7, "(");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        System.out.println();


        expr = new Expr();
        parserLevel1 = new ParserLevel1(expr);

        expr.insert(0, "8.5");
        expr.insert(0, "arc");
        expr.insert(3, "sh");
        expr.insert(4, "in");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        System.out.println();


        expr = new Expr();
        parserLevel1 = new ParserLevel1(expr);

        expr.insert(0, "66+55");
        expr.insert(3, "sin");
        expr.insert(3, "arc");
        System.out.println(expr.getExpr());
        parserLevel1.getElems().getElems().forEach(System.out::println);
        System.out.println();


        expr = new Expr();
        parserLevel1 = new ParserLevel1(expr);

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
        parserLevel1.getElems().getElems().forEach(System.out::println);
        System.out.println();



    }
}

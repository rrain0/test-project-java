package calculation;


public class Expr {
    private StringBuilder expr;
    private ExprParser exprParser;


    public String getExpr() { return expr.toString(); }

    public ExprParser getElemsParser() { return exprParser; }
    public void setElemsParser(ExprParser exprParser) { this.exprParser = exprParser; }

    public Expr() {
        expr = new StringBuilder();
    }


    public void insert(int idx, String str){
        if (str==null || str.length()==0) return;
        if (idx > expr.length()) idx = expr.length();
        else if (idx < 0) idx = 0;

        expr.insert(idx, str);
        exprParser.updateElems(idx, str.length());
    }

    public void remove(int start, int end){
        if (start >= expr.length() || end<=0 || start>=end) return;
        if (start<0) start=0;
        if (end>expr.length()) end = expr.length();

        expr.delete(start, end);
        exprParser.updateElems(start, start-end);
    }

    public void replace(int s, int e, String str){
        if (str==null) str = "";
        if (s >= expr.length() || e<=0 || s>e) return;
        if (s<0) s=0;
        if (e>expr.length()) e = expr.length();

        expr.replace(s, e, str);
        exprParser.updateElems(s, s-e+str.length());
    }

    public int len(){ return expr.length(); }
    public char charAt(int idx){ return expr.charAt(idx); }
    public String substring(int s, int e){ return expr.substring(s,e); }

}

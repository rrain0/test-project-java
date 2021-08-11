package calculation3;


import calculation3.level1.ParserLevel1;

public class Expr {
    private StringBuilder expr;
    private ParserLevel1 parserLevel1;


    public String getExpr() { return expr.toString(); }

    public ParserLevel1 getExprParser() { return parserLevel1; }
    public void setExprParser(ParserLevel1 parserLevel1) { this.parserLevel1 = parserLevel1; }

    public Expr() {
        expr = new StringBuilder();
    }


    public void insert(int idx, String str){
        if (str==null || str.length()==0) return;
        if (idx > expr.length()) idx = expr.length();
        else if (idx < 0) idx = 0;

        expr.insert(idx, str);
        parserLevel1.updateElems(idx, 0, str.length());
    }

    public void remove(int s, int e){
        if (s >= expr.length() || e<=0 || s>=e) return;
        if (s<0) s=0;
        if (e>expr.length()) e = expr.length();

        expr.delete(s, e);
        parserLevel1.updateElems(s, e-s, 0);
    }

    public void replace(int s, int e, String str){
        if (str==null) str = "";
        if (s >= expr.length() || e<=0 || s>e) return;
        if (s<0) s=0;
        if (e>expr.length()) e = expr.length();

        expr.replace(s, e, str);
        parserLevel1.updateElems(s, e-s, str.length());
    }

    public int len(){ return expr.length(); }
    public char charAt(int idx){ return expr.charAt(idx); }
    public String substring(int s, int e){ return expr.substring(s,e); }

}

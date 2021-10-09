package calculation4;

import java.util.*;

public class Tokenizer {

    public static void main(String[] args) {
        String expr = "+sintantanh/loglog2log";
        System.out.println(listToString(getTokens(expr)));
    }


    private static List<Token> getTokens(String expr){
        List<Token> tokenList = new ArrayList<>();


        for (int i = 0; i < expr.length(); i++) {
            // цифра может начинаться с [.0-9]
            char c = expr.charAt(i);
            if ( c!='.' && (c<'0' || c>'9') ){
                Token t = nextToken(expr, i);
                if (t==null) throw new RuntimeException(listToString(tokenList));
                i = t.e-1;
                tokenList.add(t);
            }
        }

        return tokenList;
    }







    private static final Set<String> funcSet;
    static {
        funcSet = new HashSet<>();
        funcSet.add("+");
        funcSet.add("-");
        funcSet.add("*");
        funcSet.add("/");
        funcSet.add("^");
        funcSet.add("tanh");
        funcSet.add("tan");
        funcSet.add("th");;
        funcSet.add("arth");
        funcSet.add("sin");
        funcSet.add("arcsin");
        funcSet.add("lg");
        funcSet.add("log2");
        funcSet.add("log");
    }


    private static class Token{
        String token;
        int s, e;

        public Token(String token, int s, int e) {
            this.token = token;
            this.s = s;
            this.e = e;
        }

        @Override
        public String toString() {
            return "Token("+token+", ["+s+","+e+"])";
        }
    }


    private static Token nextToken(final String expr, final int s){
        Set<String> tmpFuncSet = new HashSet<>(funcSet);
        String lastFound = null;

        for (int i = 0; ; i++) {

            if (tmpFuncSet.isEmpty()) break;

            Iterator<String> it = tmpFuncSet.iterator();
            while (it.hasNext()){
                String f = it.next();
                int flen = f.length();
                // если название функции минус проверенные символы длиннее множества функций, то проверяем сразу всю функцию
                if (flen-i > tmpFuncSet.size()){
                    if ( substr(expr, s+i, s+flen).equals(substr(f,i,flen)) && flen>len(lastFound) ){
                        lastFound = f;
                    }
                    it.remove();
                } else {
                    int eChar = charAt(expr, s+i);
                    int fChar = charAt(f,i);
                    if (eChar==-1 || eChar!=fChar) it.remove();
                    else if (flen==i+1 && flen>len(lastFound)) lastFound=f;
                }
            }

        }

        return Optional.ofNullable(lastFound).map(f->new Token(f, s,s+f.length())).orElse(null);
    }



    private static int len(String str){
        if (str==null) return 0;
        return str.length();
    }
    private static int charAt(String str, int i){
        if (i<0 || i>=str.length()) return -1;
        return str.charAt(i);
    }
    private static String substr(String str, int s, int e){
        int len = str.length();
        if (s<0) s=0;
        else if (s>=len) s=len-1;
        if (e<0) e=0;
        else if (e>len) e=len;
        if (s>=e) return "";
        return str.substring(s,e);
    }


    private static String listToString(List<?> list){
        if (list==null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        list.forEach(e->sb.append(e).append(", "));
        sb.append(']');
        sb.append(" as ").append(list.size());
        return sb.toString();
    }


    /*private static boolean outOfRange(String str, int i){
        return i<0 || i>=str.length();
    }*/
}

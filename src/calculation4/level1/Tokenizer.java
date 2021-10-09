package calculation4.level1;

import java.util.*;

import static calculation4.Utils.*;
import static calculation4.level1.Tokens.*;

public class Tokenizer {

    private String expr;
    private int radix;
    private List<Token> tokens = new ArrayList<>();
    //private List<Token> idxToToken = new ArrayList<>();


    public Tokenizer(String expr, int radix) {
        this.expr = expr;
        this.radix = radix;
        findTokens();
    }

    public List<Token> getTokens(){ return tokens; }



    private void findTokens(){
        //if (!checkRadix(radix)) throw new RuntimeException("Radix must be in range [0,16]");

        int sErr = -1;
        for (int i = 0; i < expr.length(); i++) {
            Token t = null;
            if (t==null) t = nextEmptyToken(i);
            if (t==null) t = nextStaticToken(i);
            if (t==null) t = nextNumberToken(i, radix);


            if (t!=null){
                i = t.e;
                if (sErr!=-1){
                    tokens.add(new DichToken(substr(expr, sErr, t.s), sErr, t.s));
                    sErr = -1;
                }
                tokens.add(t);

                i--;
            } else {
                if (sErr==-1) sErr = i;
                if (i==expr.length()-1 && sErr!=-1) {
                    tokens.add(new DichToken(substr(expr, sErr, i+1), sErr, i+1));
                    sErr = -1;
                }
            }


            //throw new TokenParseException(tokens, i);
        }

    }
/*
    public void insert(int s, String str){

    };
    public void remove(int s, int e){

    }
    public void replace(int s, int e, String str){

    }



    // todo make exception radix from number token parse
    // начать искать токены от s
    private void findTokens(int s, int firstNewTokenIdx){
        if (!checkRadix(radix)) throw new RuntimeException("Radix must be in range [0,16]");

        main: for (; s < expr.length(); s++) {
            Token t = null;
            if (t==null) t = nextEmptyToken(s);
            if (t==null) t = nextStaticToken(s);
            if (t==null) t = nextNumberToken(s, radix);

            if (t!=null){
                s = t.e-1;
                tokens.add(t); firstNewTokenIdx++;
                // add to idtotokenlist

                Token next = null;
                while (firstNewTokenIdx<tokens.size()){
                    next = tokens.get(firstNewTokenIdx);
                    if (t.e==next.s) break main;
                    if (t.e>next.s) tokens.remove(firstNewTokenIdx);
                }
                continue;
            }

            throw new TokenParseException(tokens, s);
        }

    }*/








    private Token nextEmptyToken(final int s){
        for (int i = 0; ; i++) {
            if (!empties.contains(charAt(expr,s+i)))
                if (i==0) return null;
                else return new Token(substr(expr, s, s+i), s, s+i);
        }
    }


    private StaticToken nextStaticToken(final int s){
        Set<String> tmpStaticTokens = new HashSet<>(staticTokens);
        String lastFound = null;

        for (int i = 0; !tmpStaticTokens.isEmpty(); i++) {
            Iterator<String> it = tmpStaticTokens.iterator();
            while (it.hasNext()){
                String f = it.next();
                int flen = f.length();
                // если название функции минус проверенные символы длиннее множества функций, то проверяем сразу всю функцию
                if (flen-i > tmpStaticTokens.size()){
                    if ( substr(expr, s+i, s+flen).equals(substr(f,i,flen)) && flen>len(lastFound) ){
                        lastFound = f;
                    }
                    it.remove();
                } else {
                    int eChar = charAt(expr, s+i);
                    int fChar = charAt(f,i);
                    if (eChar==-1 || eChar!=fChar) it.remove();
                    else if (flen==i+1 && flen>len(lastFound)) {
                        lastFound=f;
                        it.remove();
                    }
                }
            }

        }

        return Optional.ofNullable(lastFound).map(f->new StaticToken(f, s,s+f.length())).orElse(null);
    }




    // todo возможно лучше сначала разбить на отдельные токены (цифры, E + - .) а потом на следующем уровне собирать их в числа
    // todo unary radix как количество единиц
    // todo можно даже 11011 считать за 4
    // todo radix1 => 0; radix1::101 => 2
    // todo 5_5'5 5.'E_+ 67' - это считается числом - как минимум разделители могут быть только внутри числа
    private NumberToken nextNumberToken(final int s, int radix){
        int ss = s;
        String first3 = substr(expr, s, s+3);
        switch (first3){
            case "bin": ss=s+3; radix=2; break; // bin1010
            case "oct": ss=s+3; radix=8; break; // oct777
            case "dec": ss=s+3; radix=10; break; // dec999
            case "hex": ss=s+3; radix=16; break; // hexBAF
            default: // radix6::543210
                String first5 = substr(expr, s, s+5);
                if (first5.equals("radix")){
                    ss = s+5;
                    for (int i = ss; ; i++) {
                        if (!isDigit(charAt(expr, i))) {
                            String r = substr(expr, ss, i);
                            if (r.length()==0) return null;
                            radix = Integer.parseInt(r);
                            if (!checkRadix(radix)) return null;
                            ss+=r.length();
                            if (!substr(expr,ss,ss+2).equals("::")) return null;
                            ss+=2;
                            break;
                        }
                    }
                }
        }

        Set<Integer> numbers = new HashSet<>();
        if (radix==0) numbers.add((int)'0');
        if (radix==1) numbers.add((int)'1');
        for (int i = 0; i < radix; i++) numbers.add(i<10 ? '0'+i : 'A'+i-10);

        Set<Integer> radix10Set = new HashSet<>();
        if (radix==10) radix10Set.add((int)'E');

        Set<Integer> point = new HashSet<>();
        point.add((int)'.');

        boolean hasDigits = false;
        int lastNotSeparatorIdx = ss-1;
        int e = ss;
        for (; ; e++) {
            int c = charAt(expr,e);
            if (digitSeparators.contains(c)) {
                if (!hasDigits) return null;
                continue;
            }
            if (numbers.contains(c)) {
                lastNotSeparatorIdx = e;
                hasDigits = true;
                continue;
            }
            if (point.contains(c)){
                lastNotSeparatorIdx = e;
                point.clear();
                continue;
            }
            if (radix10Set.contains(c)){
                if (!hasDigits) return null;
                lastNotSeparatorIdx = e;
                if (radix10Set.size()==1){
                    point.clear();
                    radix10Set.clear();
                    radix10Set.addAll(plusMinus);
                } else radix10Set.clear();
                continue;
            }
            break;
        }
        e = lastNotSeparatorIdx+1;
        if (ss==e) return null;
        if (ss+1==e && point.isEmpty()) return null;

        return new NumberToken(substr(expr, s, e), s, e, substr(expr, ss, e), radix);
    }


    private static boolean checkRadix(int radix){ return radix>=0 && radix<=16; }

}

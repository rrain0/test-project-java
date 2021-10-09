package calculation4;

import java.util.List;


public final class Utils {
    private Utils() {}


    public static int len(String str){
        if (str==null) return 0;
        return str.length();
    }
    public static int charAt(String str, int i){
        if (i<0 || i>=str.length()) return -1;
        return str.charAt(i);
    }
    public static boolean isDigit(int charr){
        return charr>='0' && charr<='9';
    }
    public static String substr(String str, int s, int e){
        int len = str.length();
        if (s<0) s=0;
        else if (s>=len) s=len-1;
        if (e<0) e=0;
        else if (e>len) e=len;
        if (s>=e) return "";
        return str.substring(s,e);
    }


    public static String listToString(List<?> list){
        if (list==null) return "null";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        list.forEach(e->sb.append(e).append(", "));
        sb.delete(sb.length()-2, sb.length());
        sb.append(']');
        sb.append(" with len ").append(list.size());
        return sb.toString();
    }


    public static boolean outOfRange(String str, int i){
        return i<0 || i>=str.length();
    }


}

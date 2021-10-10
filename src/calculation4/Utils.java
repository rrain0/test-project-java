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
        for (int i = 0; ; i++) {
            Object elem = list.get(i);
            sb.append(elem);
            if (i==list.size()-1) break;
            if ((i+1)%1==0) sb.append(",\n");
            else sb.append(", ");
        }
        sb.append(']');
        sb.append(" with len ").append(list.size());
        return sb.toString();
    }


    public static boolean outOfRange(String str, int i){
        return i<0 || i>=str.length();
    }

    public static <T> T get(List<T> list, int idx){
        return idx<0 || idx>=list.size() ? null : list.get(idx);
    }

    public static <T> T getLast(List<T> list, int idx){
        return idx<0 || idx>=list.size() ? null : list.get(list.size()-idx-1);
    }
    public static <T> T getLast(List<T> list){
        return list.isEmpty() ? null : list.get(list.size()-1);
    }

    public static <T> T removeLast(List<T> list, int idx){
        return idx<0 || idx>=list.size() ? null : list.remove(list.size()-idx-1);
    }
    public static <T> T removeLast(List<T> list){
        return list.isEmpty() ? null : list.remove(list.size()-1);
    }

}

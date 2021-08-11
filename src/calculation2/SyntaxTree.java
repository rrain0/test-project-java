package calculation2;

import java.util.ArrayList;

public class SyntaxTree {
    private final CharNode root;

    public SyntaxTree() {
        this.root = new CharNode();
    }

    public void add(String s){
        CharNode curr = root;
        for (int i = 0; i < s.length(); i++) {

            for (int j = 0; ; j++) {
                if (j >= curr.next.size()){
                    curr = new CharNode(s.charAt(i));
                    break;
                }
                if (curr.next.get(j).c == s.charAt(i)) {
                    curr = curr.next.get(j);
                    curr.funCnt++;
                    break;
                }
            }


        }
    }

    private static class CharNode{
        final char c;
        final ArrayList<CharNode> next;
        int funCnt;

        public CharNode() {
            c = 0;
            next = new ArrayList<>(100);
            funCnt = 0;
        }

        public CharNode(char c) {
            this.c = c;
            this.next = new ArrayList<>(20);
            this.funCnt = 1;
        }
    }

    public Elem getElem(String str, int s){
        StringBuilder sb = new StringBuilder();
        CharNode curr = root;
        int lastNodeFunCnt = root.funCnt;
        int len = 0;

        loop: for (int i = s; i < str.length(); i++) {


            for (int j = 0; j < curr.next.size(); j++) {
                if (str.charAt(i) == curr.next.get(j).c){
                    curr = curr.next.get(j);
                    if (curr.funCnt < lastNodeFunCnt){
                        len = sb.length();
                        lastNodeFunCnt = curr.funCnt;
                    }
                    sb.append(str.charAt(i));
                    continue loop;
                }
            }

            break;

        }
        return null;
    }
}

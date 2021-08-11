import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Test009 {

    public static void main(String[] args) {


        //long ll2;
        /*ll2 = Long.parseUnsignedLong("1000000000000000000");
        System.out.println(ll2);
        System.out.println(Long.toUnsignedString(ll2));*/
        /*ll2 = Long.parseUnsignedLong("10000000000000000000");
        System.out.println(ll2);
        System.out.println(Long.toUnsignedString(ll2));
        System.out.println(Long.toUnsignedString(ll2, 16));
        System.out.printf("%x%n", ll2);*/

        int i = 8;
        String s = Integer.toBinaryString(i);
        StringBuilder sb = new StringBuilder("00000000");
        sb.replace(sb.length()-s.length(), sb.length(), s);
        System.out.println(sb.toString());
        System.out.println(String.format("%8s", Integer.toBinaryString(i)).replace(' ','0'));

        /*ll2 = Long.parseUnsignedLong("100000000000000000000");
        System.out.println(ll2);
        System.out.println(Long.toUnsignedString(ll2));*/

        int var1 = 1;
        String var2 = "dsf";
        List<Void> var3 = new ArrayList<>();
        var3.add(null);

        method();
        method(var1, var2, var3);
    }

    private static void method(Object... arr){
        System.out.println(arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].getClass());
        }
    }

    private static void method0(){

        BitSet bs = new BitSet();
        byte a = 5, b = 7;
        //byte c = a>>b;
        long d = 6, e = 9;
        //int i = d>>e;
        int[] arr = new int[]{7, 8};
        System.out.println(arr.getClass().componentType());
        Class cl = void.class;
        System.out.println(cl.getName());

        Long ll1 = 9_000_000_000_000_000_000L;
        //Long ll = 10_000_000_000_000_000_000L;
        Long.parseUnsignedLong("10000000000000000000");

        long llll = Long.MIN_VALUE;
        //System.out.println(Long.toUnsignedLong(llll));

        //ll1.

        int i1 = 0x80_00_00_08;
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(i1<<1));
        System.out.println(Integer.toBinaryString(i1>>1));
        System.out.println(Integer.toBinaryString(i1>>>1));

        //byte.class;
        //if (b instanceof int)
        //System.out.println( (a>>b).class );
    }
}

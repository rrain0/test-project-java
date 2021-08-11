import java.util.ArrayList;
import java.util.Arrays;

public class Test008 {
    public static void main(String[] args) {
        int[] a = {1,2,3,3,8};
        int odds = 0;
        for (int i = 0; i < a.length; i++) if (a[i]%2==1) odds++;
        int evens = a.length-odds;

        int[] oddsArr = new int[odds];
        int[] evensArr = new int[evens];

        for (int i = a.length-1; i >= 0; i--) {
            if (a[i]%2==1) oddsArr[--odds] = a[i];
            else evensArr[--evens] = a[i];
        }

        System.out.println(Arrays.toString(oddsArr));
        System.out.println(Arrays.toString(evensArr));

        ArrayList<Integer> oddsList = new ArrayList<>();
        ArrayList<Integer> evensList = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
            if (a[i]%2==1) oddsList.add(a[i]);
            else evensList.add(a[i]);
    }
}

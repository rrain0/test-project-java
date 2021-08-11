import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test013 {
    public static void main(String[] args) {

        List<Integer> list = Stream.iterate(0, i -> i+2).limit(10000000).collect(Collectors.toList());

        long time;

        {
            time = System.currentTimeMillis();
            int[] ints = list.stream().mapToInt(i -> i).toArray();
            System.out.println(System.currentTimeMillis()-time);
        }

        {
            time = System.currentTimeMillis();
            int[] ints = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ints[i] = list.get(i);
            }
            System.out.println(System.currentTimeMillis()-time);
        }


    }
}

import java.util.List;
import java.util.Spliterator;

public class Test012 {
    public static void main(String[] args) {

        /*List<String> list = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        Spliterator<String> s1 = list.stream().spliterator();

        s1.tryAdvance(System.out::println);
        s1.tryAdvance(System.out::println);

        Spliterator<String> s2 = s1.trySplit();

        s1.tryAdvance(System.out::println);

        s2.tryAdvance(System.out::println);

        list.stream().reduce()*/

        System.out.println(.1e-5);


        String s = "|\\\"|";
        System.out.println(s);
        System.out.println(s.replaceAll("\\\\\"", "\""));


    }


}

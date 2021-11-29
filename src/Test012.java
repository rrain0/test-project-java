import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class Test012 {

    /*List<String> list = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    Spliterator<String> s1 = list.stream().spliterator();

    s1.tryAdvance(System.out::println);
    s1.tryAdvance(System.out::println);

    Spliterator<String> s2 = s1.trySplit();

    s1.tryAdvance(System.out::println);

    s2.tryAdvance(System.out::println);

    list.stream().reduce()*/


    public static void main(String[] args) {

        System.out.println(null instanceof String);




        List<String> list = List.of("kldsajflkjas");

        Arrays.stream(list.getClass().getGenericInterfaces()).forEach(System.out::println);

        //Arrays.stream(list.getClass().getGenericSuperclass()).forEach(System.out::println);

        System.out.println(list.getClass().getGenericSuperclass());

        /*System.out.println(
            ((ParameterizedType)list.getClass().getGenericInterfaces())
                .getActualTypeArguments()[0]
        );*/

        /*Class<T> persistentClass = (Class<T>)
            ((ParameterizedType)getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];*/

    }








}

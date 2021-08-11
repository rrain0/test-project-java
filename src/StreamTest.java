
import java.util.Comparator;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        IntStream.Builder builder = IntStream.builder();
        builder.add(2).add(3).accept(5);
        IntConsumer intConsumer = builder.andThen(System.out::println);
        intConsumer.accept(7); //сделать в builder.accept(7) и выполнить System.out.println(7)
        builder.accept(6);
        IntStream ints = builder.build();
        ints.forEach(i->System.out.print(i+" "));

        System.out.println();

        Stream<Integer> i1 = Stream.generate(()->0).limit(1);
        Stream<Integer> i2 = Stream.iterate(2, i->i*2).limit(10);
        Stream.concat(i1, i2).forEach(i->System.out.print(i+" "));

        System.out.println();

        Stream<String> people = Stream.of("Tom", "Bob", "Sam", "Tom", "Alice", "Kate", "Sam");
        people.sorted(Comparator.comparing(String::length).reversed().thenComparing(String::compareTo)).forEach(s->System.out.print(s+" "));
        System.out.println();

        record Name(String nameString){}
        Set<Name> names = Set.of(new Name("Dimas"), new Name("Alex"), new Name("Yura"));
        // toArray принимает на вход IntFunction<R> (это функция, принимающая один int и возвращающая R),
        // которой на вход передаётся длина стрима и ожидается массив элементов R (R[])
        String[] namesStringArr = names.stream().map(Name::nameString).toArray(String[]::new);
        // АНАЛОГИЧНО:
        String[] namesStringArr2 = names.stream().map(Name::nameString).toArray(len -> new String[len]);
        System.out.println("namesStringArr len: " + namesStringArr.length);
        System.out.println("namesStringArr2 len: " + namesStringArr2.length);
    }
}

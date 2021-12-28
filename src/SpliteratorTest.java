import java.util.List;
import java.util.Spliterator;

public class SpliteratorTest {

    public static void main(String[] args) {
        tryAdvanceTest();
        trySplitTest();
    }


    private static void tryAdvanceTest(){
        System.out.println("TRY ADVANCE TEST:");

        class StrWrap{
            public String s;
            public StrWrap(String s) {this.s = s;}
            @Override public String toString() { return String.format("StrWrap{s=%s}",s);}
        }

        var list = List.of(new StrWrap("a"), new StrWrap("b"), new StrWrap("c"));

        Spliterator<StrWrap> listSpliterator = list.spliterator();

        // пока есть элементы, tryAdvance выполнит функцию, переданную в него + вернёт true
        int cnt = 0;
        while (listSpliterator.tryAdvance(sw->sw.s=sw.s+" traversed")){
            cnt++;
        }

        /*
            ->
            StrWrap{s=a traversed}
            StrWrap{s=b traversed}
            StrWrap{s=c traversed}
            3
         */
        list.forEach(System.out::println);
        System.out.println(cnt);


        System.out.println();
    }


    private static void trySplitTest(){
        System.out.println("TRY SPLIT TEST:");

        var list = List.of("a","b","c","d");

        Spliterator<String> spliterator1 = list.spliterator();
        spliterator1.tryAdvance(System.out::println); // -> a // используем 1 элемент, он больше не будет участвовать в дальнейших операциях
        System.out.println();

        // делим поток пополам на 2 разных Сплитератора
        // исходному, у которого вызвали trySplit(), достаётся вторая (большая) половина (size - size/2), второму - первая (size/2)
        Spliterator<String> spliterator2 = spliterator1.trySplit();

        /*
            ->
            c
            d
            2
         */
        int cnt1 = 0;
        while (spliterator1.tryAdvance(System.out::println)) cnt1++;
        System.out.println(cnt1);
        System.out.println();

        /*
            ->
            b
            1
         */
        int cnt2 = 0;
        while (spliterator2.tryAdvance(System.out::println)) cnt2++;
        System.out.println(cnt2);
        System.out.println();


        System.out.println();
    }


}

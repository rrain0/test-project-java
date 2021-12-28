import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapTest {
    public static void main(String[] args) {


        // порядок элементов в Map.of не сохраняется
        System.out.println(Map.of("a",1, "b",2));
        System.out.println(Map.of("b",2, "a",1));

    }



    private static void mapKeyUpdationTest(){
        record Token(
            String token, Long time
        ){
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Token token1 = (Token) o;
                return Objects.equals(token, token1.token);
            }

            @Override
            public int hashCode() {
                return Objects.hash(token);
            }
        }

        // ключ как объект не обновится в мапе, пока не удалить и снова не положить туда Entry

        Map<Token,Object> map = new HashMap<>();

        map.put(new Token("aaa", 100L), "jjjj");
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });
        map.put(new Token("aaa", 0L), "hhhh"); // сам ключ не обновится, только значение
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });
        map.computeIfPresent(new Token("aaa", 100L), (t,v)->{
            return null;
        });
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });

    }


}

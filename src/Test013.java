import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test013 {
    public static void main(String[] args) {

        Map<Token,Object> map = new HashMap<>();


        map.put(new Token("aaa", 100L), "jjjj");
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });
        map.put(new Token("aaa", 0L), "jjjj");
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

    private static record Token(
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
}

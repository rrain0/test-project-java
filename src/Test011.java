import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class Test011 {

    public static void main(String[] args) {
        {
            Map<String, String> map = new HashMap<>();
        }
        {
            TreeMap<String, String> treeMap = new TreeMap<>();
        }
        {
            Map<KeyEnum, String> map = new HashMap<>();
            map.put(KeyEnum.THREE, "3");
            System.out.println(map.get(KeyEnum.FOUR));
            System.out.println(map.get(KeyEnum.THREE));
        }
        {
            Map<KeyEnum, String> map = new EnumMap<KeyEnum, String>(KeyEnum.class);
            map.put(KeyEnum.THREE, "3");
            System.out.println(map.get(KeyEnum.FOUR));
            System.out.println(map.get(KeyEnum.THREE));
        }
        {
            CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
            CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        }
        {
            BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(90);
        }
        {
            Map<String, String> map = new WeakHashMap<>();
            var synchronizedMap = Collections.synchronizedMap(map);
        }
        {
            ThreadLocalRandom tlRandom = ThreadLocalRandom.current();
            Random random = new Random();
        }
        {
            System.out.println(new String("hello") == new String("hello"));
            System.out.println(new String("hello").intern() == new String("hello").intern());
        }
        {

            Stream<String> s = Stream.of("kldsjfkdj").filter(e->true);
            System.out.println(s == s.filter(e->false)); // каждая операция над стримом всегда возвращает новый стрим
            //List<String> list = s.toList();
        }
        {
            new Object().hashCode();
        }
        {
            int i = Integer.MIN_VALUE;
            System.out.println(Integer.toBinaryString(i));
            System.out.println(Integer.toBinaryString(i>>>1));
        }
        {
            AtomicInteger i;
        }
        {
            ReentrantLock lock = new ReentrantLock(false);
            Condition condition = lock.newCondition();
        }
        {
            // Thread Local переменные - у каждого потока будут свои данные
            ThreadLocal<Integer> someThreadLocalInteger = new ThreadLocal<>();
            someThreadLocalInteger.set(6);
            someThreadLocalInteger.get();

            ThreadLocal<Double> someThreadLocalDouble = new ThreadLocal<>(){
                @Override
                protected Double initialValue() {
                    return 6d;
                }
            };
            someThreadLocalDouble.set(8d);
            someThreadLocalDouble.remove();
        }
        {

        }
    }

    @FunctionalInterface
    private interface Interface {
        double getDouble();
        default int getInt(){
            return 8;
        }
        //String getStirng();
    }




    private enum KeyEnum {
        ONE, TWO, THREE, FOUR
    }


    // синхронизация на объекте класса <ClassName>.class
    private static synchronized void m(){}


    private static class MySingleton {
        private static volatile MySingleton instance = null;

        public static MySingleton getInstance(){
            if (instance==null){
                return init();
            }
            return instance;
        }
        private static synchronized MySingleton init(){
            if (instance==null){
                return instance = new MySingleton();
            }
            return instance;
        }

    }
}

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestIterator {
    public static void main(String[] args) {
        //Map Iterator
        Map<String, String> map = new HashMap<>();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, String> e = it.next();
            System.out.println(e);
        }


    }

}

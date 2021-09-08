import java.lang.reflect.InvocationTargetException;

public class Test007 {

    
    static class DataCarrier<K,V> {K v1; V v2;}
    static DataCarrier<String, String> dc = new DataCarrier<>(){
        int v3;
    };
    static {
        try {
            var dc2 = dc.getClass().getDeclaredConstructor().newInstance();
            System.out.println(dc.getClass());
            System.out.println(DataCarrier.class);
            System.out.println(dc2.getClass());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {



    }


}

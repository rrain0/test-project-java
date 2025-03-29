public class Java14instanceof {
    public static void main(String[] args) {
        Object obj = "str";
        instanceofExample(obj);
    }

    private record Color(String color) {}
    private record Point(int x, int y, Color color) {}

    private static void instanceofExample(Object obj){

        //автоматическая проверка на тип, объявление новой переменной и cast в неё
        if (obj instanceof String str) System.out.println(str.concat("ing"));
        else System.out.println(obj); // Can't access str

        if (obj instanceof String str && str.length() > 2) System.out.println(str+", len = "+str.length());


        // record destructure (nested destructure are necessary) (Java 21+)
        if (obj instanceof Point(int x, var y, Color(var color))) {
            System.out.println("x = "+x+", y = "+y+", color = "+color);
        }
    }

    private static Object returnSmth() {
        return new Object();
        //return null;
    }
}

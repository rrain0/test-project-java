public class Java14instanceof {
    public static void main(String[] args) {
        Object obj = "str";
        instanceofExample(obj);
    }


    private static void instanceofExample(Object obj){

        //автоматическая проверка на тип, объявление новой переменной и cast в неё
        if (obj instanceof String str) System.out.println(str.concat("ing"));
        else System.out.println(obj); // Can't access str

        if (obj instanceof String str && str.length() > 2) System.out.println(str+", len = "+str.length());

    }
}

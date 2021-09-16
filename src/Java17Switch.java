public class Java17Switch {

    public static void main(String[] args) {


        switch (returnSmth()){
            case String s && s.length()>1 -> System.out.println("long string");
            case String s && s.length()==1 -> System.out.println("single char");
            case String s -> System.out.println("empty string");
            case Integer i && i>0 -> System.out.println("positive Integer "+i);
            case null -> System.out.println("no object");
            default -> System.out.println("something else");
        }

        switch (returnInteger()){
            case 6,7:
                System.out.println("6 or 7");
                break;
            case Integer i && i>6:
                System.out.println(">6");
                break;
            default: break;
        }


    }

    private static Integer returnInteger(){return 7;}


    private static Object returnSmth(){
        return new Object();
        //return null;
    }


}


public class Java13Switch {
    public static void main(String[] args) {
        String s ="shdbfk";
        int i = switch (s){
            case "hhh" -> 7;
            case "hhddhh" -> 9;
            case "shdbfk", "wjkdfjk" -> {
                System.out.println(10);
                yield 10;
            }
            default -> 8;
        };
        System.out.println(i);

        int i1 = switch (s){
            case "hhh": yield 7;
            case "hhddhh": case "hh": yield 9;
            case "sdjhfj", "shdbfk": case "gg": yield 10;
            default: yield 8;
        };
        System.out.println(i1);


        switch (s) {
            case "hhh": System.out.println(1); break;
            case "hhddhh":  System.out.println(2); break;
            case "sdjhfj", "shdbfk":  System.out.println(3); System.out.println(2); break;
            default:  System.out.println(4); break;
        }

        switch (s) {
            case "hhh" -> {System.out.println(1);}
            case "hhddhh" -> System.out.println(2);
            case "sdjhfj", "shdbfk" -> {System.out.println(3); System.out.println(2);}
            default -> System.out.println(4);
        }

    }
}

public class Java21Switch {

    public static void main(String[] args) {


        // Java 13+
        // switch can return value (new lambda syntax)
        int switchResult1 = switch (getInteger()) {
            case 6, 7 -> 10;
            case Integer i when i > 6 -> 20;
            default -> { System.out.println("default"); yield 100; }
        };

        // switch can return value (old syntax)
        int switchResult2 = switch ("shdbfk") {
            case "hhh": yield 7;
            case "hhddhh": case "hh": yield 9;
            case "sdjhfj", "shdbfk": case "gg": yield 10;
            default: yield 8;
        };


        // nested conditions after pattern matching ('when': Java 21+)('&&': Java 17+)
        switch (getObject()) {
            case String s when s.length() > 1 -> System.out.println("long string");
            case String s when s.length() == 1 -> System.out.println("single char");
            case String s -> System.out.println("empty string");
            case Integer i when i > 0 -> System.out.println("positive Integer "+i);
            case null -> System.out.println("no object");
            default -> System.out.println("something else");
        }

        switch (getInteger()) {
            case 6, 7:
                System.out.println("6 or 7");
                break;
            case Integer i when i > 6:
                System.out.println("> 6");
                break;
            default: break;
        }

        var detectPoint = switch (getObject()) {
            case Point(int x, var y, Color(var color)) -> "this is point";
            default -> "this is something else";
        };


    }

    private static Integer getInteger() { return 7; }


    private static Object getObject() {
        return new Object();
        //return null;
    }

    private record Color(String color) { }
    private record Point(int x, int y, Color color) { }

}

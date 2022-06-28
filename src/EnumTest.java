import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EnumTest {



    public static void main(String[] args) {

        System.out.println(Arrays.toString(E.names));

        System.out.println(ValuesEnum.valueOf("FIRST_VALUE"));
        System.out.println(ValuesEnum.valueOf(ValuesEnum.class, "FIRST_VALUE")); // то же, что и ValuesEnum.valueOf("FIRST_VALUE")
        System.out.println(Arrays.toString(ValuesEnum.values()));

        System.out.println(ValuesEnum.FIRST_VALUE.ordinal());
        System.out.println(ValuesEnum.FIRST_VALUE.toString());


        System.out.println(Months.January);
        System.out.println(Months.February);
        System.out.println(Months.January.name());

        Months jan = Months.January;
        String result = switch (jan){
            case March -> "M";
            case January,February -> "JF";
        };

    }



    static enum E {
        FIRST("first"),
        SECOND("second")
        ;

        public final String name;

        E (String name) {
            this.name = name;
            // names.length // нет доступа к статическим членам енама из конструктора - значит статик идёт после создания объектов енама
        }




        // у енамов сначала создаются объекты енама, а потом он статически инициализируется
        static String[] names;
        static {
            names = new String[E.values().length];
            for (int i = 0; i < values().length; i++) {
                names[i] = values()[i].name;
            }
        }

    }


    
    private enum ValuesEnum {
        // нельзя FIRST_VALUE = <что-то>,
        FIRST_VALUE,
        SECOND_VALUE,
        THIRD_VALUE,name;
    }

    private enum ValuesEnum2 {
        // тут же можно прокидывать параметры в конструктор
        FIRST_VALUE(56),
        SECOND_VALUE,
        THIRD_VALUE(-8);

        private int value;

        ValuesEnum2() { }
        ValuesEnum2(int value) { this.value = value; }

        public int getValue() { return value; }
        public void setValue(int value) { this.value = value; }
    }


    private enum Months{
        January("adsfjklj"), February("Asdf"), March;

        String property;

        Months() {}
        Months(String property) {
            this.property = property;
        }

        @Override
        public String toString() {
            return this==Months.January ? "Start of year" : super.toString();
        }
    }
}
